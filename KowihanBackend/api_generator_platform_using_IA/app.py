from flask import Flask, request, jsonify, send_file
from flask_cors import CORS
import os
import json
import logging
import re
from datetime import datetime
import zipfile
import io

# Import des agents LLM
from llm_agents import AgentOrchestrator

# Configuration du logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

app = Flask(__name__)
CORS(app)

class EntityParser:
    """Parser d'entités amélioré avec détection de relations et rôles"""
    
    @staticmethod
    def parse_entities(description):
        """
        Parse les entités depuis une description texte
        Format amélioré: Entity1: attr1, attr2; Entity2: attr1, attr2
        Détecte automatiquement les relations et les rôles utilisateurs
        """
        entities = []
        relations = []
        
        # Nettoyer et diviser par point-virgule
        entity_parts = [p.strip() for p in re.split(r';', description) if p.strip()]
        
        for part in entity_parts:
            if ':' not in part:
                continue
            
            # Séparer nom et attributs
            entity_name, attrs_str = part.split(':', 1)
            entity_name = entity_name.strip()
            
            # Parser les attributs
            attributes = []
            detected_relations = []
            attr_list = [a.strip() for a in attrs_str.split(',') if a.strip()]
            
            for attr in attr_list:
                attr_info = EntityParser._parse_attribute(attr, entity_name)
                if attr_info:
                    attributes.append(attr_info)
                    
                    # Détecter les relations (foreign keys)
                    if attr_info.get('is_foreign_key'):
                        detected_relations.append({
                            'from_entity': entity_name,
                            'to_entity': attr_info['related_entity'],
                            'type': attr_info['relation_type'],
                            'field': attr_info['name']
                        })
            
            if attributes:
                entity_data = {
                    'name': entity_name,
                    'attributes': attributes,
                    'methods': ['GET', 'POST', 'PUT', 'DELETE', 'PATCH'],
                    'is_user_model': EntityParser._is_user_model(entity_name, attributes),
                    'user_role': EntityParser._detect_user_role(entity_name, attributes),
                    'has_authentication': EntityParser._needs_authentication(entity_name)
                }
                entities.append(entity_data)
                relations.extend(detected_relations)
        
        # Enrichir avec les relations détectées
        entities = EntityParser._enrich_with_relations(entities, relations)
        
        # Générer les endpoints intelligents
        endpoints = EntityParser._generate_smart_endpoints(entities, relations)
        
        return {
            'entities': entities,
            'relations': relations,
            'endpoints': endpoints,
            'estimated_endpoints': len(endpoints),
            'total_entities': len(entities),
            'has_user_management': any(e['is_user_model'] for e in entities)
        }
    
    @staticmethod
    def _parse_attribute(attr_str, entity_name):
        """Parse un attribut individuel avec détection de relation"""
        # Format: "name (type)" ou "related_id" ou juste "name"
        
        # Détecter les foreign keys (ex: author_id, user_id, category_id)
        fk_match = re.match(r'(\w+)_(id|ID)', attr_str.strip())
        if fk_match:
            related_name = fk_match.group(1).lower()
            
            # Mapper author -> User (pour éviter le bug Author inexistant)
            if related_name in ['author', 'writer', 'creator', 'owner']:
                related_entity = 'User'
            else:
                related_entity = fk_match.group(1).capitalize()
            
            return {
                'name': attr_str.strip(),
                'type': 'integer',
                'is_foreign_key': True,
                'related_entity': related_entity,
                'relation_type': 'ForeignKey',
                'nullable': False
            }
        
        # Parser avec type explicite
        type_match = re.match(r'(\w+)\s*\((\w+)\)', attr_str)
        
        if type_match:
            attr_name = type_match.group(1)
            attr_type = type_match.group(2).lower()
        else:
            attr_name = attr_str.strip()
            attr_type = EntityParser._infer_type(attr_name)
        
        # Détecter si c'est un champ obligatoire ou nullable
        is_nullable = 'optional' in attr_str.lower() or 'null' in attr_str.lower()
        is_unique = 'unique' in attr_str.lower() or attr_name.lower() in ['email', 'username', 'slug']
        
        return {
            'name': attr_name,
            'type': attr_type,
            'is_foreign_key': False,
            'nullable': is_nullable,
            'unique': is_unique
        }
    
    @staticmethod
    def _infer_type(attr_name):
        """Inférer le type d'attribut basé sur le nom"""
        attr_lower = attr_name.lower()
        
        if any(kw in attr_lower for kw in ['id', '_id']):
            return 'integer'
        elif 'email' in attr_lower:
            return 'email'
        elif 'password' in attr_lower or 'pwd' in attr_lower:
            return 'password'
        elif 'url' in attr_lower or 'link' in attr_lower:
            return 'url'
        elif 'phone' in attr_lower or 'mobile' in attr_lower:
            return 'string'
        elif any(kw in attr_lower for kw in ['price', 'amount', 'total', 'cost', 'salary']):
            return 'decimal'
        elif any(kw in attr_lower for kw in ['date', 'time', 'created', 'updated', 'published']):
            return 'datetime'
        elif any(kw in attr_lower for kw in ['is_', 'has_', 'active', 'enabled', 'verified']):
            return 'boolean'
        elif any(kw in attr_lower for kw in ['count', 'number', 'quantity', 'year', 'age', 'rating']):
            return 'integer'
        elif any(kw in attr_lower for kw in ['description', 'content', 'text', 'bio', 'body']):
            return 'text'
        elif 'image' in attr_lower or 'photo' in attr_lower or 'avatar' in attr_lower:
            return 'image'
        elif 'file' in attr_lower or 'document' in attr_lower:
            return 'file'
        else:
            return 'string'
    
    @staticmethod
    def _is_user_model(entity_name, attributes):
        """Détecte si c'est un modèle utilisateur"""
        name_lower = entity_name.lower()
        if name_lower in ['user', 'users', 'account', 'profile', 'member']:
            return True
        
        # Vérifier les attributs typiques d'un user
        attr_names = [a['name'].lower() for a in attributes]
        user_indicators = ['email', 'password', 'username', 'role']
        return sum(1 for ind in user_indicators if ind in attr_names) >= 2
    
    @staticmethod
    def _detect_user_role(entity_name, attributes):
        """Détecte le rôle utilisateur (admin, customer, etc.)"""
        name_lower = entity_name.lower()
        
        role_mapping = {
            'admin': 'admin',
            'customer': 'customer',
            'client': 'customer',
            'vendor': 'vendor',
            'seller': 'vendor',
            'author': 'content_creator',
            'writer': 'content_creator',
            'moderator': 'moderator'
        }
        
        for key, role in role_mapping.items():
            if key in name_lower:
                return role
        
        # Chercher dans les attributs
        for attr in attributes:
            if attr['name'].lower() == 'role' or attr['name'].lower() == 'user_type':
                return 'multi_role'
        
        return 'standard'
    
    @staticmethod
    def _needs_authentication(entity_name):
        """Détermine si l'entité nécessite une authentification"""
        sensitive_entities = ['user', 'account', 'profile', 'order', 'payment', 'transaction']
        return any(se in entity_name.lower() for se in sensitive_entities)
    
    @staticmethod
    def _enrich_with_relations(entities, relations):
        """Enrichit les entités avec les informations de relations"""
        entity_dict = {e['name']: e for e in entities}
        
        for relation in relations:
            from_entity_name = relation['from_entity']
            to_entity_name = relation['to_entity']
            
            if from_entity_name in entity_dict:
                if 'relationships' not in entity_dict[from_entity_name]:
                    entity_dict[from_entity_name]['relationships'] = []
                entity_dict[from_entity_name]['relationships'].append(relation)
        
        return list(entity_dict.values())
    
    @staticmethod
    def _generate_smart_endpoints(entities, relations):
        """Génère des endpoints intelligents basés sur les entités et relations"""
        endpoints = []
        
        for entity in entities:
            name = entity['name']
            plural = name.lower() + 's'
            
            # Endpoints CRUD de base
            base_endpoints = [
                {
                    'method': 'GET',
                    'path': f'/api/{plural}',
                    'description': f'Liste tous les {plural}',
                    'requires_auth': entity.get('has_authentication', False),
                    'pagination': True
                },
                {
                    'method': 'POST',
                    'path': f'/api/{plural}',
                    'description': f'Crée un nouveau {name}',
                    'requires_auth': entity.get('has_authentication', False)
                },
                {
                    'method': 'GET',
                    'path': f'/api/{plural}/<id>',
                    'description': f'Récupère un {name} spécifique',
                    'requires_auth': False
                },
                {
                    'method': 'PUT',
                    'path': f'/api/{plural}/<id>',
                    'description': f'Met à jour un {name}',
                    'requires_auth': entity.get('has_authentication', False)
                },
                {
                    'method': 'PATCH',
                    'path': f'/api/{plural}/<id>',
                    'description': f'Met à jour partiellement un {name}',
                    'requires_auth': entity.get('has_authentication', False)
                },
                {
                    'method': 'DELETE',
                    'path': f'/api/{plural}/<id>',
                    'description': f'Supprime un {name}',
                    'requires_auth': True
                }
            ]
            endpoints.extend(base_endpoints)
            
            # Endpoints pour user models
            if entity.get('is_user_model'):
                auth_endpoints = [
                    {
                        'method': 'POST',
                        'path': '/api/auth/register',
                        'description': 'Inscription utilisateur',
                        'requires_auth': False
                    },
                    {
                        'method': 'POST',
                        'path': '/api/auth/login',
                        'description': 'Connexion utilisateur',
                        'requires_auth': False
                    },
                    {
                        'method': 'POST',
                        'path': '/api/auth/logout',
                        'description': 'Déconnexion utilisateur',
                        'requires_auth': True
                    },
                    {
                        'method': 'GET',
                        'path': '/api/auth/me',
                        'description': 'Profil utilisateur courant',
                        'requires_auth': True
                    },
                    {
                        'method': 'POST',
                        'path': '/api/auth/refresh',
                        'description': 'Rafraîchir le token',
                        'requires_auth': True
                    }
                ]
                endpoints.extend(auth_endpoints)
            
            # Endpoints basés sur les relations
            if 'relationships' in entity:
                for rel in entity['relationships']:
                    related = rel['to_entity']
                    related_plural = related.lower() + 's'
                    endpoints.append({
                        'method': 'GET',
                        'path': f'/api/{plural}/<id>/{related_plural}',
                        'description': f'Récupère tous les {related_plural} d\'un {name}',
                        'requires_auth': False,
                        'is_nested': True
                    })
        
        return endpoints


class SimpleCodeGenerator:
    """Générateur de code simplifié avec support Django complet"""
    
    def generate_complete_project(self, specs):
        """Génère le projet complet"""
        logger.info(f"Génération du projet avec specs: {specs.keys()}")
        
        project_name = specs.get('project_name', 'my_project')
        framework = specs.get('framework', {})
        if isinstance(framework, dict):
            framework = framework.get('type', 'Flask')
        database = specs.get('database', {})
        if isinstance(database, dict):
            database = database.get('type', 'SQLite')
        
        entities_data = specs.get('entities', {})
        entities = entities_data.get('entities', [])
        relations = entities_data.get('relations', [])
        
        logger.info(f"Entités à générer: {[e['name'] for e in entities]}")
        logger.info(f"Relations détectées: {len(relations)}")
        
        generated_files = {}
        
        # Fichiers de base
        generated_files['requirements.txt'] = self._generate_requirements(framework, database)
        generated_files['.gitignore'] = self._generate_gitignore()
        generated_files['README.md'] = self._generate_readme(project_name, specs)
        generated_files['.env.example'] = self._generate_env_example(database, framework)
        
        if framework.lower() == 'flask':
            generated_files.update(self._generate_flask_project(project_name, entities, relations, database))
        elif framework.lower() == 'django':
            generated_files.update(self._generate_django_project(project_name, entities, relations, database))
        
        logger.info(f"Projet généré avec {len(generated_files)} fichiers")
        return generated_files
    
    def _generate_requirements(self, framework, database):
        """Génère requirements.txt"""
        reqs = []
        
        if framework.lower() == 'flask':
            reqs.extend([
                'Flask==3.0.0',
                'Flask-SQLAlchemy==3.1.1',
                'Flask-CORS==4.0.0',
                'Flask-JWT-Extended==4.5.3',
                'Flask-Migrate==4.0.5',
                'python-dotenv==1.0.0',
                'email-validator==2.1.0'
            ])
        else:  # Django
            reqs.extend([
                'Django==5.0.0',
                'djangorestframework==3.14.0',
                'django-cors-headers==4.3.1',
                'djangorestframework-simplejwt==5.3.1',
                'django-filter==23.5',
                'drf-spectacular==0.27.0',
                'python-dotenv==1.0.0',
                'Pillow==10.1.0'
            ])
        
        if database.lower() == 'postgresql':
            reqs.append('psycopg2-binary==2.9.9')
        elif database.lower() == 'mysql':
            reqs.append('mysqlclient==2.2.0')
        
        return '\n'.join(reqs)
    
    def _generate_gitignore(self):
        """Génère .gitignore"""
        return """# Python
__pycache__/
*.py[cod]
*$py.class
*.so
.Python
env/
venv/
ENV/
.venv

# IDEs
.vscode/
.idea/
*.swp
*.swo

# Environment
.env
.env.local

# Database
*.db
*.sqlite3
db.sqlite3

# Django
*/migrations/
staticfiles/
media/

# Logs
*.log

# OS
.DS_Store
Thumbs.db

# Coverage
htmlcov/
.coverage
.pytest_cache/
"""
    
    def _generate_readme(self, project_name, specs):
        """Génère README.md"""
        objective = specs.get('objectif', 'API REST')
        framework = specs.get('framework', {})
        if isinstance(framework, dict):
            framework = framework.get('type', 'Flask')
        database = specs.get('database', {})
        if isinstance(database, dict):
            database = database.get('type', 'SQLite')
        
        entities_data = specs.get('entities', {})
        entities = entities_data.get('entities', [])
        relations = entities_data.get('relations', [])
        endpoints = entities_data.get('endpoints', [])
        
        entity_names = ', '.join([e['name'] for e in entities])
        
        # Générer la documentation des endpoints
        endpoints_doc = self._generate_endpoints_documentation(endpoints)
        
        # Générer la documentation des relations
        relations_doc = self._generate_relations_documentation(relations)
        
        return f"""# {project_name}

## Description
{objective}

## Technologies
- **Framework**: {framework}
- **Base de données**: {database}
- **Entités**: {entity_names}
- **Relations**: {len(relations)} relation(s) détectée(s)
- **Endpoints**: {len(endpoints)} endpoint(s) générés

## Installation

### 1. Créer un environnement virtuel:
```bash
python -m venv venv
source venv/bin/activate  # Linux/Mac
venv\\Scripts\\activate   # Windows
```

### 2. Installer les dépendances:
```bash
pip install -r requirements.txt
```

### 3. Configurer l'environnement:
```bash
cp .env.example .env
# Éditer .env avec vos paramètres
```

### 4. Initialiser la base de données:
```bash
{"flask db init" if framework.lower() == 'flask' else "python manage.py makemigrations"}
{"flask db migrate -m 'Initial migration'" if framework.lower() == 'flask' else "python manage.py migrate"}
{"flask db upgrade" if framework.lower() == 'flask' else ""}
```

### 5. {"Créer un superutilisateur (Django):" if framework.lower() == 'django' else "Lancer l'application:"}
```bash
{"python manage.py createsuperuser" if framework.lower() == 'django' else "python run.py"}
```

{"### 6. Lancer l'application:" if framework.lower() == 'django' else ""}
{"```bash" if framework.lower() == 'django' else ""}
{"python manage.py runserver" if framework.lower() == 'django' else ""}
{"```" if framework.lower() == 'django' else ""}

## Architecture

### Entités
{self._generate_entities_doc(entities)}

### Relations
{relations_doc}

## API Endpoints

{endpoints_doc}

## Structure du projet
```
{project_name}/
├── {"app/" if framework.lower() == 'flask' else f"{project_name}/"}
│   ├── models/
│   ├── {"routes/" if framework.lower() == 'flask' else "views/"}
│   ├── {"utils/" if framework.lower() == 'flask' else "serializers/"}
│   └── {"__init__.py" if framework.lower() == 'flask' else "settings.py"}
├── tests/
├── requirements.txt
├── .env.example
└── README.md
```

## Tests

```bash
{"pytest" if framework.lower() == 'flask' else "python manage.py test"}
```

## Documentation API

{f"Django Admin: http://localhost:8000/admin/" if framework.lower() == 'django' else ""}
{f"API Documentation: http://localhost:8000/api/schema/swagger-ui/" if framework.lower() == 'django' else ""}

## Sécurité

- Les mots de passe sont hashés avec bcrypt
- JWT pour l'authentification
- CORS configuré
- Variables d'environnement pour les secrets

## Licence
MIT
"""
    
    def _generate_entities_doc(self, entities):
        """Génère la documentation des entités"""
        docs = []
        for entity in entities:
            name = entity['name']
            attrs = entity.get('attributes', [])
            is_user = entity.get('is_user_model', False)
            
            docs.append(f"\n**{name}**{'⭐ (User Model)' if is_user else ''}:")
            for attr in attrs:
                attr_type = attr.get('type', 'string')
                is_fk = attr.get('is_foreign_key', False)
                fk_note = f" → {attr.get('related_entity', '')}" if is_fk else ""
                docs.append(f"- {attr['name']} ({attr_type}){fk_note}")
        
        return '\n'.join(docs) if docs else "Aucune entité définie"
    
    def _generate_relations_documentation(self, relations):
        """Génère la documentation des relations"""
        if not relations:
            return "Aucune relation détectée"
        
        docs = []
        for rel in relations:
            docs.append(f"- {rel['from_entity']} → {rel['to_entity']} ({rel['type']})")
        
        return '\n'.join(docs)
    
    def _generate_endpoints_documentation(self, endpoints):
        """Génère la documentation des endpoints"""
        if not endpoints:
            return "Aucun endpoint défini"
        
        # Grouper par ressource
        grouped = {}
        for ep in endpoints:
            # Extraire la ressource principale
            parts = ep['path'].split('/')
            resource = parts[2] if len(parts) > 2 else 'root'
            
            if resource not in grouped:
                grouped[resource] = []
            grouped[resource].append(ep)
        
        docs = []
        for resource, eps in grouped.items():
            docs.append(f"\n### {resource.upper()}")
            for ep in eps:
                auth_badge = "🔒" if ep.get('requires_auth') else "🔓"
                docs.append(f"- `{ep['method']}` `{ep['path']}` {auth_badge} - {ep['description']}")
        
        return '\n'.join(docs)
    
    def _generate_endpoints_doc(self, entities):
        """Génère la documentation des endpoints (ancienne méthode pour compatibilité)"""
        docs = []
        for entity in entities:
            name = entity['name']
            plural = name.lower() + 's'
            docs.append(f"\n### {name}")
            docs.append(f"- `GET /api/{plural}` - Liste tous les {plural}")
            docs.append(f"- `GET /api/{plural}/<id>` - Récupère un {name}")
            docs.append(f"- `POST /api/{plural}` - Crée un {name}")
            docs.append(f"- `PUT /api/{plural}/<id>` - Met à jour un {name}")
            docs.append(f"- `DELETE /api/{plural}/<id>` - Supprime un {name}")
        return '\n'.join(docs)
    
    def _generate_env_example(self, database, framework):
        """Génère .env.example"""
        if database.lower() == 'postgresql':
            db_url = 'postgresql://user:password@localhost:5432/dbname'
        elif database.lower() == 'mysql':
            db_url = 'mysql://user:password@localhost:3306/dbname'
        else:
            db_url = 'sqlite:///app.db'
        
        if framework.lower() == 'flask':
            return f"""# Application
FLASK_APP=run.py
FLASK_ENV=development
SECRET_KEY=your-secret-key-here-change-in-production

# Database
DATABASE_URL={db_url}

# JWT
JWT_SECRET_KEY=your-jwt-secret-key-here-change-in-production
JWT_ACCESS_TOKEN_EXPIRES=3600

# CORS
CORS_ORIGINS=http://localhost:3000,http://localhost:8080
"""
        else:  # Django
            return f"""# Django
DEBUG=True
SECRET_KEY=your-secret-key-here-change-in-production
ALLOWED_HOSTS=localhost,127.0.0.1

# Database
DATABASE_URL={db_url}

# CORS
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:8080

# JWT
JWT_SECRET_KEY=your-jwt-secret-key-here
ACCESS_TOKEN_LIFETIME=60
REFRESH_TOKEN_LIFETIME=1440
"""
    
    def _generate_flask_project(self, project_name, entities, relations, database):
        """Génère la structure Flask"""
        files = {}
        
        # run.py
        files['run.py'] = """from app import create_app

app = create_app()

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
"""
        
        # config.py
        files['config.py'] = f"""import os
from dotenv import load_dotenv
from datetime import timedelta

load_dotenv()

class Config:
    SECRET_KEY = os.getenv('SECRET_KEY', 'dev-secret-key-change-in-production')
    SQLALCHEMY_DATABASE_URI = os.getenv('DATABASE_URL', 'sqlite:///app.db')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    JWT_SECRET_KEY = os.getenv('JWT_SECRET_KEY', 'jwt-secret-key-change-in-production')
    JWT_ACCESS_TOKEN_EXPIRES = timedelta(hours=1)
    JWT_REFRESH_TOKEN_EXPIRES = timedelta(days=30)

class DevelopmentConfig(Config):
    DEBUG = True

class ProductionConfig(Config):
    DEBUG = False

config = {{
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'default': DevelopmentConfig
}}
"""
        
        # app/__init__.py
        files['app/__init__.py'] = """from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from flask_jwt_extended import JWTManager
from flask_migrate import Migrate
from config import config

db = SQLAlchemy()
jwt = JWTManager()
migrate = Migrate()

def create_app(config_name='default'):
    app = Flask(__name__)
    app.config.from_object(config[config_name])
    
    db.init_app(app)
    jwt.init_app(app)
    migrate.init_app(app, db)
    CORS(app)
    
    # Register blueprints
    from app.routes import register_routes
    register_routes(app)
    
    return app
"""
        
        # app/models/__init__.py
        if entities:
            model_imports = [f"from .{e['name'].lower()} import {e['name']}" for e in entities]
            files['app/models/__init__.py'] = '\n'.join(model_imports)
            
            # Générer les modèles
            for entity in entities:
                model_content = self._generate_flask_model(entity, relations)
                files[f"app/models/{entity['name'].lower()}.py"] = model_content
        else:
            files['app/models/__init__.py'] = '"""Models package"""'
        
        # app/routes/__init__.py
        if entities:
            route_imports = [f"from .{e['name'].lower()} import {e['name'].lower()}_bp" for e in entities]
            register_calls = [f"    app.register_blueprint({e['name'].lower()}_bp)" for e in entities]
            
            # Ajouter les routes d'authentification si nécessaire
            has_user = any(e.get('is_user_model') for e in entities)
            auth_import = "from .auth import auth_bp" if has_user else ""
            auth_register = "    app.register_blueprint(auth_bp)" if has_user else ""
            
            files['app/routes/__init__.py'] = f"""from flask import jsonify

{chr(10).join(route_imports)}
{auth_import}

def register_routes(app):
    @app.route('/')
    def index():
        return jsonify({{'message': 'API is running', 'status': 'ok', 'version': '1.0'}})
    
    @app.route('/health')
    def health():
        return jsonify({{'status': 'healthy'}})
    
{chr(10).join(register_calls)}
{auth_register}
"""
            
            # Générer les routes
            for entity in entities:
                route_content = self._generate_flask_route(entity, relations)
                files[f"app/routes/{entity['name'].lower()}.py"] = route_content
            
            # Générer les routes d'authentification
            if has_user:
                user_entity = next(e for e in entities if e.get('is_user_model'))
                files['app/routes/auth.py'] = self._generate_flask_auth_routes(user_entity)
        else:
            files['app/routes/__init__.py'] = """from flask import jsonify

def register_routes(app):
    @app.route('/')
    def index():
        return jsonify({'message': 'API running'})
"""
        
        # Utilitaires
        files['app/utils/__init__.py'] = ''
        files['app/utils/decorators.py'] = self._generate_flask_decorators()
        
        return files
    
    def _generate_flask_model(self, entity, relations):
        """Génère un modèle Flask-SQLAlchemy avec relations"""
        name = entity['name']
        attributes = entity.get('attributes', [])
        is_user = entity.get('is_user_model', False)
        
        # Mapping des types
        type_mapping = {
            'string': 'db.String(255)',
            'text': 'db.Text',
            'integer': 'db.Integer',
            'float': 'db.Float',
            'decimal': 'db.Numeric(10, 2)',
            'boolean': 'db.Boolean',
            'datetime': 'db.DateTime',
            'email': 'db.String(255)',
            'password': 'db.String(255)',
            'url': 'db.String(500)',
            'image': 'db.String(500)',
            'file': 'db.String(500)'
        }
        
        # Imports supplémentaires pour user model
        extra_imports = ""
        if is_user:
            extra_imports = "from werkzeug.security import generate_password_hash, check_password_hash\n"
        
        fields = ['    id = db.Column(db.Integer, primary_key=True)']
        dict_fields = ["            'id': self.id,"]
        methods = []
        
        for attr in attributes:
            attr_name = attr['name']
            attr_type = attr.get('type', 'string')
            is_fk = attr.get('is_foreign_key', False)
            is_unique = attr.get('unique', False)
            is_nullable = attr.get('nullable', True)
            
            if is_fk:
                related_entity = attr.get('related_entity', 'Entity')
                fields.append(f"    {attr_name} = db.Column(db.Integer, db.ForeignKey('{related_entity.lower()}s.id'), nullable={is_nullable})")
                
                # Ajouter la relation
                rel_name = related_entity.lower()
                fields.append(f"    {rel_name} = db.relationship('{related_entity}', backref='{name.lower()}s')")
            else:
                db_type = type_mapping.get(attr_type, 'db.String(255)')
                unique_str = f', unique={is_unique}' if is_unique else ''
                nullable_str = f', nullable={is_nullable}'
                fields.append(f"    {attr_name} = db.Column({db_type}{unique_str}{nullable_str})")
            
            # Ne pas inclure password dans to_dict
            if attr_type != 'password':
                dict_fields.append(f"            '{attr_name}': self.{attr_name},")
        
        fields.append('    created_at = db.Column(db.DateTime, default=db.func.now())')
        fields.append('    updated_at = db.Column(db.DateTime, default=db.func.now(), onupdate=db.func.now())')
        
        dict_fields.append("            'created_at': self.created_at.isoformat() if self.created_at else None,")
        dict_fields.append("            'updated_at': self.updated_at.isoformat() if self.updated_at else None")
        
        # Méthodes spéciales pour user model
        if is_user:
            methods.append("""
    def set_password(self, password):
        \"\"\"Hash et définit le mot de passe\"\"\"
        self.password = generate_password_hash(password)
    
    def check_password(self, password):
        \"\"\"Vérifie le mot de passe\"\"\"
        return check_password_hash(self.password, password)""")
        
        return f"""from app import db
{extra_imports}
from datetime import datetime

class {name}(db.Model):
    __tablename__ = '{name.lower()}s'
    
{chr(10).join(fields)}
    
    def to_dict(self):
        return {{
{chr(10).join(dict_fields)}
        }}
{"".join(methods)}
    
    def __repr__(self):
        return f'<{name} {{self.id}}>'
"""
    
    def _generate_flask_route(self, entity, relations):
        """Génère les routes Flask pour une entité avec relations"""
        name = entity['name']
        name_lower = name.lower()
        plural = name_lower + 's'
        needs_auth = entity.get('has_authentication', False)
        
        auth_import = "from flask_jwt_extended import jwt_required, get_jwt_identity\n" if needs_auth else ""
        auth_decorator = "@jwt_required()" if needs_auth else ""
        
        # Trouver les relations de cette entité
        entity_relations = [r for r in relations if r['from_entity'] == name]
        nested_routes = []
        
        for rel in entity_relations:
            related = rel['to_entity']
            related_plural = related.lower() + 's'
            nested_routes.append(f"""
@{name_lower}_bp.route('/<int:id>/{related_plural}', methods=['GET'])
def get_{name_lower}_{related_plural}(id):
    \"\"\"Récupère tous les {related_plural} d'un {name}\"\"\"
    item = {name}.query.get_or_404(id)
    related_items = {related}.query.filter_by({name_lower}_id=id).all()
    return jsonify([r.to_dict() for r in related_items]), 200
""")
        
        nested_routes_str = ''.join(nested_routes)
        
        return f"""from flask import Blueprint, request, jsonify
{auth_import}from app import db
from app.models.{name_lower} import {name}

{name_lower}_bp = Blueprint('{name_lower}', __name__, url_prefix='/api/{plural}')

@{name_lower}_bp.route('', methods=['GET'])
def get_all_{plural}():
    \"\"\"Récupère tous les {plural} avec pagination\"\"\"
    page = request.args.get('page', 1, type=int)
    per_page = request.args.get('per_page', 10, type=int)
    
    pagination = {name}.query.paginate(page=page, per_page=per_page, error_out=False)
    
    return jsonify({{
        'items': [item.to_dict() for item in pagination.items],
        'total': pagination.total,
        'pages': pagination.pages,
        'current_page': page
    }}), 200

@{name_lower}_bp.route('/<int:id>', methods=['GET'])
def get_{name_lower}(id):
    \"\"\"Récupère un {name_lower} spécifique\"\"\"
    item = {name}.query.get_or_404(id)
    return jsonify(item.to_dict()), 200

@{name_lower}_bp.route('', methods=['POST'])
{auth_decorator}
def create_{name_lower}():
    \"\"\"Crée un nouveau {name_lower}\"\"\"
    data = request.get_json()
    
    if not data:
        return jsonify({{'error': 'No data provided'}}), 400
    
    try:
        new_item = {name}(**data)
        db.session.add(new_item)
        db.session.commit()
        return jsonify(new_item.to_dict()), 201
    except Exception as e:
        db.session.rollback()
        return jsonify({{'error': str(e)}}), 400

@{name_lower}_bp.route('/<int:id>', methods=['PUT'])
{auth_decorator}
def update_{name_lower}(id):
    \"\"\"Met à jour un {name_lower}\"\"\"
    item = {name}.query.get_or_404(id)
    data = request.get_json()
    
    if not data:
        return jsonify({{'error': 'No data provided'}}), 400
    
    try:
        for key, value in data.items():
            if hasattr(item, key) and key != 'id':
                setattr(item, key, value)
        db.session.commit()
        return jsonify(item.to_dict()), 200
    except Exception as e:
        db.session.rollback()
        return jsonify({{'error': str(e)}}), 400

@{name_lower}_bp.route('/<int:id>', methods=['PATCH'])
{auth_decorator}
def patch_{name_lower}(id):
    \"\"\"Met à jour partiellement un {name_lower}\"\"\"
    item = {name}.query.get_or_404(id)
    data = request.get_json()
    
    if not data:
        return jsonify({{'error': 'No data provided'}}), 400
    
    try:
        for key, value in data.items():
            if hasattr(item, key) and key != 'id':
                setattr(item, key, value)
        db.session.commit()
        return jsonify(item.to_dict()), 200
    except Exception as e:
        db.session.rollback()
        return jsonify({{'error': str(e)}}), 400

@{name_lower}_bp.route('/<int:id>', methods=['DELETE'])
{auth_decorator}
def delete_{name_lower}(id):
    \"\"\"Supprime un {name_lower}\"\"\"
    item = {name}.query.get_or_404(id)
    
    try:
        db.session.delete(item)
        db.session.commit()
        return jsonify({{'message': '{name} deleted successfully'}}), 200
    except Exception as e:
        db.session.rollback()
        return jsonify({{'error': str(e)}}), 400
{nested_routes_str}
"""
    
    def _generate_flask_auth_routes(self, user_entity):
        """Génère les routes d'authentification Flask"""
        user_name = user_entity['name']
        
        return f"""from flask import Blueprint, request, jsonify
from flask_jwt_extended import create_access_token, create_refresh_token, jwt_required, get_jwt_identity
from app import db
from app.models.{user_name.lower()} import {user_name}

auth_bp = Blueprint('auth', __name__, url_prefix='/api/auth')

@auth_bp.route('/register', methods=['POST'])
def register():
    \"\"\"Inscription d'un nouvel utilisateur\"\"\"
    data = request.get_json()
    
    if not data or not data.get('email') or not data.get('password'):
        return jsonify({{'error': 'Email and password required'}}), 400
    
    # Vérifier si l'utilisateur existe déjà
    if {user_name}.query.filter_by(email=data['email']).first():
        return jsonify({{'error': 'User already exists'}}), 409
    
    try:
        user = {user_name}()
        user.email = data['email']
        
        # Si le modèle a un champ username
        if hasattr(user, 'username') and 'username' in data:
            user.username = data['username']
        
        # Hasher le mot de passe
        user.set_password(data['password'])
        
        db.session.add(user)
        db.session.commit()
        
        return jsonify({{'message': 'User created successfully', 'user': user.to_dict()}}), 201
    except Exception as e:
        db.session.rollback()
        return jsonify({{'error': str(e)}}), 400

@auth_bp.route('/login', methods=['POST'])
def login():
    \"\"\"Connexion d'un utilisateur\"\"\"
    data = request.get_json()
    
    if not data or not data.get('email') or not data.get('password'):
        return jsonify({{'error': 'Email and password required'}}), 400
    
    user = {user_name}.query.filter_by(email=data['email']).first()
    
    if not user or not user.check_password(data['password']):
        return jsonify({{'error': 'Invalid credentials'}}), 401
    
    access_token = create_access_token(identity=user.id)
    refresh_token = create_refresh_token(identity=user.id)
    
    return jsonify({{
        'access_token': access_token,
        'refresh_token': refresh_token,
        'user': user.to_dict()
    }}), 200

@auth_bp.route('/me', methods=['GET'])
@jwt_required()
def get_current_user():
    \"\"\"Récupère le profil de l'utilisateur courant\"\"\"
    user_id = get_jwt_identity()
    user = {user_name}.query.get_or_404(user_id)
    return jsonify(user.to_dict()), 200

@auth_bp.route('/refresh', methods=['POST'])
@jwt_required(refresh=True)
def refresh():
    \"\"\"Rafraîchit le token d'accès\"\"\"
    user_id = get_jwt_identity()
    access_token = create_access_token(identity=user_id)
    return jsonify({{'access_token': access_token}}), 200

@auth_bp.route('/logout', methods=['POST'])
@jwt_required()
def logout():
    \"\"\"Déconnexion (côté client, suppression du token)\"\"\"
    return jsonify({{'message': 'Logged out successfully'}}), 200
"""
    
    def _generate_flask_decorators(self):
        """Génère les décorateurs utilitaires"""
        return """from functools import wraps
from flask import jsonify
from flask_jwt_extended import verify_jwt_in_request, get_jwt_identity

def admin_required(fn):
    @wraps(fn)
    def wrapper(*args, **kwargs):
        verify_jwt_in_request()
        user_id = get_jwt_identity()
        # Ajouter logique de vérification admin ici
        return fn(*args, **kwargs)
    return wrapper

def validate_json(*required_fields):
    def decorator(fn):
        @wraps(fn)
        def wrapper(*args, **kwargs):
            from flask import request
            data = request.get_json()
            if not data:
                return jsonify({'error': 'No data provided'}), 400
            
            missing = [field for field in required_fields if field not in data]
            if missing:
                return jsonify({'error': f'Missing fields: {", ".join(missing)}'}), 400
            
            return fn(*args, **kwargs)
        return wrapper
    return decorator
"""
    
    def _generate_django_project(self, project_name, entities, relations, database):
        """Génère la structure Django complète"""
        files = {}
        
        # manage.py
        files['manage.py'] = f"""#!/usr/bin/env python
import os
import sys

if __name__ == '__main__':
    os.environ.setdefault('DJANGO_SETTINGS_MODULE', '{project_name}.settings')
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc:
        raise ImportError(
            "Couldn't import Django. Are you sure it's installed?"
        ) from exc
    execute_from_command_line(sys.argv)
"""
        
        # project_name/__init__.py
        files[f'{project_name}/__init__.py'] = ''
        
        # project_name/settings.py
        files[f'{project_name}/settings.py'] = self._generate_django_settings(project_name, database, entities)
        
        # project_name/urls.py
        files[f'{project_name}/urls.py'] = self._generate_django_urls(project_name)
        
        # project_name/wsgi.py
        files[f'{project_name}/wsgi.py'] = f"""import os
from django.core.wsgi import get_wsgi_application

os.environ.setdefault('DJANGO_SETTINGS_MODULE', '{project_name}.settings')
application = get_wsgi_application()
"""
        
        # project_name/asgi.py
        files[f'{project_name}/asgi.py'] = f"""import os
from django.core.asgi import get_asgi_application

os.environ.setdefault('DJANGO_SETTINGS_MODULE', '{project_name}.settings')
application = get_asgi_application()
"""
        
        # App principale
        app_name = 'api'
        files[f'{app_name}/__init__.py'] = ''
        files[f'{app_name}/apps.py'] = f"""from django.apps import AppConfig

class ApiConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = '{app_name}'
"""
        
        # Models Django
        if entities:
            files[f'{app_name}/models.py'] = self._generate_django_models(entities, relations)
        else:
            files[f'{app_name}/models.py'] = 'from django.db import models\n'
        
        # Serializers
        if entities:
            files[f'{app_name}/serializers.py'] = self._generate_django_serializers(entities)
        else:
            files[f'{app_name}/serializers.py'] = 'from rest_framework import serializers\n'
        
        # Views
        if entities:
            files[f'{app_name}/views.py'] = self._generate_django_views(entities)
        else:
            files[f'{app_name}/views.py'] = 'from rest_framework import viewsets\n'
        
        # URLs de l'app
        if entities:
            files[f'{app_name}/urls.py'] = self._generate_django_app_urls(entities)
        else:
            files[f'{app_name}/urls.py'] = 'from django.urls import path\n\nurlpatterns = []\n'
        
        # Admin
        if entities:
            files[f'{app_name}/admin.py'] = self._generate_django_admin(entities)
        else:
            files[f'{app_name}/admin.py'] = 'from django.contrib import admin\n'
        
        # Tests
        files[f'{app_name}/tests.py'] = """from django.test import TestCase
from rest_framework.test import APITestCase

# Create your tests here.
"""
        
        return files
    
    def _generate_django_settings(self, project_name, database, entities):
        """Génère les settings Django"""
        
        # Configuration de la base de données
        if database.lower() == 'postgresql':
            db_config = """DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': os.getenv('DB_NAME', 'dbname'),
        'USER': os.getenv('DB_USER', 'user'),
        'PASSWORD': os.getenv('DB_PASSWORD', 'password'),
        'HOST': os.getenv('DB_HOST', 'localhost'),
        'PORT': os.getenv('DB_PORT', '5432'),
    }
}"""
        elif database.lower() == 'mysql':
            db_config = """DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': os.getenv('DB_NAME', 'dbname'),
        'USER': os.getenv('DB_USER', 'user'),
        'PASSWORD': os.getenv('DB_PASSWORD', 'password'),
        'HOST': os.getenv('DB_HOST', 'localhost'),
        'PORT': os.getenv('DB_PORT', '3306'),
    }
}"""
        else:  # SQLite
            db_config = """DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}"""
        
        # Déterminer si on a besoin de l'authentification JWT
        has_user = any(e.get('is_user_model') for e in entities)
        
        return f"""import os
from pathlib import Path
from dotenv import load_dotenv
from datetime import timedelta

load_dotenv()

BASE_DIR = Path(__file__).resolve().parent.parent

SECRET_KEY = os.getenv('SECRET_KEY', 'django-insecure-change-this-in-production')

DEBUG = os.getenv('DEBUG', 'True') == 'True'

ALLOWED_HOSTS = os.getenv('ALLOWED_HOSTS', 'localhost,127.0.0.1').split(',')

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    
    # Third party apps
    'rest_framework',
    'corsheaders',
    'django_filters',
    'drf_spectacular',
    
    # Local apps
    'api',
]

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'corsheaders.middleware.CorsMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = '{project_name}.urls'

TEMPLATES = [
    {{
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'APP_DIRS': True,
        'OPTIONS': {{
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        }},
    }},
]

WSGI_APPLICATION = '{project_name}.wsgi.application'

{db_config}

AUTH_PASSWORD_VALIDATORS = [
    {{'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator'}},
    {{'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator'}},
    {{'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator'}},
    {{'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator'}},
]

LANGUAGE_CODE = 'fr-fr'
TIME_ZONE = 'UTC'
USE_I18N = True
USE_TZ = True

STATIC_URL = 'static/'
STATIC_ROOT = BASE_DIR / 'staticfiles'

MEDIA_URL = 'media/'
MEDIA_ROOT = BASE_DIR / 'media'

DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'

# REST Framework
REST_FRAMEWORK = {{
    'DEFAULT_PERMISSION_CLASSES': [
        'rest_framework.permissions.{"IsAuthenticatedOrReadOnly" if has_user else "AllowAny"}',
    ],
    'DEFAULT_AUTHENTICATION_CLASSES': [
        {"'rest_framework_simplejwt.authentication.JWTAuthentication'," if has_user else ''}
        'rest_framework.authentication.SessionAuthentication',
    ],
    'DEFAULT_PAGINATION_CLASS': 'rest_framework.pagination.PageNumberPagination',
    'PAGE_SIZE': 10,
    'DEFAULT_FILTER_BACKENDS': [
        'django_filters.rest_framework.DjangoFilterBackend',
        'rest_framework.filters.SearchFilter',
        'rest_framework.filters.OrderingFilter',
    ],
    'DEFAULT_SCHEMA_CLASS': 'drf_spectacular.openapi.AutoSchema',
}}

# CORS
CORS_ALLOWED_ORIGINS = os.getenv('CORS_ALLOWED_ORIGINS', 'http://localhost:3000,http://localhost:8080').split(',')
CORS_ALLOW_CREDENTIALS = True

# JWT Settings{' (if user authentication)' if has_user else ''}
{'''SIMPLE_JWT = {
    'ACCESS_TOKEN_LIFETIME': timedelta(minutes=int(os.getenv('ACCESS_TOKEN_LIFETIME', 60))),
    'REFRESH_TOKEN_LIFETIME': timedelta(days=int(os.getenv('REFRESH_TOKEN_LIFETIME', 1))),
    'ROTATE_REFRESH_TOKENS': False,
    'BLACKLIST_AFTER_ROTATION': True,
}''' if has_user else ''}

# Spectacular Settings (API Documentation)
SPECTACULAR_SETTINGS = {{
    'TITLE': '{project_name} API',
    'DESCRIPTION': 'API REST pour {project_name}',
    'VERSION': '1.0.0',
    'SERVE_INCLUDE_SCHEMA': False,
}}
"""
    
    def _generate_django_urls(self, project_name):
        """Génère les URLs principales Django"""
        return f"""from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView, SpectacularRedocView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('api.urls')),
    
    # API Schema
    path('api/schema/', SpectacularAPIView.as_view(), name='schema'),
    path('api/schema/swagger-ui/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger-ui'),
    path('api/schema/redoc/', SpectacularRedocView.as_view(url_name='schema'), name='redoc'),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
"""
    
    def _generate_django_models(self, entities, relations):
        """Génère les models Django avec relations"""
        
        # Mapping des types
        type_mapping = {
            'string': 'models.CharField(max_length=255)',
            'text': 'models.TextField()',
            'integer': 'models.IntegerField()',
            'float': 'models.FloatField()',
            'decimal': 'models.DecimalField(max_digits=10, decimal_places=2)',
            'boolean': 'models.BooleanField(default=False)',
            'datetime': 'models.DateTimeField()',
            'email': 'models.EmailField(unique=True)',
            'password': 'models.CharField(max_length=255)',
            'url': 'models.URLField(max_length=500)',
            'image': 'models.ImageField(upload_to="images/")',
            'file': 'models.FileField(upload_to="files/")'
        }
        
        imports = ["from django.db import models"]
        has_user_model = any(e.get('is_user_model') for e in entities)
        
        if has_user_model:
            imports.append("from django.contrib.auth.hashers import make_password, check_password")
        
        models_code = ['\n'.join(imports), '\n']
        
        for entity in entities:
            name = entity['name']
            attributes = entity.get('attributes', [])
            is_user = entity.get('is_user_model', False)
            
            # Début de la classe
            models_code.append(f"\nclass {name}(models.Model):")
            models_code.append(f'    """Model pour {name}"""')
            
            # Champs
            for attr in attributes:
                attr_name = attr['name']
                attr_type = attr.get('type', 'string')
                is_fk = attr.get('is_foreign_key', False)
                is_nullable = attr.get('nullable', True)
                is_unique = attr.get('unique', False)
                
                if is_fk:
                    related_entity = attr.get('related_entity', 'Entity')
                    models_code.append(f"    {attr_name} = models.ForeignKey(")
                    models_code.append(f"        '{related_entity}',")
                    models_code.append(f"        on_delete=models.CASCADE,")
                    models_code.append(f"        related_name='{name.lower()}s',")
                    models_code.append(f"        null={is_nullable}")
                    models_code.append(f"    )")
                else:
                    field_def = type_mapping.get(attr_type, 'models.CharField(max_length=255)')
                    
                    # Ajouter null et unique si nécessaire
                    if is_nullable and 'null=' not in field_def:
                        field_def = field_def.rstrip(')') + ', null=True, blank=True)'
                    if is_unique and 'unique=' not in field_def and attr_type != 'email':
                        field_def = field_def.rstrip(')') + ', unique=True)'
                    
                    models_code.append(f"    {attr_name} = {field_def}")
            
            # Timestamps
            models_code.append("    created_at = models.DateTimeField(auto_now_add=True)")
            models_code.append("    updated_at = models.DateTimeField(auto_now=True)")
            
            # Meta
            models_code.append("\n    class Meta:")
            models_code.append(f"        db_table = '{name.lower()}s'")
            models_code.append(f"        verbose_name = '{name}'")
            models_code.append(f"        verbose_name_plural = '{name}s'")
            models_code.append("        ordering = ['-created_at']")
            
            # Méthodes
            models_code.append("\n    def __str__(self):")
            # Essayer de trouver un champ de nom
            name_field = next((a['name'] for a in attributes if 'name' in a['name'].lower() or 'title' in a['name'].lower()), None)
            if name_field:
                models_code.append(f"        return self.{name_field}")
            else:
                models_code.append(f"        return f'{name} {{self.id}}'")
            
            # Méthodes spéciales pour user model
            if is_user:
                models_code.append("\n    def set_password(self, raw_password):")
                models_code.append("        \"\"\"Hash et définit le mot de passe\"\"\"")
                models_code.append("        self.password = make_password(raw_password)")
                
                models_code.append("\n    def check_password(self, raw_password):")
                models_code.append("        \"\"\"Vérifie le mot de passe\"\"\"")
                models_code.append("        return check_password(raw_password, self.password)")
        
        return '\n'.join(models_code)
    
    def _generate_django_serializers(self, entities):
        """Génère les serializers Django REST Framework"""
        code = ["from rest_framework import serializers"]
        code.append("from .models import " + ', '.join([e['name'] for e in entities]))
        code.append("")
        
        for entity in entities:
            name = entity['name']
            attributes = entity.get('attributes', [])
            is_user = entity.get('is_user_model', False)
            
            code.append(f"\nclass {name}Serializer(serializers.ModelSerializer):")
            code.append(f'    """Serializer pour {name}"""')
            
            # Champs spéciaux pour user
            if is_user:
                code.append("    password = serializers.CharField(write_only=True, required=False)")
            
            code.append("\n    class Meta:")
            code.append(f"        model = {name}")
            code.append(f"        fields = '__all__'")
            code.append(f"        read_only_fields = ('id', 'created_at', 'updated_at')")
            
            # Write-only pour password
            if is_user:
                password_field = next((a['name'] for a in attributes if a.get('type') == 'password'), None)
                if password_field:
                    code.append(f"        extra_kwargs = {{'{password_field}': {{'write_only': True}}}}")
            
            # Méthode create pour hasher le password
            if is_user:
                code.append("\n    def create(self, validated_data):")
                code.append("        password = validated_data.pop('password', None)")
                code.append(f"        instance = {name}.objects.create(**validated_data)")
                code.append("        if password:")
                code.append("            instance.set_password(password)")
                code.append("            instance.save()")
                code.append("        return instance")
                
                code.append("\n    def update(self, instance, validated_data):")
                code.append("        password = validated_data.pop('password', None)")
                code.append("        for attr, value in validated_data.items():")
                code.append("            setattr(instance, attr, value)")
                code.append("        if password:")
                code.append("            instance.set_password(password)")
                code.append("        instance.save()")
                code.append("        return instance")
        
        return '\n'.join(code)
    
    def _generate_django_views(self, entities):
        """Génère les views Django REST Framework"""
        code = ["from rest_framework import viewsets, status, filters"]
        code.append("from rest_framework.decorators import action")
        code.append("from rest_framework.response import Response")
        code.append("from rest_framework.permissions import IsAuthenticatedOrReadOnly, IsAuthenticated, AllowAny")
        code.append("from django_filters.rest_framework import DjangoFilterBackend")
        
        # Imports pour JWT si nécessaire
        has_user = any(e.get('is_user_model') for e in entities)
        if has_user:
            code.append("from rest_framework_simplejwt.tokens import RefreshToken")
        
        code.append("\nfrom .models import " + ', '.join([e['name'] for e in entities]))
        code.append("from .serializers import " + ', '.join([f"{e['name']}Serializer" for e in entities]))
        code.append("")
        
        for entity in entities:
            name = entity['name']
            is_user = entity.get('is_user_model', False)
            needs_auth = entity.get('has_authentication', False)
            
            permission = 'IsAuthenticated' if needs_auth else 'IsAuthenticatedOrReadOnly'
            
            code.append(f"\nclass {name}ViewSet(viewsets.ModelViewSet):")
            code.append(f'    """ViewSet pour {name}"""')
            code.append(f"    queryset = {name}.objects.all()")
            code.append(f"    serializer_class = {name}Serializer")
            code.append(f"    permission_classes = [{permission}]")
            code.append("    filter_backends = [DjangoFilterBackend, filters.SearchFilter, filters.OrderingFilter]")
            
            # Déterminer les champs de recherche
            search_fields = []
            for attr in entity.get('attributes', []):
                attr_type = attr.get('type', 'string')
                if attr_type in ['string', 'text', 'email']:
                    search_fields.append(attr['name'])
            
            if search_fields:
                code.append(f"    search_fields = {search_fields[:3]}")  # Limiter à 3 champs
                code.append(f"    ordering_fields = {search_fields[:2] + ['created_at']}")
            
            # Actions personnalisées pour user model
            if is_user:
                code.append("\n    @action(detail=False, methods=['post'], permission_classes=[AllowAny])")
                code.append("    def register(self, request):")
                code.append("        \"\"\"Inscription d'un nouvel utilisateur\"\"\"")
                code.append("        serializer = self.get_serializer(data=request.data)")
                code.append("        serializer.is_valid(raise_exception=True)")
                code.append("        user = serializer.save()")
                code.append("        ")
                code.append("        refresh = RefreshToken.for_user(user)")
                code.append("        return Response({")
                code.append("            'user': serializer.data,")
                code.append("            'refresh': str(refresh),")
                code.append("            'access': str(refresh.access_token),")
                code.append("        }, status=status.HTTP_201_CREATED)")
                
                code.append("\n    @action(detail=False, methods=['post'], permission_classes=[AllowAny])")
                code.append("    def login(self, request):")
                code.append("        \"\"\"Connexion d'un utilisateur\"\"\"")
                code.append("        email = request.data.get('email')")
                code.append("        password = request.data.get('password')")
                code.append("        ")
                code.append("        try:")
                code.append(f"            user = {name}.objects.get(email=email)")
                code.append("        except " + name + ".DoesNotExist:")
                code.append("            return Response({'error': 'Invalid credentials'}, status=status.HTTP_401_UNAUTHORIZED)")
                code.append("        ")
                code.append("        if not user.check_password(password):")
                code.append("            return Response({'error': 'Invalid credentials'}, status=status.HTTP_401_UNAUTHORIZED)")
                code.append("        ")
                code.append("        refresh = RefreshToken.for_user(user)")
                code.append(f"        serializer = {name}Serializer(user)")
                code.append("        ")
                code.append("        return Response({")
                code.append("            'user': serializer.data,")
                code.append("            'refresh': str(refresh),")
                code.append("            'access': str(refresh.access_token),")
                code.append("        })")
                
                code.append("\n    @action(detail=False, methods=['get'], permission_classes=[IsAuthenticated])")
                code.append("    def me(self, request):")
                code.append("        \"\"\"Récupère le profil de l'utilisateur courant\"\"\"")
                code.append("        serializer = self.get_serializer(request.user)")
                code.append("        return Response(serializer.data)")
        
        return '\n'.join(code)
    
    def _generate_django_app_urls(self, entities):
        """Génère les URLs de l'app Django"""
        code = ["from django.urls import path, include"]
        code.append("from rest_framework.routers import DefaultRouter")
        code.append("from . import views")
        code.append("")
        code.append("router = DefaultRouter()")
        
        for entity in entities:
            name = entity['name']
            plural = name.lower() + 's'
            code.append(f"router.register(r'{plural}', views.{name}ViewSet)")
        
        code.append("")
        code.append("urlpatterns = [")
        code.append("    path('', include(router.urls)),")
        code.append("]")
        
        return '\n'.join(code)
    
    def _generate_django_admin(self, entities):
        """Génère le fichier admin Django"""
        code = ["from django.contrib import admin"]
        code.append("from .models import " + ', '.join([e['name'] for e in entities]))
        code.append("")
        
        for entity in entities:
            name = entity['name']
            attributes = entity.get('attributes', [])
            
            # Déterminer les champs à afficher
            list_display = ['id']
            search_fields = []
            list_filter = []
            
            for attr in attributes[:5]:  # Limiter à 5 champs
                attr_name = attr['name']
                attr_type = attr.get('type', 'string')
                
                list_display.append(attr_name)
                
                if attr_type in ['string', 'text', 'email']:
                    search_fields.append(attr_name)
                elif attr_type == 'boolean':
                    list_filter.append(attr_name)
            
            list_display.extend(['created_at', 'updated_at'])
            list_filter.append('created_at')
            
            code.append(f"\n@admin.register({name})")
            code.append(f"class {name}Admin(admin.ModelAdmin):")
            code.append(f"    list_display = {list_display}")
            if search_fields:
                code.append(f"    search_fields = {search_fields}")
            code.append(f"    list_filter = {list_filter}")
            code.append(f"    date_hierarchy = 'created_at'")
            code.append(f"    ordering = ['-created_at']")
        
        return '\n'.join(code)


class AgenticRAGGenerator:
    """Générateur principal avec conversation et agents LLM"""
    
    def __init__(self):
        self.code_generator = SimpleCodeGenerator()
        self.entity_parser = EntityParser()
        self.sessions = {}
        self.agent_orchestrator = AgentOrchestrator()  # 🤖 Agents LLM
        logger.info("✅ Agents LLM initialisés")
    
    def start_new_session(self):
        """Démarre une nouvelle session"""
        session_id = f"session_{datetime.now().strftime('%Y%m%d_%H%M%S_%f')}"
        self.sessions[session_id] = {
            'step': 'objectif',
            'specifications': {},
            'confirmed': False,
            'created_at': datetime.now().isoformat()
        }
        logger.info(f"Nouvelle session: {session_id}")
        return session_id
    
    def process_user_input(self, session_id, user_input):
        """Traite l'input utilisateur"""
        if session_id not in self.sessions:
            return {"error": "Session non trouvée", "code": "SESSION_NOT_FOUND"}
        
        session = self.sessions[session_id]
        current_step = session['step']
        
        logger.info(f"Étape {current_step}: {user_input[:50]}...")
        
        handlers = {
            'objectif': self._process_objectif,
            'database': self._process_database,
            'framework': self._process_framework,
            'entities': self._process_entities,
            'confirmation': self._process_confirmation
        }
        
        handler = handlers.get(current_step)
        if not handler:
            return {"error": "Étape inconnue"}
        
        try:
            return handler(session, user_input)
        except Exception as e:
            logger.error(f"Erreur: {e}", exc_info=True)
            return {"error": f"Erreur: {str(e)}"}
    
    def _process_objectif(self, session, objectif):
        """Traite l'objectif"""
        if len(objectif.strip()) < 10:
            return {
                "step": "objectif",
                "error": "Objectif trop court (minimum 10 caractères)"
            }
        
        # Analyser la complexité
        complexity = 'medium'
        if any(word in objectif.lower() for word in ['simple', 'basique', 'petit']):
            complexity = 'low'
        elif any(word in objectif.lower() for word in ['complexe', 'avancé', 'gros']):
            complexity = 'high'
        
        # Suggérer un nom
        words = re.findall(r'\b\w+\b', objectif.lower())
        project_name = '_'.join(words[:3]) if len(words) >= 3 else 'my_project'
        
        session['specifications']['objectif'] = objectif.strip()
        session['specifications']['complexity'] = complexity
        session['specifications']['project_name'] = project_name
        session['step'] = 'database'
        
        return {
            "step": "database",
            "message": "✅ Objectif enregistré! Quelle base de données souhaitez-vous utiliser?",
            "suggestions": ["PostgreSQL", "MySQL", "SQLite", "MongoDB"],
            "current_specs": session['specifications']
        }
    
    def _process_database(self, session, database_choice):
        """Traite le choix de base de données"""
        valid_dbs = {
            'postgresql': 'PostgreSQL',
            'postgres': 'PostgreSQL',
            'mysql': 'MySQL',
            'sqlite': 'SQLite',
            'mongodb': 'MongoDB',
            'mongo': 'MongoDB'
        }
        
        db_lower = database_choice.lower().strip()
        matched_db = None
        
        for key, value in valid_dbs.items():
            if key in db_lower:
                matched_db = value
                break
        
        if not matched_db:
            return {
                "step": "database",
                "error": f"Base de données non reconnue. Choisissez parmi: {', '.join(set(valid_dbs.values()))}",
                "suggestions": ["PostgreSQL", "MySQL", "SQLite", "MongoDB"]
            }
        
        session['specifications']['database'] = {
            'type': matched_db,
            'suitable': True
        }
        session['step'] = 'framework'
        
        return {
            "step": "framework",
            "message": f"✅ {matched_db} sélectionné! Quel framework souhaitez-vous utiliser?",
            "suggestions": ["Flask", "Django"],
            "current_specs": session['specifications']
        }
    
    def _process_framework(self, session, framework_choice):
        """Traite le choix de framework"""
        framework_lower = framework_choice.lower().strip()
        
        if 'flask' in framework_lower:
            framework = 'Flask'
        elif 'django' in framework_lower:
            framework = 'Django'
        else:
            return {
                "step": "framework",
                "error": "Framework non reconnu. Choisissez 'Flask' ou 'Django'",
                "suggestions": ["Flask", "Django"]
            }
        
        session['specifications']['framework'] = {
            'type': framework
        }
        session['step'] = 'entities'
        
        return {
            "step": "entities",
            "message": f"✅ {framework} sélectionné! Décrivez maintenant vos entités:",
            "format": "Entity1: attr1, attr2, related_id; Entity2: attr1, attr2",
            "tips": [
                "Les champs terminant par '_id' seront automatiquement détectés comme des clés étrangères",
                "Utilisez (type) pour spécifier le type: name (string), age (integer), price (decimal)",
                "Les noms comme 'User', 'Account', 'Profile' seront détectés comme modèles utilisateur",
                "Les champs 'email', 'password', 'username' activeront l'authentification"
            ],
            "examples": [
                "User: username, email, password; Post: title, content, author_id; Comment: content, post_id, user_id",
                "Product: name, price (decimal), stock (integer); Order: total (decimal), status, customer_id; Customer: name, email",
                "Author: name, bio, birth_year (integer); Book: title, isbn, year (integer), author_id"
            ],
            "current_specs": session['specifications']
        }
    
    def _process_entities(self, session, entities_description):
        """Traite les entités"""
        if len(entities_description.strip()) < 5:
            return {
                "step": "entities",
                "error": "Description trop courte",
                "examples": [
                    "Book: title, author; Author: name, bio"
                ]
            }
        
        try:
            # Parser les entités avec le nouveau parser intelligent
            parsed = self.entity_parser.parse_entities(entities_description)
            
            if not parsed['entities']:
                return {
                    "step": "entities",
                    "error": "Aucune entité détectée. Format: Entity: attr1, attr2; Entity2: attr1",
                    "examples": ["Book: title, author; Author: name"]
                }
            
            session['specifications']['entities'] = parsed
            session['step'] = 'confirmation'
            
            summary = self._generate_summary(session['specifications'])
            
            logger.info(f"Entités parsées: {[e['name'] for e in parsed['entities']]}")
            logger.info(f"Relations détectées: {len(parsed.get('relations', []))}")
            logger.info(f"Endpoints générés: {len(parsed.get('endpoints', []))}")
            
            return {
                "step": "confirmation",
                "message": "✅ Entités analysées avec succès! Voici le résumé détaillé:",
                "summary": summary,
                "insights": {
                    "total_entities": len(parsed['entities']),
                    "total_relations": len(parsed.get('relations', [])),
                    "total_endpoints": len(parsed.get('endpoints', [])),
                    "has_authentication": parsed.get('has_user_management', False),
                    "user_models": [e['name'] for e in parsed['entities'] if e.get('is_user_model')]
                },
                "instructions": "Tapez 'confirmer' pour générer le projet ou 'modifier' pour recommencer",
                "current_specs": session['specifications']
            }
        
        except Exception as e:
            logger.error(f"Erreur parsing: {e}", exc_info=True)
            return {
                "step": "entities",
                "error": f"Erreur de parsing: {str(e)}",
                "examples": ["Book: title, author; Author: name"]
            }
    
    def _process_confirmation(self, session, user_response):
        """Traite la confirmation"""
        response_lower = user_response.lower().strip()
        
        if response_lower in ['confirmer', 'confirm', 'oui', 'yes', 'ok', 'valider']:
            session['confirmed'] = True
            
            try:
                # 🤖 Analyse par agents LLM
                logger.info("🤖 Analyse du projet par les agents IA...")
                agent_analysis = self.agent_orchestrator.process_with_agents(
                    session['specifications']
                )
                
                # Sauvegarder les insights des agents
                session['agent_insights'] = agent_analysis['agent_insights']
                
                # Générer un résumé des recommandations
                recommendations_summary = self.agent_orchestrator.get_recommendations_summary(
                    agent_analysis
                )
                
                # Générer le code
                logger.info("🚀 Génération du projet...")
                generated_code = self.code_generator.generate_complete_project(
                    session['specifications']
                )
                
                # Ajouter le fichier de recommandations IA
                generated_code['AI_RECOMMENDATIONS.md'] = recommendations_summary
                
                session['generated_code'] = generated_code
                
                return {
                    "step": "complete",
                    "message": "🎉 Projet généré avec succès!",
                    "summary": self._generate_summary(session['specifications']),
                    "agent_recommendations": {
                        "domain": agent_analysis['agent_insights'].get('specification_analysis', {}).get('domain'),
                        "security_score": agent_analysis['agent_insights'].get('security_audit', {}).get('score'),
                        "suggestions_count": len(agent_analysis['agent_insights'].get('best_practices', [])),
                        "improvements_available": True
                    },
                    "files_count": len(generated_code),
                    "files": list(generated_code.keys()),
                    "download_ready": True,
                    "session_id": next(k for k, v in self.sessions.items() if v == session)
                }
            
            except Exception as e:
                logger.error(f"Erreur génération: {e}", exc_info=True)
                return {
                    "step": "confirmation",
                    "error": f"Erreur de génération: {str(e)}"
                }
        
        elif 'modifier' in response_lower or 'change' in response_lower or 'recommencer' in response_lower:
            # Réinitialiser à l'objectif
            session['step'] = 'objectif'
            session['specifications'] = {}
            return {
                "step": "objectif",
                "message": "Très bien, recommençons depuis le début. Quel est l'objectif de votre projet?"
            }
        
        else:
            return {
                "step": "confirmation",
                "error": "Réponse non comprise. Dites 'confirmer' pour valider ou 'modifier' pour recommencer"
            }
    
    def _generate_summary(self, specs):
        """Génère un résumé détaillé"""
        entities_data = specs.get('entities', {})
        entities = entities_data.get('entities', [])
        relations = entities_data.get('relations', [])
        endpoints = entities_data.get('endpoints', [])
        
        return {
            "project_name": specs.get('project_name', 'my_project'),
            "objective": specs.get('objectif', ''),
            "database": specs.get('database', {}).get('type', ''),
            "framework": specs.get('framework', {}).get('type', ''),
            "complexity": specs.get('complexity', 'medium'),
            "entities": [
                {
                    "name": e['name'],
                    "attributes": [f"{a['name']} ({a['type']})" for a in e.get('attributes', [])],
                    "is_user_model": e.get('is_user_model', False),
                    "user_role": e.get('user_role', 'standard'),
                    "relationships": e.get('relationships', [])
                }
                for e in entities
            ],
            "relations": [
                f"{r['from_entity']} → {r['to_entity']} ({r['type']})"
                for r in relations
            ],
            "endpoints_summary": {
                "total": len(endpoints),
                "authenticated": sum(1 for e in endpoints if e.get('requires_auth')),
                "public": sum(1 for e in endpoints if not e.get('requires_auth')),
                "nested": sum(1 for e in endpoints if e.get('is_nested'))
            },
            "total_classes": len(entities),
            "total_relations": len(relations),
            "estimated_endpoints": len(endpoints),
            "has_user_management": entities_data.get('has_user_management', False)
        }
    
    def get_session(self, session_id):
        """Récupère une session"""
        return self.sessions.get(session_id)


# Instance globale
generator = AgenticRAGGenerator()


# Routes Flask
@app.route('/')
def home():
    return jsonify({
        "name": "Agentic RAG API Generator v2.1 🤖",
        "version": "2.1.0",
        "status": "running",
        "features": [
            "🧠 AI-Powered Code Generation with LLM Agents",
            "🔍 Intelligent entity parsing with relations detection",
            "🔗 Automatic foreign key detection",
            "👤 User model detection and authentication",
            "⚡ Smart endpoint generation",
            "🐍 Full Django & Flask support",
            "🔐 Role-based access control",
            "📊 Domain-specific recommendations",
            "🛡️ Security audit",
            "📚 Enhanced documentation generation"
        ],
        "ai_agents": {
            "specification_analyzer": "Analyzes project objectives and suggests improvements",
            "entity_enricher": "Suggests additional attributes and relations",
            "code_optimizer": "Provides best practices recommendations",
            "security_auditor": "Performs security audit",
            "documentation_generator": "Generates enhanced documentation"
        },
        "endpoints": {
            "start": "POST /api/start - Start new session",
            "process": "POST /api/process - Process user input",
            "download": "GET /api/download/<session_id> - Download generated project",
            "recommendations": "GET /api/recommendations/<session_id> - Get AI recommendations",
            "sessions": "GET /api/sessions - List all sessions"
        }
    })


@app.route('/api/start', methods=['POST'])
def start_session():
    """Démarre une nouvelle session"""
    try:
        session_id = generator.start_new_session()
        return jsonify({
            "success": True,
            "session_id": session_id,
            "step": "objectif",
            "message": "🎯 Bienvenue! Décrivez l'objectif de votre API:",
            "examples": [
                "API de gestion de bibliothèque avec livres et auteurs",
                "Système de blog avec articles, commentaires et utilisateurs",
                "Plateforme e-commerce avec produits, commandes et clients"
            ]
        })
    except Exception as e:
        logger.error(f"Erreur start: {e}")
        return jsonify({"success": False, "error": str(e)}), 500


@app.route('/api/process', methods=['POST'])
def process_input():
    """Traite l'input utilisateur"""
    try:
        data = request.get_json()
        if not data:
            return jsonify({"success": False, "error": "Pas de données"}), 400
        
        session_id = data.get('session_id')
        user_input = data.get('user_input')
        
        if not session_id or not user_input:
            return jsonify({
                "success": False,
                "error": "session_id et user_input requis"
            }), 400
        
        result = generator.process_user_input(session_id, user_input)
        result['success'] = 'error' not in result
        return jsonify(result)
    
    except Exception as e:
        logger.error(f"Erreur process: {e}")
        return jsonify({"success": False, "error": str(e)}), 500


@app.route('/api/download/<session_id>', methods=['GET'])
def download_project(session_id):
    """Télécharge le projet généré"""
    try:
        session = generator.get_session(session_id)
        if not session:
            return jsonify({"error": "Session non trouvée"}), 404
        
        if 'generated_code' not in session:
            return jsonify({"error": "Pas de code généré"}), 400
        
        # Créer le ZIP
        zip_buffer = io.BytesIO()
        with zipfile.ZipFile(zip_buffer, 'w', zipfile.ZIP_DEFLATED) as zf:
            for filepath, content in session['generated_code'].items():
                zf.writestr(filepath, content)
        
        zip_buffer.seek(0)
        
        project_name = session['specifications'].get('project_name', 'project')
        filename = f"{project_name}.zip"
        
        return send_file(
            zip_buffer,
            as_attachment=True,
            download_name=filename,
            mimetype='application/zip'
        )
    
    except Exception as e:
        logger.error(f"Erreur download: {e}")
        return jsonify({"error": str(e)}), 500


@app.route('/api/health', methods=['GET'])
def health():
    """Health check endpoint"""
    return jsonify({
        "status": "healthy",
        "sessions": len(generator.sessions),
        "timestamp": datetime.now().isoformat(),
        "version": "2.0.0"
    })


@app.route('/api/sessions', methods=['GET'])
def list_sessions():
    """Liste toutes les sessions actives"""
    try:
        sessions_info = []
        for session_id, session_data in generator.sessions.items():
            sessions_info.append({
                "session_id": session_id,
                "step": session_data.get('step'),
                "confirmed": session_data.get('confirmed', False),
                "created_at": session_data.get('created_at'),
                "has_code": 'generated_code' in session_data
            })
        
        return jsonify({
            "success": True,
            "total": len(sessions_info),
            "sessions": sessions_info
        })
    except Exception as e:
        logger.error(f"Erreur list_sessions: {e}")
        return jsonify({"success": False, "error": str(e)}), 500


@app.route('/api/recommendations/<session_id>', methods=['GET'])
def get_ai_recommendations(session_id):
    """Récupère les recommandations IA pour une session"""
    try:
        session = generator.get_session(session_id)
        if not session:
            return jsonify({"error": "Session non trouvée"}), 404
        
        agent_insights = session.get('agent_insights')
        if not agent_insights:
            return jsonify({
                "message": "Aucune recommandation disponible. Générez d'abord le projet."
            }), 404
        
        # Générer le résumé complet
        agent_results = {"agent_insights": agent_insights}
        summary = generator.agent_orchestrator.get_recommendations_summary(agent_results)
        
        return jsonify({
            "success": True,
            "session_id": session_id,
            "insights": agent_insights,
            "summary_markdown": summary
        })
    
    except Exception as e:
        logger.error(f"Erreur recommendations: {e}")
        return jsonify({"success": False, "error": str(e)}), 500


if __name__ == '__main__':
    port = int(os.getenv('PORT', 7000))
    logger.info(f"🚀 Démarrage du serveur sur le port {port}")
    app.run(host='0.0.0.0', port=port, debug=True)