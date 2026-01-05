"""
Module d'intégration Ollama pour utiliser Llama localement
Pas de limites de requêtes, 100% gratuit et privé
"""

import requests
import json
import logging
from typing import Dict, List, Optional
from dataclasses import dataclass

logger = logging.getLogger(__name__)

@dataclass
class OllamaConfig:
    """Configuration pour Ollama"""
    base_url: str = "http://localhost:11434"
    model: str = "llama3.2"  # ou llama2, mistral, codellama, etc.
    temperature: float = 0.7
    timeout: int = 120


class OllamaClient:
    """Client pour communiquer avec Ollama (Llama local)"""
    
    def __init__(self, config: OllamaConfig = None):
        self.config = config or OllamaConfig()
        self._check_ollama_available()
    
    def _check_ollama_available(self):
        """Vérifie si Ollama est disponible"""
        try:
            response = requests.get(f"{self.config.base_url}/api/tags", timeout=5)
            if response.status_code == 200:
                models = response.json().get('models', [])
                available_models = [m['name'] for m in models]
                logger.info(f"✅ Ollama disponible. Modèles: {available_models}")
                
                # Vérifier si le modèle configuré est disponible
                if not any(self.config.model in m for m in available_models):
                    logger.warning(f"⚠️ Modèle {self.config.model} non trouvé. Téléchargez-le avec: ollama pull {self.config.model}")
            else:
                logger.warning("⚠️ Ollama n'est pas accessible. Installez-le: https://ollama.ai")
        except requests.exceptions.RequestException as e:
            logger.warning(f"⚠️ Ollama non disponible: {e}")
            logger.info("Installation: curl -fsSL https://ollama.ai/install.sh | sh")
    
    def generate(self, prompt: str, system_prompt: str = None) -> str:
        """
        Génère une réponse avec Ollama
        
        Args:
            prompt: Le prompt utilisateur
            system_prompt: Instructions système (optionnel)
        
        Returns:
            La réponse générée
        """
        try:
            url = f"{self.config.base_url}/api/generate"
            
            payload = {
                "model": self.config.model,
                "prompt": prompt,
                "stream": False,
                "options": {
                    "temperature": self.config.temperature
                }
            }
            
            if system_prompt:
                payload["system"] = system_prompt
            
            logger.info(f"🤖 Appel Ollama ({self.config.model})...")
            response = requests.post(
                url,
                json=payload,
                timeout=self.config.timeout
            )
            
            if response.status_code == 200:
                result = response.json()
                generated_text = result.get('response', '')
                logger.info(f"✅ Réponse générée ({len(generated_text)} chars)")
                return generated_text
            else:
                logger.error(f"❌ Erreur Ollama: {response.status_code}")
                return self._fallback_response(prompt)
        
        except requests.exceptions.Timeout:
            logger.error("⏱️ Timeout Ollama")
            return self._fallback_response(prompt)
        except Exception as e:
            logger.error(f"❌ Erreur Ollama: {e}")
            return self._fallback_response(prompt)
    
    def chat(self, messages: List[Dict[str, str]]) -> str:
        """
        Mode chat avec historique
        
        Args:
            messages: Liste de messages [{"role": "user", "content": "..."}]
        
        Returns:
            La réponse générée
        """
        try:
            url = f"{self.config.base_url}/api/chat"
            
            payload = {
                "model": self.config.model,
                "messages": messages,
                "stream": False,
                "options": {
                    "temperature": self.config.temperature
                }
            }
            
            response = requests.post(
                url,
                json=payload,
                timeout=self.config.timeout
            )
            
            if response.status_code == 200:
                result = response.json()
                return result.get('message', {}).get('content', '')
            else:
                logger.error(f"Erreur chat Ollama: {response.status_code}")
                return ""
        
        except Exception as e:
            logger.error(f"Erreur chat Ollama: {e}")
            return ""
    
    def _fallback_response(self, prompt: str) -> str:
        """Réponse de secours si Ollama n'est pas disponible"""
        return ""
    
    def is_available(self) -> bool:
        """Vérifie si Ollama est disponible"""
        try:
            response = requests.get(f"{self.config.base_url}/api/tags", timeout=2)
            return response.status_code == 200
        except:
            return False


class SmartLlamaAgent:
    """Agent intelligent utilisant Llama via Ollama"""
    
    def __init__(self, model: str = "llama3.2"):
        self.client = OllamaClient(OllamaConfig(model=model))
        self.available = self.client.is_available()
    
    def analyze_objective(self, objective: str) -> Dict:
        """Analyse l'objectif du projet avec Llama"""
        
        if not self.available:
            logger.info("Ollama non disponible, utilisation des règles")
            return self._analyze_with_rules(objective)
        
        system_prompt = """Tu es un expert en architecture logicielle et développement d'APIs REST.
Analyse l'objectif donné et identifie:
1. Le domaine d'application (blog, e-commerce, social, healthcare, etc.)
2. La complexité (low, medium, high)
3. Les entités principales nécessaires
4. Les relations entre entités

Réponds en JSON avec cette structure:
{
  "domain": "nom_du_domaine",
  "complexity": "low|medium|high",
  "entities": ["Entity1", "Entity2"],
  "suggestions": ["suggestion1", "suggestion2"]
}"""
        
        prompt = f"Analyse ce projet: {objective}"
        
        response = self.client.generate(prompt, system_prompt)
        
        try:
            # Extraire le JSON de la réponse
            json_start = response.find('{')
            json_end = response.rfind('}') + 1
            if json_start >= 0 and json_end > json_start:
                json_str = response[json_start:json_end]
                result = json.loads(json_str)
                logger.info(f"✅ Analyse Llama: {result.get('domain')}")
                return result
            else:
                logger.warning("Pas de JSON dans la réponse, fallback")
                return self._analyze_with_rules(objective)
        except Exception as e:
            logger.error(f"Erreur parsing réponse Llama: {e}")
            return self._analyze_with_rules(objective)
    
    def suggest_attributes(self, entity_name: str, context: str = "") -> List[Dict]:
        """Suggère des attributs pour une entité"""
        
        if not self.available:
            return self._suggest_attributes_rules(entity_name)
        
        system_prompt = """Tu es un expert en modélisation de données.
Suggère 5 attributs pertinents pour l'entité donnée.

Réponds en JSON:
{
  "attributes": [
    {"name": "nom_attribut", "type": "string|integer|boolean|datetime", "reason": "raison"}
  ]
}"""
        
        prompt = f"Entité: {entity_name}\nContexte: {context}\nSuggère des attributs pertinents."
        
        response = self.client.generate(prompt, system_prompt)
        
        try:
            json_start = response.find('{')
            json_end = response.rfind('}') + 1
            if json_start >= 0:
                json_str = response[json_start:json_end]
                result = json.loads(json_str)
                return result.get('attributes', [])
        except:
            pass
        
        return self._suggest_attributes_rules(entity_name)
    
    def generate_best_practices(self, project_type: str, framework: str) -> List[str]:
        """Génère des best practices avec Llama"""
        
        if not self.available:
            return self._best_practices_rules(framework)
        
        system_prompt = """Tu es un expert en développement web et sécurité.
Donne 8 best practices pour ce type de projet.
Réponds en JSON: {"practices": ["practice1", "practice2", ...]}"""
        
        prompt = f"Projet: {project_type}\nFramework: {framework}\nDonne des best practices."
        
        response = self.client.generate(prompt, system_prompt)
        
        try:
            json_start = response.find('{')
            json_end = response.rfind('}') + 1
            if json_start >= 0:
                json_str = response[json_start:json_end]
                result = json.loads(json_str)
                return result.get('practices', [])
        except:
            pass
        
        return self._best_practices_rules(framework)
    
    def audit_security(self, project_specs: Dict) -> Dict:
        """Audit de sécurité avec Llama"""
        
        if not self.available:
            return self._security_audit_rules(project_specs)
        
        system_prompt = """Tu es un expert en sécurité des applications web.
Analyse les specs du projet et donne:
1. Un score de sécurité (A, B+, B, C)
2. 3 avertissements principaux
3. 5 recommandations

Réponds en JSON:
{
  "score": "B+",
  "warnings": ["warning1", "warning2"],
  "recommendations": ["rec1", "rec2"]
}"""
        
        prompt = f"Specs du projet: {json.dumps(project_specs, indent=2)}\nFais un audit de sécurité."
        
        response = self.client.generate(prompt, system_prompt)
        
        try:
            json_start = response.find('{')
            json_end = response.rfind('}') + 1
            if json_start >= 0:
                json_str = response[json_start:json_end]
                return json.loads(json_str)
        except:
            pass
        
        return self._security_audit_rules(project_specs)
    
    # ===== Méthodes fallback basées sur des règles =====
    
    def _analyze_with_rules(self, objective: str) -> Dict:
        """Analyse avec des règles (fallback)"""
        obj_lower = objective.lower()
        
        # Détection du domaine
        domain_keywords = {
            "blog": ["blog", "article", "post"],
            "e-commerce": ["shop", "store", "product", "ecommerce"],
            "social": ["social", "network", "community"],
            "healthcare": ["health", "medical", "patient"],
            "finance": ["bank", "finance", "trading"]
        }
        
        domain = "general"
        for d, keywords in domain_keywords.items():
            if any(kw in obj_lower for kw in keywords):
                domain = d
                break
        
        # Complexité
        if any(w in obj_lower for w in ["simple", "basic", "small"]):
            complexity = "low"
        elif any(w in obj_lower for w in ["complex", "advanced", "scalable"]):
            complexity = "high"
        else:
            complexity = "medium"
        
        # Entités suggérées selon le domaine
        entity_suggestions = {
            "blog": ["User", "Post", "Comment", "Category", "Tag"],
            "e-commerce": ["User", "Product", "Order", "Cart", "Review"],
            "social": ["User", "Post", "Comment", "Like", "Follow"],
            "general": ["User", "Item"]
        }
        
        return {
            "domain": domain,
            "complexity": complexity,
            "entities": entity_suggestions.get(domain, ["User"]),
            "suggestions": [
                f"Considérez un système de {domain}",
                "Ajoutez une authentification JWT",
                "Implémentez une pagination"
            ]
        }
    
    def _suggest_attributes_rules(self, entity_name: str) -> List[Dict]:
        """Suggestions d'attributs par règles"""
        entity_lower = entity_name.lower()
        
        common_attrs = {
            "user": [
                {"name": "username", "type": "string", "reason": "Identifiant unique"},
                {"name": "email", "type": "string", "reason": "Contact"},
                {"name": "password", "type": "password", "reason": "Authentification"},
                {"name": "avatar", "type": "image", "reason": "Photo de profil"},
                {"name": "is_active", "type": "boolean", "reason": "Compte actif"}
            ],
            "post": [
                {"name": "title", "type": "string", "reason": "Titre"},
                {"name": "content", "type": "text", "reason": "Contenu"},
                {"name": "slug", "type": "string", "reason": "URL SEO"},
                {"name": "status", "type": "string", "reason": "État de publication"},
                {"name": "view_count", "type": "integer", "reason": "Nombre de vues"}
            ],
            "product": [
                {"name": "name", "type": "string", "reason": "Nom du produit"},
                {"name": "price", "type": "decimal", "reason": "Prix"},
                {"name": "stock", "type": "integer", "reason": "Quantité"},
                {"name": "image", "type": "image", "reason": "Photo"},
                {"name": "sku", "type": "string", "reason": "Référence"}
            ]
        }
        
        return common_attrs.get(entity_lower, [])
    
    def _best_practices_rules(self, framework: str) -> List[str]:
        """Best practices par règles"""
        practices = [
            "Utilisez des variables d'environnement pour les secrets",
            "Implémentez la pagination sur les listes",
            "Ajoutez des tests unitaires et d'intégration",
            "Documentez votre API avec OpenAPI/Swagger",
            "Utilisez HTTPS en production",
            "Implémentez le rate limiting",
            "Ajoutez des logs structurés",
            "Versionnez votre API (/api/v1/)"
        ]
        
        if framework.lower() == "django":
            practices.extend([
                "Utilisez select_related() pour les ForeignKey",
                "Configurez django-debug-toolbar en dev"
            ])
        
        return practices
    
    def _security_audit_rules(self, specs: Dict) -> Dict:
        """Audit de sécurité par règles"""
        has_auth = specs.get('entities', {}).get('has_user_management', False)
        
        score = "B" if has_auth else "C"
        
        warnings = [
            "⚠️ Définissez des SECRET_KEY robustes",
            "⚠️ Validez toutes les entrées utilisateur",
            "⚠️ Activez HTTPS en production"
        ]
        
        recommendations = [
            "Utilisez JWT pour l'authentification",
            "Implémentez CORS correctement",
            "Ajoutez django-ratelimit",
            "Loggez les actions sensibles",
            "Chiffrez les données sensibles"
        ]
        
        if has_auth:
            recommendations.insert(0, "✅ Authentification détectée")
            score = "B+"
        
        return {
            "score": score,
            "warnings": warnings,
            "recommendations": recommendations
        }


# Fonction utilitaire pour installer Ollama automatiquement
def install_ollama_instructions():
    """Retourne les instructions d'installation d'Ollama"""
    return """
# 🦙 Installation d'Ollama (Llama local)

## Linux / macOS:
```bash
curl -fsSL https://ollama.ai/install.sh | sh
```

## Windows:
Téléchargez depuis: https://ollama.ai/download

## Après installation:

1. Démarrer Ollama:
```bash
ollama serve
```

2. Télécharger un modèle (choisissez selon vos besoins):
```bash
# Llama 3.2 (3B - Rapide, 2GB RAM)
ollama pull llama3.2

# Llama 2 (7B - Équilibré, 4GB RAM)
ollama pull llama2

# Mistral (7B - Très bon, 4GB RAM)
ollama pull mistral

# CodeLlama (7B - Spécialisé code, 4GB RAM)
ollama pull codellama

# Llama 3 (8B - Puissant, 5GB RAM)
ollama pull llama3
```

3. Tester:
```bash
ollama run llama3.2 "Hello, écris-moi un JSON"
```

## Modèles recommandés par usage:

| Modèle | Taille | RAM | Usage |
|--------|--------|-----|-------|
| llama3.2 | 3B | 2GB | Léger, rapide, idéal pour dev |
| llama2 | 7B | 4GB | Équilibré, bon compromis |
| mistral | 7B | 4GB | Très performant, français |
| codellama | 7B | 4GB | Spécialisé code/API |
| llama3 | 8B | 5GB | Le plus puissant |

## Vérifier qu'Ollama fonctionne:
```bash
curl http://localhost:11434/api/tags
```

✅ Si vous voyez une liste de modèles, c'est bon!
"""


# Export des classes principales
__all__ = [
    'OllamaClient',
    'OllamaConfig',
    'SmartLlamaAgent',
    'install_ollama_instructions'
]