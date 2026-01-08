#!/usr/bin/env python3
"""
Acceleo Runner - Bridge Python pour exécuter les templates Acceleo MTL
Ce script appelle le vrai moteur de génération Acceleo
"""

import os
import sys
import subprocess
import json
from pathlib import Path
from typing import Dict, Any

class AcceleoRunner:
    """Runner pour exécuter les templates Acceleo via Java"""
    
    def __init__(self, java_generator_jar: str = None):
        """
        Args:
            java_generator_jar: Chemin vers le JAR du générateur Acceleo compilé
        """
        self.java_generator_jar = java_generator_jar or self._find_generator_jar()
    
    def _find_generator_jar(self) -> str:
        """Trouve le JAR du générateur Acceleo"""
        possible_paths = [
            # Dans le projet Spring Boot
            "../spring-boot-acceleo/generator/acceleo-generator.jar",
            "./generator/acceleo-generator.jar",
            # Dans le projet Eclipse
            "../../KowihanGenerator/target/kowihan-generator.jar",
            "../KowihanGenerator/bin/kowihan-generator.jar",
        ]
        
        for path in possible_paths:
            if os.path.exists(path):
                return str(Path(path).resolve())
        
        # Si aucun JAR trouvé, utiliser le fallback Python
        print("  Acceleo JAR not found, using Python fallback generator")
        return None
    
    def generate(self, model_path: str, output_path: str, 
                 template_path: str = None, metamodel_path: str = None) -> Dict[str, Any]:
        """
        Génère le projet en appelant Acceleo
        
        Args:
            model_path: Chemin vers le fichier XMI model
            output_path: Répertoire de sortie
            template_path: Chemin vers main.mtl (optionnel)
            metamodel_path: Chemin vers APIMetamodel.ecore (optionnel)
        
        Returns:
            Dict avec success, output_path, generated_files, etc.
        """
        
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
        
        # Si JAR Acceleo disponible, l'utiliser
        if self.java_generator_jar and os.path.exists(self.java_generator_jar):
            return self._run_acceleo_java(model_path, output_path)
        else:
            # Sinon, utiliser le générateur Python de fallback
            return self._run_python_fallback(model_path, output_path)
    
    def _run_acceleo_java(self, model_path: str, output_path: str) -> Dict[str, Any]:
        """Exécute le générateur Acceleo Java"""
        try:
            print(" Calling Acceleo Java Generator...")
            print(f"   JAR: {self.java_generator_jar}")
            print(f"   Model: {model_path}")
            print(f"   Output: {output_path}")
            
            # Commande Java
            cmd = [
                "java",
                "-jar",
                self.java_generator_jar,
                model_path,
                output_path
            ]
            
            # Exécuter
            result = subprocess.run(
                cmd,
                capture_output=True,
                text=True,
                timeout=120  # 2 minutes max
            )
            
            if result.returncode == 0:
                # Compter les fichiers générés
                generated_files = self._count_generated_files(output_path)
                
                return {
                    "success": True,
                    "output_path": output_path,
                    "generated_files": generated_files,
                    "generator": "acceleo-java",
                    "stdout": result.stdout
                }
            else:
                return {
                    "success": False,
                    "error": f"Acceleo generation failed: {result.stderr}",
                    "output_path": output_path,
                    "generated_files": 0,
                    "stderr": result.stderr
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
    
    def _run_python_fallback(self, model_path: str, output_path: str) -> Dict[str, Any]:
        """Générateur Python de fallback (simple)"""
        print("⚠️  Using Python fallback generator")
        print("   For production, compile and use Acceleo Java generator!")
        
        try:
            # Import du générateur Python embarqué
            from .python_generator import PythonGenerator
            
            generator = PythonGenerator(model_path, output_path)
            result = generator.generate()
            
            return {
                **result,
                "generator": "python-fallback",
                "warning": "Using fallback generator. Compile Acceleo for production!"
            }
            
        except ImportError:
            return {
                "success": False,
                "error": "Neither Acceleo JAR nor Python fallback available",
                "output_path": output_path,
                "generated_files": 0
            }
    
    def _count_generated_files(self, output_path: str) -> int:
        """Compte les fichiers générés"""
        count = 0
        for root, dirs, files in os.walk(output_path):
            count += len(files)
        return count


def main():
    """Point d'entrée du script"""
    import argparse
    
    parser = argparse.ArgumentParser(
        description='Acceleo Runner - Execute Acceleo templates via Python'
    )
    parser.add_argument('--template', help='Path to Acceleo template (main.mtl)')
    parser.add_argument('--metamodel', help='Path to Ecore metamodel (.ecore)')
    parser.add_argument('--model', required=True, help='Path to XMI model file')
    parser.add_argument('--output', required=True, help='Output directory')
    parser.add_argument('--jar', help='Path to Acceleo generator JAR (optional)')
    
    args = parser.parse_args()
    
    print("="*70)
    print(" Acceleo Runner - Python Bridge")
    print("="*70)
    print(f"Model:  {args.model}")
    print(f"Output: {args.output}")
    if args.jar:
        print(f"JAR:    {args.jar}")
    print()
    
    # Créer le runner
    runner = AcceleoRunner(java_generator_jar=args.jar)
    
    # Générer
    result = runner.generate(
        model_path=args.model,
        output_path=args.output,
        template_path=args.template,
        metamodel_path=args.metamodel
    )
    
    # Afficher le résultat
    print()
    print("="*70)
    if result.get('success'):
        print(" GENERATION SUCCESSFUL")
        print("="*70)
        print(f"Generator:   {result.get('generator', 'unknown')}")
        print(f"Output:      {result.get('output_path')}")
        print(f"Files:       {result.get('generated_files')}")
        
        if result.get('warning'):
            print(f"⚠️  Warning:   {result['warning']}")
        
        sys.exit(0)
    else:
        print(" GENERATION FAILED")
        print("="*70)
        print(f"Error: {result.get('error')}")
        if result.get('stderr'):
            print()
            print("Details:")
            print(result['stderr'])
        sys.exit(1)


if __name__ == "__main__":
    main()