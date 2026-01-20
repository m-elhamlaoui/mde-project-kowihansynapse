#!/usr/bin/env python3
"""
Acceleo Runner - Version corrigée avec les bons chemins ET classpath
"""

import os
import sys
import subprocess
import json
from pathlib import Path
from typing import Dict, Any

class AcceleoRunner:
    def __init__(self, java_generator_jar: str = None):
        self.java_generator_jar = java_generator_jar or self._find_generator_jar()
    
    def _find_generator_jar(self) -> str:
        """Trouve le JAR du générateur Acceleo - CHEMIN CORRIGÉ"""
        
        jar_path = Path(__file__).parent.parent / "KowihanGenerator" / "kowihan-generator.jar"
        
        if jar_path.exists():
            print(f"JAR trouvé: {jar_path}")
            return str(jar_path.resolve())
        
        # Autres chemins possibles
        possible_paths = [
            "../KowihanGenerator/kowihan-generator.jar",  # ← LE BON CHEMIN
            "../../KowihanGenerator/kowihan-generator.jar",
            "./kowihan-generator.jar",
            "kowihan-generator.jar",
        ]
        
        for path in possible_paths:
            if os.path.exists(path):
                print(f" JAR trouvé (alternatif): {path}")
                return str(Path(path).resolve())
        
        print("  Aucun JAR trouvé, utilisation du classpath direct")
        return None
    
    def generate(self, model_path: str, output_path: str, 
                 template_path: str = None, metamodel_path: str = None) -> Dict[str, Any]:
        # Vérifier que le modèle existe
        if not os.path.exists(model_path):
            return {
                "success": False,
                "error": f"Model file not found: {model_path}",
                "output_path": output_path,
                "generated_files": 0
            }
        
        # Créer le répertoire de sortie
        os.makedirs(output_path, exist_ok=True)
        
        # TOUJOURS utiliser la méthode avec classpath complet
        return self._run_acceleo_with_classpath(model_path, output_path)
    
    def _run_acceleo_with_classpath(self, model_path: str, output_path: str) -> Dict[str, Any]:
        """Exécute avec le bon classpath (comme run-acceleo.sh)"""
        try:
            # MÊMES CHEMINS QUE run-acceleo.sh
            kowihan_bin = "/home/wissalelalouan/eclipse-workspace/KowihanGenerator/bin"
            eclipse_plugins = "/home/wissalelalouan/opt/eclipse/plugins"
            eclipse_old = "/home/wissalelalouan/eclipse/plugins"
            
            print(f" Lancement avec classpath complet...")
            print(f"   Kowihan Bin: {kowihan_bin}")
            print(f"   Eclipse Plugins: {eclipse_plugins}")
            print(f"   Eclipse Old: {eclipse_old}")
            
            # Vérifier que les chemins existent
            if not os.path.exists(kowihan_bin):
                return {
                    "success": False,
                    "error": f"Kowihan bin directory not found: {kowihan_bin}",
                    "output_path": output_path,
                    "generated_files": 0
                }
            
            
            cmd = [
                "java",
                "-cp", f"{kowihan_bin}:{eclipse_plugins}/*:{eclipse_old}/*",
                "KowihanGenerator.ManualGenerator",
                "--model", model_path,
                "--output", output_path
            ]
            
            print(f"   Commande: {' '.join(cmd[:3])} ...")
            
            result = subprocess.run(
                cmd,
                capture_output=True,
                text=True,
                timeout=120
            )
            
            # Afficher la sortie pour débogage
            if result.stdout:
                print(f"   Sortie: {result.stdout[:200]}...")
            if result.stderr:
                print(f"   Erreurs: {result.stderr[:500]}...")
            
            if result.returncode == 0:
                # Compter les fichiers générés
                count = 0
                for root, dirs, files in os.walk(output_path):
                    count += len(files)
                
                return {
                    "success": True,
                    "output_path": output_path,
                    "generated_files": count,
                    "generator": "acceleo-classpath",
                    "stdout": result.stdout,
                    "returncode": result.returncode
                }
            else:
                return {
                    "success": False,
                    "error": f"Generation failed with code {result.returncode}",
                    "output_path": output_path,
                    "generated_files": 0,
                    "stderr": result.stderr,
                    "stdout": result.stdout,
                    "returncode": result.returncode
                }
                
        except subprocess.TimeoutExpired:
            return {
                "success": False,
                "error": "Acceleo generation timeout (>2min)",
                "output_path": output_path,
                "generated_files": 0
            }
        except Exception as e:
            return {
                "success": False,
                "error": f"Failed to run Acceleo: {str(e)}",
                "output_path": output_path,
                "generated_files": 0
            }
    
    def _run_acceleo_java(self, model_path: str, output_path: str) -> Dict[str, Any]:
        """Ancienne méthode (ne pas utiliser) - gardée pour compatibilité"""
        print("⚠️  Utilisation de l'ancienne méthode - passage à classpath...")
        return self._run_acceleo_with_classpath(model_path, output_path)

def main():
    import argparse
    
    parser = argparse.ArgumentParser(
        description='Acceleo Runner - Version corrigée'
    )
    parser.add_argument('--model', required=True, help='Path to XMI model file')
    parser.add_argument('--output', required=True, help='Output directory')
    parser.add_argument('--jar', help='Path to generator JAR (optional)')
    
    args = parser.parse_args()
    
    print("="*60)
    print(" Acceleo Runner - Version avec Classpath Complet")
    print("="*60)
    print(f"Model:  {args.model}")
    print(f"Output: {args.output}")
    print()
    
    # Créer le runner
    runner = AcceleoRunner(java_generator_jar=args.jar)
    
    # Générer
    result = runner.generate(
        model_path=args.model,
        output_path=args.output
    )
    
    # Afficher le résultat
    print()
    print("="*60)
    if result.get('success'):
        print(" GENERATION SUCCESSFUL")
        print("="*60)
        print(f"Générateur: {result.get('generator', 'unknown')}")
        print(f"Sortie:    {result.get('output_path')}")
        print(f"Fichiers:  {result.get('generated_files')}")
        print(f"Code sortie: {result.get('returncode', 'N/A')}")
        
        if result.get('stdout'):
            print(f"\nSortie complète:")
            print(result['stdout'][-500:])  # Derniers 500 caractères
        
        sys.exit(0)
    else:
        print(" GENERATION FAILED")
        print("="*60)
        print(f"Erreur: {result.get('error')}")
        if result.get('stderr'):
            print(f"\nDétails de l'erreur:")
            print(result['stderr'][:1000])
        sys.exit(1)

if __name__ == "__main__":
    main()
