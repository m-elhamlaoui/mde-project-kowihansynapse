from flask import Flask, request, jsonify, send_file, Response
from flask_cors import CORS
import os
import json
import zipfile
import uuid
from datetime import datetime
from pathlib import Path
import xml.etree.ElementTree as ET
from xml.dom import minidom
import requests
app = Flask(__name__)
CORS(app)


UPLOAD_FOLDER = 'uploads'
GENERATED_FOLDER = 'generated'
SPRING_BOOT_URL = os.getenv('SPRING_BOOT_URL', 'http://localhost:8080')
IA_SERVICE_URL = os.getenv('IA_SERVICE_URL', 'http://localhost:7000')  
temporary_storage = {}

for folder in [UPLOAD_FOLDER, GENERATED_FOLDER]:
    os.makedirs(folder, exist_ok=True)

@app.route('/api/health', methods=['GET'])
def health_check():
    """Health check global (MDE + IA)"""
    
    mde_status = "available"
    mde_sessions = len([k for k in temporary_storage.keys() if not k.startswith('ia_')])
    
    
    ia_status = "unknown"
    try:
        ia_response = requests.get(f"{IA_SERVICE_URL}/", timeout=2)
        ia_status = "available" if ia_response.status_code == 200 else "unavailable"
    except:
        ia_status = "unavailable"
    
    ia_sessions = len([k for k in temporary_storage.keys() if k.startswith('ia_')])
    
    return jsonify({
        "status": "healthy",
        "service": "KowihanBackend Unified",
        "version": "3.0.0",
        "modes": {
            "mde": {
                "status": mde_status,
                "active_sessions": mde_sessions,
                "spring_boot_url": SPRING_BOOT_URL
            },
            "ia": {
                "status": ia_status,
                "active_sessions": ia_sessions,
                "ia_service_url": IA_SERVICE_URL
            }
        },
        "total_sessions": len(temporary_storage),
        "timestamp": datetime.now().isoformat()
    })

@app.route('/api/config/save',methods=['POST'])
def save_configuration():
    """
    Sauvegarde temporairement la configuration du projet
    """
    try:
        config_data = request.json
        
        if not config_data:
            return jsonify({
                "success": False,
                "error": "Aucune donnée reçue",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        required_fields = ['projectName', 'framework', 'database']
        missing_fields = [field for field in required_fields if field not in config_data]
        
        if missing_fields:
            return jsonify({
                "success": False,
                "error": f"Champs manquants: {', '.join(missing_fields)}",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        config_id = str(uuid.uuid4())[:8]  
        
        config_data['_metadata'] = {
            'id': config_id,
            'created_at': datetime.now().isoformat(),
            'status': 'pending'  
        }
        
        temporary_storage[config_id] = config_data
        
        return jsonify({
            "success": True,
            "message": "Configuration sauvegardée",
            "config_id": config_id,
            "data": {
                "projectName": config_data['projectName'],
                "framework": config_data['framework'],
                "database": config_data['database']['type'],
                "hasAuthentication": config_data.get('authentication', {}).get('enabled', False)
            },
            "next_step": "Envoyer le diagramme à /api/diagram/upload/xmi",
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/config/list',methods=['GET'])
def list_configuration():
    configs_list=[]
    for config_id, config_data in temporary_storage.items():
        configs_list.append({
            "id": config_id,
            "projectName": config_data.get('projectName', 'Inconnu'),
            "framework": config_data.get('framework', 'Inconnu'),
            "database": config_data.get('database', {}).get('type', 'Inconnu'),
            "created_at": config_data.get('_metadata', {}).get('created_at'),
            "status": config_data.get('_metadata', {}).get('status', 'unknown')
        })
    return jsonify({
        "success": True,
        "count": len(configs_list),
        "configurations": configs_list,
        "timestamp": datetime.now().isoformat()
    })

def parse_papyrus_xmi_content(xmi_content):
    """
    Parse le contenu XMI Papyrus pour extraire les informations UML
    Version corrigée pour le format spécifique Papyrus
    """
    try:
        # Parser le XML
        root = ET.fromstring(xmi_content)
        
        # Définir les namespaces
        namespaces = {
            'xmi': 'http://www.omg.org/spec/XMI/20131001',
            'uml': 'http://www.eclipse.org/uml2/5.0.0/UML',
            'ecore': 'http://www.eclipse.org/emf/2002/Ecore'
        }
        
        # Enregistrer les namespaces
        for prefix, uri in namespaces.items():
            ET.register_namespace(prefix, uri)
        
        entities = []
        relationships = []
        
        # 1. TROUVER LES CLASSES (packagedElement avec xmi:type="uml:Class")
        classes = root.findall(".//packagedElement[@xmi:type='uml:Class']", namespaces)
        
        # Fallback : si la méthode XPath échoue, itérer manuellement
        if not classes:
            for elem in root.iter():
                if 'packagedElement' in elem.tag and elem.get('{http://www.omg.org/spec/XMI/20131001}type') == 'uml:Class':
                    classes.append(elem)
        
        for cls in classes:
            class_name = cls.get('name', 'Unnamed')
            if not class_name or class_name == '':
                continue
                
            class_info = {
                'name': class_name,
                'id': cls.get('{http://www.omg.org/spec/XMI/20131001}id', '') or cls.get('xmi:id', ''),
                'isActive': cls.get('isActive', 'false') == 'true',
                'attributes': [],
                'associations': []
            }
            
            # 2. TROUVER LES ATTRIBUTS (ownedAttribute)
            attributes = cls.findall("./ownedAttribute")
            
            for attr in attributes:
                attr_name = attr.get('name', '')
                if not attr_name:
                    continue
                
                # Vérifier si c'est une association (a un attribut 'association')
                association_ref = attr.get('association')
                
                if not association_ref:  # C'est un attribut normal
                    # Déterminer le type
                    attr_type = 'String'  # default
                    type_href = ''
                    
                    # Chercher l'élément 'type' enfant
                    type_elem = attr.find('type')
                    if type_elem is not None:
                        type_href = type_elem.get('href', '')
                        
                        # Mapper les types UML vers des noms simples
                        if 'String' in type_href:
                            attr_type = 'String'
                        elif 'Integer' in type_href:
                            attr_type = 'Integer'
                        elif 'BigInteger' in type_href:
                            attr_type = 'BigInteger'
                        elif 'Float' in type_href:
                            attr_type = 'Float'
                        elif 'Boolean' in type_href:
                            attr_type = 'Boolean'
                        elif 'Date' in type_href:
                            attr_type = 'Date'
                        elif 'DateTime' in type_href:
                            attr_type = 'DateTime'
                    
                    # Extraire les cardinalités si présentes
                    lower_value = '1'  # default
                    upper_value = '1'  # default
                    
                    lower_elem = attr.find('lowerValue')
                    if lower_elem is not None:
                        lower_value = lower_elem.get('value', '1')
                    
                    upper_elem = attr.find('upperValue')
                    if upper_elem is not None:
                        upper_value = upper_elem.get('value', '1')
                    
                    attribute_info = {
                        'name': attr_name,
                        'type': attr_type,
                        'original_type': type_href,
                        'is_primary_key': 'id' in attr_name.lower(),
                        'multiplicity': {
                            'lower': lower_value,
                            'upper': upper_value
                        }
                    }
                    class_info['attributes'].append(attribute_info)
                else:
                    # C'est une propriété d'association
                    target_class_ref = attr.get('type', '')
                    
                    # Extraire les cardinalités
                    lower_value = '1'
                    upper_value = '1'
                    
                    lower_elem = attr.find('lowerValue')
                    if lower_elem is not None:
                        lower_value = lower_elem.get('value', '1')
                    
                    upper_elem = attr.find('upperValue')
                    if upper_elem is not None:
                        upper_value = upper_elem.get('value', '1')
                    
                    assoc_info = {
                        'name': attr_name,
                        'association_id': association_ref,
                        'target_class_ref': target_class_ref,
                        'type': 'association',
                        'multiplicity': {
                            'lower': lower_value,
                            'upper': upper_value
                        }
                    }
                    class_info['associations'].append(assoc_info)
            
            entities.append(class_info)
        
        # 3. TROUVER LES ASSOCIATIONS (packagedElement avec xmi:type="uml:Association")
        associations = root.findall(".//packagedElement[@xmi:type='uml:Association']", namespaces)
        
        # Fallback
        if not associations:
            for elem in root.iter():
                if 'packagedElement' in elem.tag and elem.get('{http://www.omg.org/spec/XMI/20131001}type') == 'uml:Association':
                    associations.append(elem)
        
        for assoc in associations:
            assoc_name = assoc.get('name', 'Unnamed')
            assoc_id = assoc.get('{http://www.omg.org/spec/XMI/20131001}id', '') or assoc.get('xmi:id', '')
            
            # Récupérer les memberEnd
            member_ends = []
            member_ends_str = assoc.get('memberEnd', '')
            if member_ends_str:
                member_ends = member_ends_str.split()
            
            # Récupérer les ownedEnd (les extrémités de l'association)
            owned_ends = []
            for owned_end in assoc.findall("./ownedEnd"):
                owned_ends.append({
                    'name': owned_end.get('name', ''),
                    'type': owned_end.get('type', ''),
                    'association': owned_end.get('association', '')
                })
            
            relationship_info = {
                'name': assoc_name,
                'id': assoc_id,
                'type': 'Association',
                'member_ends': member_ends,
                'owned_ends': owned_ends,
                'member_count': len(member_ends)
            }
            
            relationships.append(relationship_info)
        
        # 4. TROUVER LES ABSTRATIONS/DÉPENDANCES
        abstractions = root.findall(".//packagedElement[@xmi:type='uml:Abstraction']", namespaces)
        
        for abstr in abstractions:
            abstraction_info = {
                'name': 'Abstraction',
                'type': 'Dependency',
                'client': abstr.get('client', ''),
                'supplier': abstr.get('supplier', '')
            }
            relationships.append(abstraction_info)
        
        # 5. CALCULER LES STATISTIQUES
        total_attributes = sum(len(e['attributes']) for e in entities)
        total_class_associations = sum(len(e['associations']) for e in entities)
        
        stats = {
            'entities': len(entities),
            'attributes': total_attributes,
            'class_associations': total_class_associations,
            'global_associations': len([r for r in relationships if r['type'] == 'Association']),
            'dependencies': len([r for r in relationships if r['type'] == 'Dependency']),
            'total_elements': len(entities) + len(relationships)
        }
        
        # 6. TROUVER LE NOM DU MODÈLE
        model_name = 'Unnamed Model'
        model_elem = root.find(".//uml:Model", namespaces)
        if model_elem is not None:
            model_name = model_elem.get('name', 'Unnamed Model')
        
        return {
            'statistics': stats,
            'entities': entities,
            'relationships': relationships,
            'model_name': model_name,
            'xmi_version': root.get('{http://www.omg.org/spec/XMI/20131001}version', 
                                   root.get('xmi:version', 'Unknown'))
        }
        
    except ET.ParseError as e:
        return {
            'error': f"ParseError: {str(e)}",
            'statistics': {'error': 'XML parse failed'},
            'entities': [],
            'relationships': []
        }
    except Exception as e:
        return {
            'error': f"Erreur d'analyse: {str(e)}",
            'trace': str(e.__traceback__),
            'statistics': {'error': 'Analysis failed'},
            'entities': [],
            'relationships': []
        }

@app.route('/api/diagram/upload/xmi', methods=['POST'])
def upload_papyrus_xmi():
    """
    Upload spécifique pour les fichiers XMI de Papyrus (.xmi, .uml)
    Analyse immédiate du contenu UML
    """
    try:
        # Vérifier si un fichier est présent
        if 'xmi_file' not in request.files:
            return jsonify({
                "success": False,
                "error": "Aucun fichier XMI fourni",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        file = request.files['xmi_file']
        
        if file.filename == '':
            return jsonify({
                "success": False,
                "error": "Nom de fichier vide",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Vérifier l'extension XMI
        file_extension = os.path.splitext(file.filename)[1].lower()
        allowed_extensions = ['.xmi', '.uml', '.xml']
        
        if file_extension not in allowed_extensions:
            return jsonify({
                "success": False,
                "error": f"Extension non supportée. Seuls .xmi, .uml, .xml sont acceptés",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Lire le contenu
        file_content = file.read().decode('utf-8', errors='ignore')
        file.seek(0)  # Réinitialiser le curseur
        
        # Vérifier les signatures XMI/Papyrus
        is_papyrus_xmi = False
        xmi_signatures = [
            'xmi:version',
            'uml:Model',
            'xmlns:uml',
            'http://www.eclipse.org/uml2'
        ]
        
        for signature in xmi_signatures:
            if signature in file_content:
                is_papyrus_xmi = True
                break
        
        if not is_papyrus_xmi:
            return jsonify({
                "success": False,
                "error": "Le fichier ne semble pas être un XMI Papyrus valide",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Générer un nom unique
        unique_id = str(uuid.uuid4())[:8]
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        safe_filename = f"papyrus_{timestamp}_{unique_id}{file_extension}"
        save_path = os.path.join(UPLOAD_FOLDER, safe_filename)
        
        # Sauvegarder le fichier
        file.save(save_path)
        
        # Parser le XMI
        xmi_data = parse_papyrus_xmi_content(file_content)
        
        # Stocker les métadonnées
        diagram_data = {
            'id': unique_id,
            'filename': safe_filename,
            'original_name': file.filename,
            'path': save_path,
            'uploaded_at': datetime.now().isoformat(),
            'size': os.path.getsize(save_path),
            'extension': file_extension,
            'type': 'papyrus_xmi',
            'status': 'uploaded',
            'parsed_data': xmi_data
        }
        
        # Initialiser le stockage des diagrammes si nécessaire
        if 'xmi_diagrams' not in temporary_storage:
            temporary_storage['xmi_diagrams'] = {}
        
        temporary_storage['xmi_diagrams'][unique_id] = diagram_data
        
        return jsonify({
            "success": True,
            "message": "XMI Papyrus uploadé et analysé avec succès",
            "diagram_id": unique_id,
            "analysis": xmi_data,
            "files": {
                "xmi": safe_filename,
                "metamodel": "http://www.eclipse.org/uml2/5.0.0/UML"
            },
            "endpoints": {
                "analyze": f"/api/diagram/analyze/{unique_id}",
                "download_xmi": f"/api/diagram/download/{unique_id}",
                "generate_ecore": f"/api/diagram/to-ecore/{unique_id}"
            },
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur lors du traitement XMI: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/diagram/analyze/<diagram_id>', methods=['GET'])
def analyze_xmi_diagram(diagram_id):
    """
    Analyse détaillée d'un diagramme XMI Papyrus
    """
    try:
        if 'xmi_diagrams' not in temporary_storage or diagram_id not in temporary_storage['xmi_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme XMI {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['xmi_diagrams'][diagram_id]
        
        # Lire et parser le fichier
        with open(diagram_data['path'], 'r', encoding='utf-8', errors='ignore') as f:
            xmi_content = f.read()
        
        # Analyse complète
        analysis_result = parse_papyrus_xmi_content(xmi_content)
        
        return jsonify({
            "success": True,
            "diagram_id": diagram_id,
            "filename": diagram_data['original_name'],
            "analysis": analysis_result,
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur d'analyse: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/diagram/to-ecore/<diagram_id>', methods=['POST'])
def transform_to_ecore(diagram_id):
    """
    Transforme un XMI Papyrus UML en XMI Ecore (pour Acceleo)
    """
    try:
        # Vérifier la présence du diagramme
        if 'xmi_diagrams' not in temporary_storage or diagram_id not in temporary_storage['xmi_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['xmi_diagrams'][diagram_id]
        
        # Charger le XMI Papyrus
        with open(diagram_data['path'], 'r', encoding='utf-8', errors='ignore') as f:
            papyrus_xmi = f.read()
        
        # Transformer en Ecore XMI
        ecore_xmi = transform_papyrus_to_ecore(papyrus_xmi, diagram_id)
        
        # Sauvegarder le résultat
        ecore_filename = f"ecore_{diagram_id}.xmi"
        ecore_path = os.path.join(GENERATED_FOLDER, ecore_filename)
        
        with open(ecore_path, 'w', encoding='utf-8') as f:
            f.write(ecore_xmi)
        
        # Stocker la référence
        if 'ecore_models' not in temporary_storage:
            temporary_storage['ecore_models'] = {}
        
        temporary_storage['ecore_models'][diagram_id] = {
            'original_diagram_id': diagram_id,
            'ecore_path': ecore_path,
            'ecore_filename': ecore_filename,
            'generated_at': datetime.now().isoformat(),
            'status': 'transformed'
        }
        
        return jsonify({
            "success": True,
            "message": "Transformation UML → Ecore réussie",
            "original_diagram": diagram_id,
            "ecore_model": ecore_filename,
            "download_url": f"/api/model/download/ecore/{diagram_id}",
            "next_step": {
                "description": "Utiliser ce modèle Ecore avec Acceleo",
                "endpoint": "/api/project/generate",
                "parameters": {
                    "ecore_model": ecore_filename,
                    "config_id": "required",
                    "framework": "DJANGO|FLASK|FASTAPI"
                }
            },
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de transformation: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

def transform_papyrus_to_ecore(papyrus_xmi, diagram_id):
    """
    Transforme un XMI Papyrus en XMI Ecore
    """
    try:
        # Parser le XMI Papyrus
        root = ET.fromstring(papyrus_xmi)
        
        # Extraire les classes
        classes = []
        for cls in root.findall('.//{http://www.eclipse.org/uml2/5.0.0/UML}Class'):
            class_name = cls.get('name', 'Unnamed')
            class_id = cls.get('{http://www.omg.org/spec/XMI/20131001}id', '')
            
            attributes = []
            for attr in cls.findall('.//{http://www.eclipse.org/uml2/5.0.0/UML}Property'):
                if attr.get('association') is None:
                    attr_name = attr.get('name', '')
                    type_elem = attr.find('{http://www.eclipse.org/uml2/5.0.0/UML}type')
                    if type_elem is not None:
                        type_href = type_elem.get('href', '')
                        ecore_type = map_uml_to_ecore_type(type_href)
                        
                        attributes.append({
                            'name': attr_name,
                            'type': ecore_type,
                            'is_id': 'id' in attr_name.lower()
                        })
            
            classes.append({
                'name': class_name,
                'id': class_id,
                'attributes': attributes
            })
        
        # Générer le XML Ecore
        return generate_ecore_xmi(classes, diagram_id)
        
    except Exception as e:
        # Fallback: template basique
        return f'''<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" 
    xmlns:xmi="http://www.omg.org/XMI" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
    name="GeneratedFromPapyrus_{diagram_id}" 
    nsURI="http://www.kowihan.com/GeneratedFromPapyrus/{diagram_id}" 
    nsPrefix="gen">
    
    <!-- Generated from Papyrus UML -->
    <!-- Date: {datetime.now().isoformat()} -->
    
    <eClassifiers xsi:type="ecore:EClass" name="BaseEntity">
        <eStructuralFeatures xsi:type="ecore:EAttribute" name="id" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
        <eStructuralFeatures xsi:type="ecore:EAttribute" name="created_at" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDate"/>
        <eStructuralFeatures xsi:type="ecore:EAttribute" name="updated_at" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDate"/>
    </eClassifiers>
    
</ecore:EPackage>'''

def map_uml_to_ecore_type(uml_type_href):
    """Mappe les types UML vers les types Ecore"""
    if 'String' in uml_type_href:
        return 'EString'
    elif 'Integer' in uml_type_href or 'BigInteger' in uml_type_href:
        return 'EInt'
    elif 'Float' in uml_type_href:
        return 'EFloat'
    elif 'Boolean' in uml_type_href:
        return 'EBoolean'
    elif 'Date' in uml_type_href:
        return 'EDate'
    else:
        return 'EString'

def generate_ecore_xmi(classes, diagram_id):
    """Génère un XMI Ecore à partir des classes"""
    
    classes_xml = ""
    for cls in classes:
        class_xml = f'''
    <eClassifiers xsi:type="ecore:EClass" name="{cls['name']}">
        <eStructuralFeatures xsi:type="ecore:EAttribute" name="id" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
'''
        
        for attr in cls['attributes']:
            if attr['name'].lower() != 'id':  # On a déjà ajouté l'id
                class_xml += f'''        <eStructuralFeatures xsi:type="ecore:EAttribute" name="{attr['name']}" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//{attr['type']}"/>
'''
        
        class_xml += '''    </eClassifiers>'''
        classes_xml += class_xml
    
    ecore_template = f'''<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" 
    xmlns:xmi="http://www.omg.org/XMI" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
    name="APIModel_{diagram_id}" 
    nsURI="http://www.kowihan.com/APIModel/{diagram_id}" 
    nsPrefix="api">
    
    <!-- Generated from Papyrus UML Diagram -->
    <!-- Date: {datetime.now().isoformat()} -->
    <!-- Classes: {len(classes)} -->
    
{classes_xml}
    
</ecore:EPackage>'''
    
    return ecore_template

@app.route('/api/model/download/ecore/<diagram_id>', methods=['GET'])
def download_ecore_model(diagram_id):
    """
    Télécharge le modèle Ecore généré
    """
    try:
        if 'ecore_models' not in temporary_storage or diagram_id not in temporary_storage['ecore_models']:
            return jsonify({
                "success": False,
                "error": f"Modèle Ecore {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        ecore_data = temporary_storage['ecore_models'][diagram_id]
        
        return send_file(
            ecore_data['ecore_path'],
            as_attachment=True,
            download_name=ecore_data['ecore_filename'],
            mimetype='application/xml'
        )
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de téléchargement: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/diagram/download/<diagram_id>', methods=['GET'])
def download_xmi_diagram(diagram_id):
    """
    Télécharge le diagramme XMI original
    """
    try:
        if 'xmi_diagrams' not in temporary_storage or diagram_id not in temporary_storage['xmi_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['xmi_diagrams'][diagram_id]
        
        return send_file(
            diagram_data['path'],
            as_attachment=True,
            download_name=diagram_data['original_name'],
            mimetype='application/xml'
        )
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de téléchargement: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/test/upload', methods=['POST'])
def test_upload():
    """
    Endpoint de test spécifique pour test.uml
    """
    try:
        with open('test.uml', 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Analyser le contenu
        result = parse_papyrus_xmi_content(content)
        
        return jsonify({
            "success": True,
            "message": "Fichier test.uml analysé avec succès",
            "analysis": result,
            "file_size": len(content),
            "timestamp": datetime.now().isoformat()
        })
        
    except FileNotFoundError:
        return jsonify({
            "success": False,
            "error": "Fichier test.uml non trouvé. Placez-le dans le même dossier que app.py",
            "timestamp": datetime.now().isoformat()
        }), 404
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

def transform_papyrus_to_apimetamodel(diagram_data, config_data, sequence_diagram_ids=None):
    """
    Transforme les données Papyrus analysées en structure APIMetamodel
    pour l'envoi à Spring Boot - VERSION AVEC DEBUGGING
    """
    try:
        parsed_data = diagram_data.get('parsed_data', {})
        entities_data = parsed_data.get('entities', [])
        
        print("\n" + "="*80)
        print("🔍 DEBUGGING TRANSFORMATION PAPYRUS → APIMETAMODEL")
        print("="*80)
        
        # Transformer les entités
        entities = []
        for entity_data in entities_data:
            entity_name = entity_data.get('name', 'Unnamed')
            print(f"\n📦 Processing Entity: {entity_name}")
            print(f"   Raw attributes count: {len(entity_data.get('attributes', []))}")
            
            entity = {
                'name': entity_name,
                'tableName': entity_name.lower() + 's',
                'description': f"Entity {entity_name}",
                'isAbstract': False,
                'attributes': [],
                'relationships': []
            }
            
            # Transformer les attributs
            for idx, attr_data in enumerate(entity_data.get('attributes', [])):
                attr_name = attr_data.get('name', '').lower()
                attr_type = attr_data.get('type', 'String').upper()
                
                print(f"\n   📌 Attribute #{idx + 1}:")
                print(f"      Name: {attr_name}")
                print(f"      Raw Type: {attr_data.get('type')}")
                print(f"      Upper Type: {attr_type}")
                print(f"      is_primary_key: {attr_data.get('is_primary_key', False)}")
                
                # Mapper les types UML vers les types APIMetamodel
                type_mapping = {
                    'STRING': 'STRING',
                    'INTEGER': 'INTEGER',
                    'BIGINTEGER': 'INTEGER',
                    'FLOAT': 'FLOAT',
                    'BOOLEAN': 'BOOLEAN',
                    'DATE': 'DATE',
                    'DATETIME': 'DATETIME'
                }
                mapped_type = type_mapping.get(attr_type, 'STRING')
                
                # Calculer isNullable
                is_primary = attr_data.get('is_primary_key', False)
                is_nullable = not is_primary  # Si c'est une PK, alors NOT nullable
                
                print(f"      Mapped Type: {mapped_type}")
                print(f"      isPrimaryKey: {is_primary}")
                print(f"      isNullable: {is_nullable}")
                
                attribute = {
                    'name': attr_name,
                    'type': mapped_type,
                    'isPrimaryKey': is_primary,
                    'isNullable': is_nullable,
                    'isUnique': False
                }
                
                if mapped_type == 'STRING':
                    attribute['maxLength'] = 255
                    print(f"      maxLength: 255")
                
                entity['attributes'].append(attribute)
                print(f"       Attribute added to entity")
            
            print(f"\n    Entity '{entity_name}' processed with {len(entity['attributes'])} attributes")
            
            # Transformer les relations
            for assoc_data in entity_data.get('associations', []):
                relationship = {
                    'name': assoc_data.get('name', '').lower(),
                    'type': 'MANY_TO_ONE',
                    'targetEntity': assoc_data.get('target_class_ref', '').split('/')[-1] if assoc_data.get('target_class_ref') else '',
                    'relatedName': None,
                    'onDelete': 'CASCADE'
                }
                
                multiplicity = assoc_data.get('multiplicity', {})
                upper = multiplicity.get('upper', '1')
                if upper == '*':
                    relationship['type'] = 'MANY_TO_ONE'
                else:
                    relationship['type'] = 'ONE_TO_ONE'
                
                if relationship['targetEntity']:
                    entity['relationships'].append(relationship)
                    print(f"   🔗 Relationship added: {relationship['name']} → {relationship['targetEntity']}")
            
            entities.append(entity)
        
        print("\n" + "="*80)
        print("TRANSFORMATION SUMMARY")
        print("="*80)
        print(f"Total entities: {len(entities)}")
        for ent in entities:
            print(f"  - {ent['name']}: {len(ent['attributes'])} attributes, {len(ent['relationships'])} relationships")
        
        # Récupérer les interactions
        all_interactions = []
        
        if sequence_diagram_ids:
            if isinstance(sequence_diagram_ids, str):
                sequence_diagram_ids = [sequence_diagram_ids]
            
            for seq_id in sequence_diagram_ids:
                if 'sequence_diagrams' in temporary_storage and seq_id in temporary_storage['sequence_diagrams']:
                    sequence_data = temporary_storage['sequence_diagrams'][seq_id]
                    interactions = sequence_data.get('interactions', [])
                    print(f"[DEBUG] Added {len(interactions)} interactions from sequence diagram {seq_id}")
                    all_interactions.extend(interactions)
                else:
                    print(f"[WARNING] Sequence diagram {seq_id} not found")
        
        if not all_interactions:
            all_interactions = create_crud_interactions_from_entities(entities)
            print(f"[DEBUG] Generated {len(all_interactions)} CRUD interactions")
        
        # Construire la spécification complète
        specification = {
            'projectName': config_data.get('projectName', 'GeneratedProject'),
            'framework': config_data.get('framework', 'DJANGO'),
            'description': config_data.get('description', 'Generated from UML diagrams'),
            'pythonVersion': config_data.get('pythonVersion', '3.9'),
            'database': config_data.get('database', {
                'type': 'SQLITE',
                'host': 'localhost',
                'port': 5432,
                'name': 'app_db'
            }),
            'authentication': config_data.get('authentication', {
                'enabled': False,
                'method': 'JWT',
                'tokenExpiryMinutes': 60
            }),
            'apiFeatures': config_data.get('apiFeatures', {
                'pagination': False,
                'filtering': False,
                'swagger': True,
                'corsEnabled': True
            }),
            'entities': entities,
            'interactions': all_interactions,
            'metadata': {
                'sequence_diagrams_used': sequence_diagram_ids if sequence_diagram_ids else [],
                'entities_count': len(entities),
                'interactions_count': len(all_interactions)
            }
        }
        
        print("\n" + "="*80)
        print(" FINAL SPECIFICATION TO SPRING BOOT")
        print("="*80)
        import json
        print(json.dumps(specification, indent=2))
        print("="*80 + "\n")
        
        return specification
        
    except Exception as e:
        print(f"\nERROR in transform_papyrus_to_apimetamodel: {str(e)}")
        import traceback
        traceback.print_exc()
        raise Exception(f"Erreur lors de la transformation: {str(e)}")

@app.route('/api/generate/project', methods=['POST'])
def generate_project():
    """
    Endpoint principal pour générer un projet complet
    Peut inclure plusieurs diagrammes de séquence
    """
    try:
        data = request.json
        
        # Vérifier les paramètres requis
        diagram_id = data.get('diagram_id')
        config_id = data.get('config_id')
        sequence_diagram_ids = data.get('sequence_diagram_ids')  # Peut être une liste
        
        if not diagram_id or not config_id:
            return jsonify({
                "success": False,
                "error": "diagram_id et config_id sont requis",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Récupérer les données du diagramme
        if 'xmi_diagrams' not in temporary_storage or diagram_id not in temporary_storage['xmi_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['xmi_diagrams'][diagram_id]
        
        # Récupérer la configuration
        if config_id not in temporary_storage:
            return jsonify({
                "success": False,
                "error": f"Configuration {config_id} non trouvée",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        config_data = temporary_storage[config_id]
        
        # Vérifier que les diagrammes de séquence existent
        valid_sequence_ids = []
        if sequence_diagram_ids:
            # Convertir en liste si c'est une chaîne unique
            if isinstance(sequence_diagram_ids, str):
                sequence_diagram_ids = [sequence_diagram_ids]
            
            for seq_id in sequence_diagram_ids:
                if 'sequence_diagrams' in temporary_storage and seq_id in temporary_storage['sequence_diagrams']:
                    valid_sequence_ids.append(seq_id)
                else:
                    print(f"[WARNING] Sequence diagram {seq_id} not found, skipping")
        
        # Transformer Papyrus → APIMetamodel (avec interactions si fournies)
        specification = transform_papyrus_to_apimetamodel(diagram_data, config_data, valid_sequence_ids)
        
        print(f"[DEBUG] Generated specification with {len(specification.get('entities', []))} entities and {len(specification.get('interactions', []))} interactions")
        print(f"[DEBUG] Sequence diagrams used: {valid_sequence_ids}")
        
        # Envoyer à Spring Boot
        spring_boot_endpoint = f"{SPRING_BOOT_URL}/api/generation/generate-from-spec"
        
        try:
            response = requests.post(
                spring_boot_endpoint,
                json=specification,
                headers={'Content-Type': 'application/json'},
                timeout=300
            )
            
            if response.status_code != 200:
                return jsonify({
                    "success": False,
                    "error": f"Erreur Spring Boot: {response.text}",
                    "status_code": response.status_code,
                    "timestamp": datetime.now().isoformat()
                }), 500
            
            spring_boot_response = response.json()
            
            if not spring_boot_response.get('success'):
                return jsonify({
                    "success": False,
                    "error": spring_boot_response.get('error', 'Erreur inconnue'),
                    "timestamp": datetime.now().isoformat()
                }), 500
            
            # Télécharger le ZIP depuis Spring Boot
            zip_filename = spring_boot_response.get('zipFilePath', '').split('/')[-1]
            download_url = spring_boot_response.get('downloadUrl', '')
            
            if download_url:
                full_download_url = f"{SPRING_BOOT_URL}{download_url}"
                
                zip_response = requests.get(full_download_url, timeout=300)
                
                if zip_response.status_code == 200:
                    # Sauvegarder le ZIP localement
                    zip_path = os.path.join(GENERATED_FOLDER, zip_filename)
                    with open(zip_path, 'wb') as f:
                        f.write(zip_response.content)
                    
                    file_size = os.path.getsize(zip_path)
                    
                    return jsonify({
                        "success": True,
                        "message": "Projet généré avec succès",
                        "project_name": spring_boot_response.get('projectName'),
                        "entities_count": len(specification.get('entities', [])),
                        "interactions_count": len(specification.get('interactions', [])),
                        "sequence_diagrams_used": len(valid_sequence_ids),
                        "has_custom_interactions": len(valid_sequence_ids) > 0,
                        "download_url": f"/api/generate/download/{zip_filename}",
                        "spring_boot_download_url": full_download_url,
                        "file_size": file_size,
                        "generated_at": spring_boot_response.get('generatedAt'),
                        "specification_summary": {
                            "entities": [e['name'] for e in specification.get('entities', [])][:5],
                            "interactions": [i['name'] for i in specification.get('interactions', [])][:5]
                        },
                        "timestamp": datetime.now().isoformat()
                    })
                else:
                    return jsonify({
                        "success": False,
                        "error": f"Impossible de télécharger le ZIP depuis Spring Boot: {zip_response.status_code}",
                        "timestamp": datetime.now().isoformat()
                    }), 500
            else:
                return jsonify({
                    "success": False,
                    "error": "URL de téléchargement non fournie par Spring Boot",
                    "timestamp": datetime.now().isoformat()
                }), 500
                
        except requests.exceptions.RequestException as e:
            return jsonify({
                "success": False,
                "error": f"Erreur de communication avec Spring Boot: {str(e)}",
                "spring_boot_url": SPRING_BOOT_URL,
                "timestamp": datetime.now().isoformat()
            }), 500
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur lors de la génération: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/interactions/combine', methods=['POST'])
def combine_sequence_diagrams():
    """
    Combine plusieurs diagrammes de séquence en un seul ensemble d'interactions
    """
    try:
        data = request.json
        
        sequence_diagram_ids = data.get('sequence_diagram_ids', [])
        if not sequence_diagram_ids:
            return jsonify({
                "success": False,
                "error": "sequence_diagram_ids est requis",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Vérifier que ce sont des listes
        if isinstance(sequence_diagram_ids, str):
            sequence_diagram_ids = [sequence_diagram_ids]
        
        all_interactions = []
        valid_ids = []
        
        for seq_id in sequence_diagram_ids:
            if 'sequence_diagrams' in temporary_storage and seq_id in temporary_storage['sequence_diagrams']:
                sequence_data = temporary_storage['sequence_diagrams'][seq_id]
                interactions = sequence_data.get('interactions', [])
                all_interactions.extend(interactions)
                valid_ids.append(seq_id)
                print(f"[DEBUG] Added {len(interactions)} interactions from {seq_id}")
            else:
                print(f"[WARNING] Sequence diagram {seq_id} not found")
        
        # Créer un ID combiné
        combined_id = f"combined_{hash(''.join(valid_ids)) % 10000:04d}"
        
        # Stocker la combinaison
        if 'combined_diagrams' not in temporary_storage:
            temporary_storage['combined_diagrams'] = {}
        
        temporary_storage['combined_diagrams'][combined_id] = {
            'id': combined_id,
            'created_at': datetime.now().isoformat(),
            'source_diagrams': valid_ids,
            'interactions': all_interactions,
            'interactions_count': len(all_interactions)
        }
        
        return jsonify({
            "success": True,
            "message": f"Combined {len(valid_ids)} sequence diagrams",
            "combined_id": combined_id,
            "source_diagrams": valid_ids,
            "interactions_count": len(all_interactions),
            "interactions": [{"name": i['name'], "endpoint": i.get('endpoint')} for i in all_interactions[:10]],
            "usage": f"Use combined_id: {combined_id} in generate_project",
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur lors de la combinaison: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/generate/download/<filename>', methods=['GET'])
def download_generated_project(filename):
    """
    Télécharge un projet généré (ZIP)
    """
    try:
        zip_path = os.path.join(GENERATED_FOLDER, filename)
        
        if not os.path.exists(zip_path):
            return jsonify({
                "success": False,
                "error": f"Fichier {filename} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        return send_file(
            zip_path,
            as_attachment=True,
            download_name=filename,
            mimetype='application/zip'
        )
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de téléchargement: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/generate/spring-boot-status', methods=['GET'])
def check_spring_boot_status():
    """
    Vérifie l'état du service Spring Boot
    """
    try:
        response = requests.get(f"{SPRING_BOOT_URL}/api/generation/health", timeout=5)
        
        if response.status_code == 200:
            return jsonify({
                "success": True,
                "spring_boot_status": "UP",
                "spring_boot_url": SPRING_BOOT_URL,
                "response": response.json(),
                "timestamp": datetime.now().isoformat()
            })
        else:
            return jsonify({
                "success": False,
                "spring_boot_status": "DOWN",
                "spring_boot_url": SPRING_BOOT_URL,
                "status_code": response.status_code,
                "timestamp": datetime.now().isoformat()
            }), 503
            
    except requests.exceptions.RequestException as e:
        return jsonify({
            "success": False,
            "spring_boot_status": "UNREACHABLE",
            "spring_boot_url": SPRING_BOOT_URL,
            "error": str(e),
            "timestamp": datetime.now().isoformat()
        }), 503

def create_crud_interactions_from_entities(entities):
    """
    Crée des interactions CRUD par défaut basées sur les entités du diagramme de classes
    """
    interactions = []
    
    for entity in entities:
        entity_name = entity.get('name', 'Entity')
        entity_name_lower = entity_name.lower()
        
        # Interaction GET: Lister les entités
        interactions.append({
            'name': f'Get{entity_name}s',
            'useCaseDescription': f'Lister tous les {entity_name}s',
            'httpMethod': 'GET',
            'endpoint': f'/api/{entity_name_lower}s',
            'participants': [
                {'name': 'User', 'actorType': 'ACTOR', 'entityName': None},
                {'name': 'API', 'actorType': 'SYSTEM', 'entityName': None},
                {'name': entity_name, 'actorType': 'ENTITY', 'entityName': entity_name}
            ],
            'messages': [
                {
                    'sequenceNumber': 1,
                    'operation': f'GET /api/{entity_name_lower}s',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'User',
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 2,
                    'operation': f'query {entity_name}.objects.all()',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'API',
                    'toParticipant': entity_name
                },
                {
                    'sequenceNumber': 3,
                    'operation': f'return {entity_name_lower} list',
                    'messageType': 'REPLY',
                    'fromParticipant': entity_name,
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 4,
                    'operation': 'return JSON response',
                    'messageType': 'REPLY',
                    'fromParticipant': 'API',
                    'toParticipant': 'User'
                }
            ]
        })
        
        # Interaction POST: Créer une entité
        interactions.append({
            'name': f'Create{entity_name}',
            'useCaseDescription': f'Créer un nouveau {entity_name}',
            'httpMethod': 'POST',
            'endpoint': f'/api/{entity_name_lower}s',
            'participants': [
                {'name': 'User', 'actorType': 'ACTOR', 'entityName': None},
                {'name': 'API', 'actorType': 'SYSTEM', 'entityName': None},
                {'name': entity_name, 'actorType': 'ENTITY', 'entityName': entity_name}
            ],
            'messages': [
                {
                    'sequenceNumber': 1,
                    'operation': f'POST /api/{entity_name_lower}s avec données',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'User',
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 2,
                    'operation': 'validate and save data',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'API',
                    'toParticipant': entity_name
                },
                {
                    'sequenceNumber': 3,
                    'operation': 'return created instance',
                    'messageType': 'REPLY',
                    'fromParticipant': entity_name,
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 4,
                    'operation': 'return 201 Created',
                    'messageType': 'REPLY',
                    'fromParticipant': 'API',
                    'toParticipant': 'User'
                }
            ]
        })
        
        # Interaction GET par ID
        interactions.append({
            'name': f'Get{entity_name}ById',
            'useCaseDescription': f'Récupérer un {entity_name} par son ID',
            'httpMethod': 'GET',
            'endpoint': f'/api/{entity_name_lower}s/{{id}}',
            'participants': [
                {'name': 'User', 'actorType': 'ACTOR', 'entityName': None},
                {'name': 'API', 'actorType': 'SYSTEM', 'entityName': None},
                {'name': entity_name, 'actorType': 'ENTITY', 'entityName': entity_name}
            ],
            'messages': [
                {
                    'sequenceNumber': 1,
                    'operation': f'GET /api/{entity_name_lower}s/{{id}}',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'User',
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 2,
                    'operation': f'query {entity_name}.objects.get(id=id)',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'API',
                    'toParticipant': entity_name
                },
                {
                    'sequenceNumber': 3,
                    'operation': f'return {entity_name_lower} data',
                    'messageType': 'REPLY',
                    'fromParticipant': entity_name,
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 4,
                    'operation': 'return JSON response',
                    'messageType': 'REPLY',
                    'fromParticipant': 'API',
                    'toParticipant': 'User'
                }
            ]
        })
    
    return interactions

def extract_interactions_from_xmi(xmi_content, entities=None):
    """
    Extrait les interactions/diagrammes de séquence du XMI Papyrus
    VERSION FINALE CORRIGÉE
    """
    try:
        print("[DEBUG] ========================================")
        print("[DEBUG] Starting interaction extraction...")
        print(f"[DEBUG] XMI content length: {len(xmi_content)} bytes")
        
        # Parser le XML
        try:
            root = ET.fromstring(xmi_content)
            
            # Chercher packagedElement avec type="uml:Interaction"
            # IMPORTANT: Le tag est sans prefix, mais l'attribut xmi:type a le prefix
            found_interaction = False
            for elem in root:
                elem_type = elem.get('{http://www.omg.org/spec/XMI/20131001}type', '')
                if 'Interaction' in elem_type:
                    found_interaction = True
                    print(f"[DEBUG] ✓ Found Interaction: {elem.get('name')}")
                    break
            
            if found_interaction:
                print("[DEBUG] Calling parse_papyrus_sequence_diagram...")
                interactions = parse_papyrus_sequence_diagram(xmi_content)
                
                if interactions and len(interactions) > 0:
                    print(f"[DEBUG] ✓ Successfully parsed {len(interactions)} interaction(s)")
                    for i, interaction_data in enumerate(interactions):
                        print(f"[DEBUG]   Interaction {i+1}: {interaction_data.get('name')}")
                        print(f"[DEBUG]     Method: {interaction_data.get('httpMethod')}")
                        print(f"[DEBUG]     Endpoint: {interaction_data.get('endpoint')}")
                        print(f"[DEBUG]     Participants: {len(interaction_data.get('participants', []))}")
                        print(f"[DEBUG]     Messages: {len(interaction_data.get('messages', []))}")
                    print("[DEBUG] ========================================")
                    return interactions
                else:
                    print("[DEBUG] ✗ parse_papyrus_sequence_diagram returned empty")
            else:
                print("[DEBUG] ✗ No Interaction found in XMI")
                
        except ET.ParseError as e:
            print(f"[DEBUG] ✗ XML Parse Error: {e}")
        
        # Si pas d'interactions et on a des entités du diagramme de classes
        if entities and len(entities) > 0:
            print(f"[DEBUG] Generating CRUD for {len(entities)} entities from class diagram")
            print("[DEBUG] ========================================")
            return create_crud_interactions_from_entities(entities)
        
        # Dernier recours
        print("[WARNING] ⚠ No interactions found, returning generic placeholders")
        print("[DEBUG] ========================================")
        return [{
            'name': 'GenericCRUD',
            'useCaseDescription': 'Opérations CRUD génériques (diagramme de séquence non détecté)',
            'httpMethod': 'GET',
            'endpoint': '/api/entities',
            'participants': [
                {'name': 'User', 'actorType': 'ACTOR', 'entityName': None},
                {'name': 'API', 'actorType': 'SYSTEM', 'entityName': None}
            ],
            'messages': [
                {
                    'sequenceNumber': 1,
                    'operation': 'GET /api/entities',
                    'messageType': 'SYNCHRONOUS',
                    'fromParticipant': 'User',
                    'toParticipant': 'API'
                },
                {
                    'sequenceNumber': 2,
                    'operation': 'return data',
                    'messageType': 'REPLY',
                    'fromParticipant': 'API',
                    'toParticipant': 'User'
                }
            ]
        }]
        
    except Exception as e:
        print(f"[ERROR] ✗ Error in extract_interactions_from_xmi: {str(e)}")
        import traceback
        traceback.print_exc()
        print("[DEBUG] ========================================")
        return []

def parse_papyrus_sequence_diagram(xmi_content):
    """
    Parser Papyrus UML Sequence Diagram
    VERSION FINALE avec namespaces corrects
    """
    try:
        print("[DEBUG] --- parse_papyrus_sequence_diagram START ---")
        
        root = ET.fromstring(xmi_content)
        
        # IMPORTANT: Pas de déclaration de namespace avec find()
        
        interaction = None
        for elem in root:
            if elem.get('{http://www.omg.org/spec/XMI/20131001}type') == 'uml:Interaction':
                interaction = elem
                break
        
        if interaction is None:
            print("[DEBUG] ✗ No Interaction element found")
            return []
        
        interaction_name = interaction.get("name", "UnnamedInteraction")
        print(f"[DEBUG] ✓ Interaction name: '{interaction_name}'")
        
        lifeline_by_id = {}
        participants = []
        
        for child in interaction:
            child_type = child.get('{http://www.omg.org/spec/XMI/20131001}type', '')
            
            if child_type == 'uml:Lifeline':
                lifeline_id = child.get("{http://www.omg.org/spec/XMI/20131001}id")
                name = child.get("name", "Unnamed")
                
                if lifeline_id:
                    lifeline_by_id[lifeline_id] = name
                
                # Déterminer le type d'acteur
                name_lower = name.lower()
                if any(keyword in name_lower for keyword in ['client', 'user', 'customer']):
                    actor_type = "ACTOR"
                    entity_name = None
                elif any(keyword in name_lower for keyword in ['system', 'api', 'service', 'booking']):
                    actor_type = "SYSTEM"
                    entity_name = None
                else:
                    actor_type = "ENTITY"
                    entity_name = name
                
                participants.append({
                    "name": name,
                    "actorType": actor_type,
                    "entityName": entity_name
                })
                print(f"[DEBUG]   - Participant: {name} ({actor_type})")
        
        print(f"[DEBUG] Found {len(participants)} participant(s)")
        
       
        event_to_lifeline = {}
        
        # Parcourir tous les descendants
        for elem in interaction.iter():
            elem_type = elem.get('{http://www.omg.org/spec/XMI/20131001}type', '')
            
            if elem_type == 'uml:MessageOccurrenceSpecification':
                event_id = elem.get("{http://www.omg.org/spec/XMI/20131001}id")
                covered = elem.get("covered")
                
                if event_id and covered and covered in lifeline_by_id:
                    event_to_lifeline[event_id] = lifeline_by_id[covered]
        
        print(f"[DEBUG] Mapped {len(event_to_lifeline)} event(s) to lifelines")
        
        
        messages = []
        sequence = 1
        
        for child in interaction:
            child_type = child.get('{http://www.omg.org/spec/XMI/20131001}type', '')
            
            if child_type == 'uml:Message':
                msg_name = child.get("name", "UnnamedMessage")
                msg_sort = child.get("messageSort", "synchCall")
                
                send_event = child.get("sendEvent")
                receive_event = child.get("receiveEvent")
                
                from_participant = event_to_lifeline.get(send_event, "Unknown")
                to_participant = event_to_lifeline.get(receive_event, "Unknown")
                
                # Extraire l'opération (nom sans paramètres)
                operation = msg_name
                if "(" in msg_name and ")" in msg_name:
                    operation = msg_name.split("(")[0].strip()
                
                msg_type = map_message_type(msg_sort)
                
                messages.append({
                    "sequenceNumber": sequence,
                    "operation": operation,
                    "messageType": msg_type,
                    "fromParticipant": from_participant,
                    "toParticipant": to_participant
                })
                
                print(f"[DEBUG]   Message {sequence}: {from_participant} -> {to_participant}: {operation} ({msg_type})")
                sequence += 1
        
        print(f"[DEBUG] Found {len(messages)} message(s)")
        
        
        endpoint, http_method = determine_endpoint_from_interaction(interaction_name, messages)
        
        print(f"[DEBUG] ✓ Determined: {http_method} {endpoint}")
        
        
        interaction_data = {
            "name": interaction_name,
            "useCaseDescription": f"Interaction {interaction_name} from Papyrus UML Sequence Diagram",
            "httpMethod": http_method,
            "endpoint": endpoint,
            "participants": participants,
            "messages": messages
        }
        
        print(f"[DEBUG] --- parse_papyrus_sequence_diagram END (SUCCESS) ---")
        
        return [interaction_data]
        
    except ET.ParseError as e:
        print(f"[ERROR] ✗ XML Parse Error: {str(e)}")
        return []
    except Exception as e:
        print(f"[ERROR] ✗ Error: {str(e)}")
        import traceback
        traceback.print_exc()
        return []
def map_message_type(message_sort):
    """Mappe le type de message UML vers notre format"""
    mapping = {
        'synchCall': 'SYNCHRONOUS',
        'asynchCall': 'ASYNCHRONOUS',
        'asynchSignal': 'ASYNCHRONOUS',
        'reply': 'REPLY',
        'createMessage': 'CREATE'
    }
    return mapping.get(message_sort, 'SYNCHRONOUS')
def extract_participants_from_sequence(interaction_elem, namespaces):
    """Extrait les participants d'un diagramme de séquence"""
    participants = []
    
    # Chercher les lifelines
    lifelines = interaction_elem.findall('.//uml:Lifeline', namespaces)
    
    for lifeline in lifelines:
        participant_name = lifeline.get('name', 'Unnamed')
        
        # Déterminer le type d'acteur basé sur le nom
        actor_type = determine_actor_type_from_name(participant_name)
        
        # Déterminer l'entité associée
        entity_name = determine_entity_name_from_participant(participant_name)
        
        participants.append({
            'name': participant_name,
            'actorType': actor_type,
            'entityName': entity_name
        })
    
    return participants

def extract_messages_from_sequence(interaction_elem, namespaces):
    """Extrait les messages d'un diagramme de séquence"""
    messages = []
    
    # Chercher les messages
    message_elements = interaction_elem.findall('.//uml:Message', namespaces)
    
    sequence_counter = 1
    
    for msg_elem in message_elements:
        message_name = msg_elem.get('name', 'UnnamedMessage')
        message_sort = msg_elem.get('messageSort', 'synchCall')
        
        # Extraire l'opération et les paramètres
        operation = message_name
        parameters = []
        
        if '(' in message_name and ')' in message_name:
            operation = message_name.split('(')[0].strip()
            params_str = message_name.split('(')[1].split(')')[0]
            parameters = [p.strip() for p in params_str.split(',') if p.strip()]
        
        # Déterminer le type de message
        message_type = map_message_type(message_sort)
        
        # Trouver les participants
        send_event = msg_elem.get('sendEvent', '')
        receive_event = msg_elem.get('receiveEvent', '')
        
        from_participant = find_participant_by_event(interaction_elem, send_event, namespaces)
        to_participant = find_participant_by_event(interaction_elem, receive_event, namespaces)
        
        messages.append({
            'sequenceNumber': sequence_counter,
            'operation': operation,
            'messageType': message_type,
            'fromParticipant': from_participant,
            'toParticipant': to_participant
        })
        
        sequence_counter += 1
    
    return messages

def determine_actor_type_from_name(participant_name):
    """Détermine le type d'acteur basé sur le nom"""
    participant_lower = participant_name.lower()
    
    if 'user' in participant_lower or 'client' in participant_lower:
        return 'ACTOR'
    elif 'api' in participant_lower or 'system' in participant_lower:
        return 'SYSTEM'
    elif any(entity_word in participant_lower for entity_word in ['entity', 'model', 'object']):
        return 'ENTITY'
    else:
        return 'SYSTEM'

def determine_entity_name_from_participant(participant_name):
    """Détermine le nom de l'entité associée"""
    # Pour une approche générique, on peut utiliser le nom du participant
    # ou le dériver du contexte
    if any(keyword in participant_name.lower() for keyword in ['user', 'client', 'api']):
        return None
    else:
        # Supposer que c'est une entité
        return participant_name

def map_message_type(message_sort):
    """Mappe le type de message UML vers notre format"""
    mapping = {
        'synchCall': 'SYNCHRONOUS',
        'asynchCall': 'ASYNCHRONOUS',
        'reply': 'REPLY',
        'createMessage': 'CREATE'
    }
    return mapping.get(message_sort, 'SYNCHRONOUS')

def find_participant_by_event(interaction_elem, event_id, namespaces):
    """Trouve le participant associé à un événement"""
    if not event_id:
        return "Unknown"
    
    # Chercher l'événement
    event = interaction_elem.find(f'.//*[@xmi:id="{event_id}"]', namespaces)
    if event is None:
        return "Unknown"
    
    # Chercher la lifeline couverte
    covered_id = event.get('covered', '')
    if not covered_id:
        return "Unknown"
    
    # Trouver la lifeline
    lifeline = interaction_elem.find(f'.//uml:Lifeline[@xmi:id="{covered_id}"]', namespaces)
    if lifeline is not None:
        return lifeline.get('name', 'Unknown')
    
    return "Unknown"

def determine_endpoint_from_interaction(interaction_name, messages):
    """Détermine l'endpoint et la méthode HTTP de manière générique"""
    interaction_lower = interaction_name.lower()
    
    operation_type = 'POST'
    entity_name = 'resources'
    
    get_keywords = ['search', 'get', 'find', 'list', 'retrieve', 'fetch', 'query', 'show', 'view', 'read']
    post_keywords = ['create', 'add', 'insert', 'new', 'register', 'submit', 'save', 'book']
    put_keywords = ['update', 'modify', 'edit', 'change', 'patch']
    delete_keywords = ['delete', 'remove', 'cancel', 'destroy']
    
    # Analyser le nom de l'interaction
    if any(word in interaction_lower for word in get_keywords):
        operation_type = 'GET'
    elif any(word in interaction_lower for word in post_keywords):
        operation_type = 'POST'
    elif any(word in interaction_lower for word in put_keywords):
        operation_type = 'PUT'
    elif any(word in interaction_lower for word in delete_keywords):
        operation_type = 'DELETE'
    
    # Analyser les messages
    for message in messages:
        operation = message.get('operation', '').lower()
        
        if any(word in operation for word in get_keywords):
            operation_type = 'GET'
        elif any(word in operation for word in post_keywords):
            operation_type = 'POST'
        elif any(word in operation for word in put_keywords):
            operation_type = 'PUT'
        elif any(word in operation for word in delete_keywords):
            operation_type = 'DELETE'
    
    # Extraire le nom de l'entité
    entity_candidate = interaction_lower
    
    for keyword in get_keywords + post_keywords + put_keywords + delete_keywords:
        entity_candidate = entity_candidate.replace(keyword, '')
    
    entity_candidate = entity_candidate.strip()
    
    if entity_candidate and len(entity_candidate) > 2:
        words = [w for w in entity_candidate.split() if w.isalpha() and len(w) > 2]
        if words:
            entity_name = words[0]
            if not entity_name.endswith('s'):
                entity_name = entity_name + 's'
    else:
        clean_name = ''.join(c for c in interaction_name if c.isalnum())
        if clean_name:
            entity_name = clean_name.lower()
            if not entity_name.endswith('s'):
                entity_name = entity_name + 's'
    
    endpoint = f'/api/{entity_name}'
    
    if 'byid' in interaction_lower or 'by_id' in interaction_lower:
        endpoint = f'/api/{entity_name}/{{id}}'
    elif 'search' in interaction_lower or 'query' in interaction_lower:
        endpoint = f'/api/{entity_name}/search'
    
    return endpoint, operation_type

@app.route('/api/interactions/upload/sequence', methods=['POST'])
def upload_sequence_diagram():
    """
    Uploader un diagramme de séquence XMI
    Peut optionnellement recevoir un class_diagram_id pour utiliser ses entités
    """
    try:
        print("[DEBUG] Received sequence diagram upload request")
        
        # Récupérer les paramètres
        class_diagram_id = request.form.get('class_diagram_id')
        entities = []
        
        # Si un diagramme de classes est fourni, récupérer ses entités
        if class_diagram_id and 'xmi_diagrams' in temporary_storage and class_diagram_id in temporary_storage['xmi_diagrams']:
            class_diagram = temporary_storage['xmi_diagrams'][class_diagram_id]
            entities = class_diagram.get('parsed_data', {}).get('entities', [])
            print(f"[DEBUG] Using entities from class diagram {class_diagram_id}: {len(entities)} entities")
        
        if 'sequence_file' not in request.files:
            print("[DEBUG] No file in request")
            return jsonify({
                "success": False,
                "error": "Aucun fichier de diagramme de séquence fourni",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        file = request.files['sequence_file']
        
        if file.filename == '':
            print("[DEBUG] Empty filename")
            return jsonify({
                "success": False,
                "error": "Nom de fichier vide",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        print(f"[DEBUG] Processing file: {file.filename}")
        
        # Vérifier l'extension
        file_extension = os.path.splitext(file.filename)[1].lower()
        allowed_extensions = ['.xmi', '.uml', '.xml']
        
        if file_extension not in allowed_extensions:
            print(f"[DEBUG] Invalid extension: {file_extension}")
            return jsonify({
                "success": False,
                "error": f"Extension non supportée. Seuls {', '.join(allowed_extensions)} sont acceptés",
                "timestamp": datetime.now().isoformat()
            }), 400
        
        # Lire et analyser le contenu
        file_content = file.read().decode('utf-8', errors='ignore')
        file.seek(0)  # Réinitialiser le curseur
        
        print(f"[DEBUG] File size: {len(file_content)} bytes")
        
        # Analyser le diagramme de séquence AVEC les entités si disponibles
        interactions = extract_interactions_from_xmi(file_content, entities)
        
        # Générer un ID unique
        diagram_id = str(uuid.uuid4())[:8]
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        safe_filename = f"sequence_{timestamp}_{diagram_id}{file_extension}"
        save_path = os.path.join(UPLOAD_FOLDER, safe_filename)
        
        # Sauvegarder le fichier
        file.save(save_path)
        print(f"[DEBUG] File saved to: {save_path}")
        
        # Stocker les métadonnées
        if 'sequence_diagrams' not in temporary_storage:
            temporary_storage['sequence_diagrams'] = {}
        
        temporary_storage['sequence_diagrams'][diagram_id] = {
            'id': diagram_id,
            'filename': safe_filename,
            'original_name': file.filename,
            'path': save_path,
            'uploaded_at': datetime.now().isoformat(),
            'size': os.path.getsize(save_path),
            'extension': file_extension,
            'type': 'sequence_diagram',
            'status': 'uploaded',
            'interactions': interactions,
            'interactions_count': len(interactions),
            'class_diagram_id': class_diagram_id if class_diagram_id else None,
            'entities_count': len(entities) if entities else 0
        }
        
        # Afficher les interactions extraites
        print(f"[DEBUG] Extracted {len(interactions)} interaction(s)")
        for i, interaction in enumerate(interactions):
            print(f"[DEBUG] Interaction {i+1}: {interaction.get('name')}")
            print(f"[DEBUG]   Endpoint: {interaction.get('httpMethod')} {interaction.get('endpoint')}")
            print(f"[DEBUG]   Messages: {len(interaction.get('messages', []))}")
        
        return jsonify({
            "success": True,
            "message": "Diagramme de séquence analysé avec succès",
            "diagram_id": diagram_id,
            "interactions_count": len(interactions),
            "interactions": interactions,
            "class_diagram_linked": class_diagram_id is not None,
            "entities_used": len(entities) if entities else 0,
            "files": {
                "sequence": safe_filename
            },
            "endpoints": {
                "analyze": f"/api/interactions/analyze/{diagram_id}",
                "download": f"/api/interactions/download/{diagram_id}",
                "generate_with_classes": f"/api/generate/project-with-interactions?sequence_id={diagram_id}&class_id={class_diagram_id}" if class_diagram_id else None
            },
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        print(f"[ERROR] Error in upload_sequence_diagram: {str(e)}")
        import traceback
        traceback.print_exc()
        return jsonify({
            "success": False,
            "error": f"Erreur lors de l'analyse du diagramme de séquence: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/interactions/analyze/<diagram_id>', methods=['GET'])
def analyze_sequence_diagram(diagram_id):
    """
    Analyse détaillée d'un diagramme de séquence
    """
    try:
        print(f"[DEBUG] Analyzing sequence diagram: {diagram_id}")
        
        if 'sequence_diagrams' not in temporary_storage or diagram_id not in temporary_storage['sequence_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme de séquence {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['sequence_diagrams'][diagram_id]
        
        # Lire et re-analyser le fichier
        with open(diagram_data['path'], 'r', encoding='utf-8', errors='ignore') as f:
            xmi_content = f.read()
        
        # Extraire les interactions
        interactions = extract_interactions_from_xmi(xmi_content)
        
        return jsonify({
            "success": True,
            "diagram_id": diagram_id,
            "filename": diagram_data['original_name'],
            "interactions_count": len(interactions),
            "interactions": interactions,
            "metadata": {
                "uploaded_at": diagram_data['uploaded_at'],
                "file_size": diagram_data['size']
            },
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        print(f"[ERROR] Error analyzing sequence diagram: {str(e)}")
        return jsonify({
            "success": False,
            "error": f"Erreur d'analyse: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/interactions/download/<diagram_id>', methods=['GET'])
def download_sequence_diagram(diagram_id):
    """
    Télécharge le diagramme de séquence original
    """
    try:
        if 'sequence_diagrams' not in temporary_storage or diagram_id not in temporary_storage['sequence_diagrams']:
            return jsonify({
                "success": False,
                "error": f"Diagramme de séquence {diagram_id} non trouvé",
                "timestamp": datetime.now().isoformat()
            }), 404
        
        diagram_data = temporary_storage['sequence_diagrams'][diagram_id]
        
        return send_file(
            diagram_data['path'],
            as_attachment=True,
            download_name=diagram_data['original_name'],
            mimetype='application/xml'
        )
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de téléchargement: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/interactions/list', methods=['GET'])
def list_sequence_diagrams():
    """
    Liste tous les diagrammes de séquence uploadés
    """
    try:
        diagrams_list = []
        
        if 'sequence_diagrams' in temporary_storage:
            for diagram_id, diagram_data in temporary_storage['sequence_diagrams'].items():
                diagrams_list.append({
                    "id": diagram_id,
                    "filename": diagram_data['original_name'],
                    "uploaded_at": diagram_data['uploaded_at'],
                    "size": diagram_data['size'],
                    "interactions_count": diagram_data.get('interactions_count', 0),
                    "status": diagram_data.get('status', 'unknown')
                })
        
        return jsonify({
            "success": True,
            "count": len(diagrams_list),
            "diagrams": diagrams_list,
            "timestamp": datetime.now().isoformat()
        })
        
    except Exception as e:
        return jsonify({
            "success": False,
            "error": f"Erreur: {str(e)}",
            "timestamp": datetime.now().isoformat()
        }), 500
# ROUTES IA - Connexion vers api_generator_platform_using_IA
@app.route('/api/ia/health', methods=['GET'])
def ia_health_check():
    """Vérifie l'état du service IA"""
    try:
        response = requests.get(f"{IA_SERVICE_URL}/", timeout=5)
        if response.status_code == 200:
            return jsonify({
                "success": True,
                "ia_status": "UP",
                "ia_url": IA_SERVICE_URL,
                "response": response.json(),
                "timestamp": datetime.now().isoformat()
            })
        else:
            return jsonify({
                "success": False,
                "ia_status": "DOWN",
                "ia_url": IA_SERVICE_URL,
                "status_code": response.status_code,
                "timestamp": datetime.now().isoformat()
            }), 503
    except requests.exceptions.RequestException as e:
        return jsonify({
            "success": False,
            "ia_status": "UNREACHABLE",
            "ia_url": IA_SERVICE_URL,
            "error": str(e),
            "timestamp": datetime.now().isoformat()
        }), 503

@app.route('/api/ia/start', methods=['POST', 'OPTIONS'])  # ✅ Ajouter OPTIONS pour CORS
def ia_start_session():
    """Démarre une nouvelle session IA conversationnelle"""
    
    # ✅ Gérer la requête OPTIONS pour CORS
    if request.method == 'OPTIONS':
        return '', 200
    
    try:
        # ✅ Toujours envoyer un objet vide au service IA
        response = requests.post(
            f"{IA_SERVICE_URL}/api/start",
            json={},  # ✅ Changé: toujours envoyer {}
            headers={'Content-Type': 'application/json'},
            timeout=30
        )
        
        if response.status_code == 200:
            data = response.json()
            if data.get('success') and data.get('session_id'):
                session_id = data['session_id']
                temporary_storage[f'ia_{session_id}'] = {
                    'type': 'ia_session',
                    'session_id': session_id,
                    'created_at': datetime.now().isoformat(),
                    'status': 'active',
                    'step': data.get('step', 'objectif'), 
                    'last_update': datetime.now().isoformat()
                }
                print(f" Created IA session: ia_{session_id}")  
            return jsonify(data)
        else:
            return jsonify({
                "success": False,
                "error": f"Service IA error: {response.status_code}",
                "timestamp": datetime.now().isoformat()
            }), response.status_code
            
    except requests.exceptions.RequestException as e:
        print(f" IA Service connection error: {e}") 
        return jsonify({
            "success": False,
            "error": f"Erreur de communication avec le service IA: {str(e)}",
            "ia_url": IA_SERVICE_URL,
            "timestamp": datetime.now().isoformat()
        }), 503  

@app.route('/api/ia/process', methods=['POST'])
def ia_process_input():
    """Traite l'input utilisateur via IA"""
    try:
        response = requests.post(
            f"{IA_SERVICE_URL}/api/process",
            json=request.json,
            headers={'Content-Type': 'application/json'},
            timeout=600
        )
        
        if response.status_code == 200:
            data = response.json()
            if 'session_id' in request.json:
                session_key = f"ia_{request.json['session_id']}"
                if session_key in temporary_storage:
                    temporary_storage[session_key]['last_update'] = datetime.now().isoformat()
                    temporary_storage[session_key]['step'] = data.get('step', 'unknown')
                    if data.get('step') == 'complete':
                        temporary_storage[session_key]['status'] = 'completed'
            return jsonify(data)
        else:
            return jsonify({
                "success": False,
                "error": f"Service IA error: {response.status_code}",
                "timestamp": datetime.now().isoformat()
            }), response.status_code
    except requests.exceptions.RequestException as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de communication avec le service IA: {str(e)}",
            "ia_url": IA_SERVICE_URL,
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/ia/download/<session_id>', methods=['GET'])
def ia_download_project(session_id):
    """Télécharge le projet généré par IA"""
    try:
        response = requests.get(
            f"{IA_SERVICE_URL}/api/download/{session_id}",
            timeout=600,
            stream=True
        )
        
        if response.status_code == 200:
            zip_filename = f"ia_project_{session_id}.zip"
            zip_path = os.path.join(GENERATED_FOLDER, zip_filename)
            
            with open(zip_path, 'wb') as f:
                for chunk in response.iter_content(chunk_size=8192):
                    f.write(chunk)
            
            session_key = f"ia_{session_id}"
            if session_key in temporary_storage:
                temporary_storage[session_key]['downloaded'] = True
                temporary_storage[session_key]['zip_file'] = zip_filename
            
            return send_file(
                zip_path,
                as_attachment=True,
                download_name=zip_filename,
                mimetype='application/zip'
            )
        else:
            return jsonify({
                "success": False,
                "error": f"Projet non trouvé: {response.status_code}",
                "timestamp": datetime.now().isoformat()
            }), response.status_code
    except requests.exceptions.RequestException as e:
        return jsonify({
            "success": False,
            "error": f"Erreur de téléchargement: {str(e)}",
            "ia_url": IA_SERVICE_URL,
            "timestamp": datetime.now().isoformat()
        }), 500

@app.route('/api/compare/modes', methods=['GET'])
def compare_generation_modes():
    """Compare les deux modes de génération disponibles"""
    return jsonify({
        "success": True,
        "comparison": {
            "mde": {
                "name": "Model-Driven Engineering",
                "description": "Génération depuis diagrammes UML formels",
                "workflow": "UML Diagrams → XMI → Acceleo → Code",
                "best_for": "Projets d'entreprise, systèmes critiques",
                "endpoints": {
                    "config": "/api/config/save",
                    "upload": "/api/diagram/upload/xmi",
                    "generate": "/api/generate/project"
                }
            },
            "ia": {
                "name": "AI-Powered Generation",
                "description": "Génération conversationnelle avec agents LLM",
                "workflow": "Natural Language → AI Analysis → Code",
                "best_for": "Prototypage rapide, MVP, startups",
                "endpoints": {
                    "start": "/api/ia/start",
                    "process": "/api/ia/process",
                    "download": "/api/ia/download/<session_id>"
                }
            }
        },
        "timestamp": datetime.now().isoformat()
    })

@app.route('/api/compare/status', methods=['GET'])
def compare_services_status():
    """Vérifie l'état des deux services"""
    # Vérifier MDE (Spring Boot)
    mde_status = "unknown"
    try:
        mde_response = requests.get(f"{SPRING_BOOT_URL}/api/generation/health", timeout=3)
        mde_status = "UP" if mde_response.status_code == 200 else "DOWN"
    except:
        mde_status = "UNREACHABLE"
    
    # Vérifier IA
    ia_status = "unknown"
    try:
        ia_response = requests.get(f"{IA_SERVICE_URL}/", timeout=3)
        ia_status = "UP" if ia_response.status_code == 200 else "DOWN"
    except:
        ia_status = "UNREACHABLE"
    
    return jsonify({
        "success": True,
        "services": {
            "mde": {
                "status": mde_status,
                "url": SPRING_BOOT_URL,
                "active_configs": len([k for k in temporary_storage.keys() if not k.startswith('ia_')])
            },
            "ia": {
                "status": ia_status,
                "url": IA_SERVICE_URL,
                "active_sessions": len([k for k in temporary_storage.keys() if k.startswith('ia_')])
            }
        },
        "overall_status": "healthy" if mde_status == "UP" or ia_status == "UP" else "degraded",
        "timestamp": datetime.now().isoformat()
    })


if __name__ == '__main__':
    print(" Démarrage du serveur KowihanBackend UNIFIÉ...")
    print(f" Dossier uploads: {os.path.abspath(UPLOAD_FOLDER)}")
    print(f" Dossier généré: {os.path.abspath(GENERATED_FOLDER)}")
    print(" Serveur disponible sur: http://localhost:5000")
    app.run(host='0.0.0.0', port=5000, debug=True)