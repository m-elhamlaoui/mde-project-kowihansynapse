#!/usr/bin/env python3
"""
Wrapper Python pour exécuter Acceleo depuis Spring Boot
Utilise le script acceleo_runner.py existant dans le projet Flask
"""

import argparse
import sys
import os
from pathlib import Path

def main():
    parser = argparse.ArgumentParser(description='Execute Acceleo template generation')
    parser.add_argument('--template', help='Path to Acceleo template (.mtl) - kept for compatibility')
    parser.add_argument('--metamodel', help='Path to Ecore metamodel - kept for compatibility')
    parser.add_argument('--model', required=True, help='Path to XMI model file')
    parser.add_argument('--output', required=True, help='Output directory')
    
    args = parser.parse_args()
    
    print(f"Acceleo Runner - Python Wrapper")
    print(f"Model: {args.model}")
    print(f"Output: {args.output}")
    
    # Check if model file exists
    if not os.path.exists(args.model):
        print(f"ERROR: Model file not found: {args.model}", file=sys.stderr)
        sys.exit(1)
    
    # Create output directory
    os.makedirs(args.output, exist_ok=True)
    
    # Try to use the Flask project's acceleo_runner.py if available
    # Look for it in multiple possible locations
    possible_paths = [
        Path(__file__).parent.parent.parent.parent / "acceleo_runner.py",  # From resources/scripts to root
        Path(__file__).parent.parent.parent.parent.parent / "acceleo_runner.py",  # One more level up
        Path("acceleo_runner.py"),  # Current directory
        Path("../acceleo_runner.py"),  # Parent directory
    ]
    
    flask_runner = None
    for path in possible_paths:
        abs_path = path.resolve()
        if abs_path.exists() and abs_path.is_file():
            flask_runner = abs_path
            break
    
    if flask_runner:
        print(f"Found Flask project acceleo_runner.py at {flask_runner}, using it...")
        try:
            # Import and use the Flask project's generator
            sys.path.insert(0, str(flask_runner.parent))
            from acceleo_runner import AcceleoGenerator
            
            generator = AcceleoGenerator()
            result = generator.generate(
                model_path=args.model,
                output_path=args.output
            )
            
            if result.get('success'):
                print("✓ Generation completed successfully")
                print(f"  Project: {result.get('project_name')}")
                print(f"  Framework: {result.get('framework')}")
                print(f"  Files generated: {result.get('generated_files')}")
                sys.exit(0)
            else:
                print(f"ERROR: Generation failed: {result.get('error')}", file=sys.stderr)
                sys.exit(1)
        except Exception as e:
            print(f"ERROR: Could not use Flask generator: {e}", file=sys.stderr)
            import traceback
            traceback.print_exc()
            sys.exit(1)
    else:
        print("ERROR: acceleo_runner.py not found in Flask project", file=sys.stderr)
        print("Searched in:", file=sys.stderr)
        for path in possible_paths:
            print(f"  - {path.resolve()}", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()
