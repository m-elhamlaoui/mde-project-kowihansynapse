"""
Agents LLM pour amélioration intelligente de la génération de code
Utilise Ollama (Llama local) - 100% gratuit, sans limites!
"""

import os
import json
import logging
from typing import Dict, List, Any, Optional
from dataclasses import dataclass

# Import de l'intégration Ollama
from ollama_integration import SmartLlamaAgent

logger = logging.getLogger(__name__)

@dataclass
class AgentResponse:
    """Réponse d'un agent LLM"""
    success: bool
    data: Any
    suggestions: List[str] = None
    warnings: List[str] = None
    improvements: List[str] = None


class BaseAgent:
    """Agent de base pour tous les agents LLM"""
    
    def __init__(self, model: str = "llama3.2"):
        self.llama_agent = SmartLlamaAgent(model=model)
        self.model = model
        logger.info(f"🦙 Agent initialisé avec {model}")
    
    def is_available(self) -> bool:
        """Vérifie si Llama est disponible"""
        return self.llama_agent.available


class SpecificationAnalyzer(BaseAgent):
    """Agent pour analyser et enrichir les spécifications utilisateur"""
    
    def analyze_objective(self, objective: str) -> AgentResponse:
        """
        Analyse l'objectif pour suggérer des améliorations
        Utilise Llama si disponible, sinon règles intelligentes
        """
        logger.info(f"Analyse de l'objectif: {objective[:50]}...")
        
        # 🦙 Utiliser Llama pour l'analyse
        analysis_result = self.llama_agent.analyze_objective(objective)
        
        domain = analysis_result.get('domain', 'general')
        complexity = analysis_result.get('complexity', 'medium')
        suggested_entities = analysis_result.get('entities', [])
        suggestions = analysis_result.get('suggestions', [])
        
        # Enrichir avec des suggestions spécifiques au domaine
        improvements = []
        if domain == "blog" and "Category" not in str(suggested_entities):
            improvements.append("Pour un blog complet, ajoutez un système de catégories et tags")
        elif domain == "e-commerce" and "Cart" not in str(suggested_entities):
            improvements.append("Pour l'e-commerce, pensez aux paiements et avis clients")
        elif domain == "social" and "Follow" not in str(suggested_entities):
            improvements.append("Pour un réseau social, ajoutez interactions et notifications")
        
        # Suggérer la stack technique
        tech_stack = self._suggest_tech_stack(objective, complexity)
        
        return AgentResponse(
            success=True,
            data={
                "domain": domain,
                "complexity": complexity,
                "suggested_tech_stack": tech_stack,
                "recommended_entities": self._format_entities(suggested_entities)
            },
            suggestions=suggestions,
            improvements=improvements
        )
    
    def _format_entities(self, entity_names: List[str]) -> List[Dict]:
        """Formate les noms d'entités en structure complète"""
        entity_descriptions = {
            "User": "Utilisateurs du système",
            "Post": "Publications/Articles",
            "Comment": "Commentaires",
            "Category": "Catégories",
            "Tag": "Tags/Étiquettes",
            "Product": "Produits",
            "Order": "Commandes",
            "Cart": "Panier",
            "Review": "Avis clients",
            "Like": "Likes",
            "Follow": "Abonnements"
        }
        
        return [
            {"name": entity, "desc": entity_descriptions.get(entity, f"{entity} du système")}
            for entity in entity_names
        ]
    
    def _suggest_tech_stack(self, objective: str, complexity: str) -> Dict:
        
        return AgentResponse(
            success=True,
            data={
                "domain": domain,
                "complexity": complexity,
                "suggested_tech_stack": tech_stack,
                "recommended_entities": self._suggest_entities(domain)
            },
            suggestions=suggestions,
            warnings=warnings,
            improvements=improvements
        )
    
    def _detect_domain(self, objective: str) -> str:
        """Détecte le domaine d'application"""
        obj_lower = objective.lower()
        
        domains = {
            "blog": ["blog", "article", "post", "news", "magazine"],
            "e-commerce": ["shop", "store", "ecommerce", "e-commerce", "produit", "product"],
            "social": ["social", "réseau", "network", "communauté", "community"],
            "learning": ["cours", "formation", "learning", "éducation", "education"],
            "healthcare": ["santé", "health", "médical", "medical", "patient"],
            "finance": ["banque", "bank", "finance", "trading", "investissement"],
            "hr": ["rh", "hr", "recrutement", "recruitment", "employé", "employee"],
            "real_estate": ["immobilier", "real estate", "propriété", "property"],
            "project_management": ["projet", "project", "task", "gestion"]
        }
        
        for domain, keywords in domains.items():
            if any(kw in obj_lower for kw in keywords):
                return domain
        
        return "general"
    
    def _estimate_complexity(self, objective: str) -> str:
        """Estime la complexité du projet"""
        words = objective.lower().split()
        
        complexity_indicators = {
            "high": ["complexe", "avancé", "complet", "scalable", "microservices"],
            "low": ["simple", "basique", "petit", "minimal", "prototype"]
        }
        
        for level, keywords in complexity_indicators.items():
            if any(kw in words for kw in keywords):
                return level
        
        # Estimer par le nombre de concepts mentionnés
        concept_count = len([w for w in words if len(w) > 5])
        if concept_count > 15:
            return "high"
        elif concept_count < 8:
            return "low"
        return "medium"
    
    def _suggest_tech_stack(self, objective: str, complexity: str) -> Dict:
        """Suggère une stack technique adaptée"""
        obj_lower = objective.lower()
        
        # Par défaut
        stack = {
            "framework": "Django",
            "database": "PostgreSQL",
            "cache": "Redis",
            "queue": "Celery"
        }
        
        # Ajustements selon le domaine
        if complexity == "low" or "simple" in obj_lower:
            stack["framework"] = "Flask"
            stack["database"] = "SQLite"
            stack.pop("cache", None)
            stack.pop("queue", None)
        
        if "real-time" in obj_lower or "chat" in obj_lower:
            stack["websocket"] = "Django Channels"
        
        if "api" in obj_lower and "mobile" in obj_lower:
            stack["api_doc"] = "OpenAPI/Swagger"
        
        return stack
    
    def _suggest_entities(self, domain: str) -> List[Dict]:
        """Suggère des entités pour un domaine donné"""
        entity_templates = {
            "blog": [
                {"name": "User", "desc": "Auteurs et lecteurs"},
                {"name": "Post", "desc": "Articles de blog"},
                {"name": "Comment", "desc": "Commentaires"},
                {"name": "Category", "desc": "Catégories d'articles"},
                {"name": "Tag", "desc": "Tags pour articles"}
            ],
            "e-commerce": [
                {"name": "User", "desc": "Clients"},
                {"name": "Product", "desc": "Produits"},
                {"name": "Order", "desc": "Commandes"},
                {"name": "Cart", "desc": "Panier"},
                {"name": "Review", "desc": "Avis clients"}
            ],
            "social": [
                {"name": "User", "desc": "Utilisateurs"},
                {"name": "Post", "desc": "Publications"},
                {"name": "Comment", "desc": "Commentaires"},
                {"name": "Like", "desc": "Likes"},
                {"name": "Follow", "desc": "Abonnements"}
            ],
            "general": [
                {"name": "User", "desc": "Utilisateurs"},
                {"name": "Item", "desc": "Éléments principaux"}
            ]
        }
        
        return entity_templates.get(domain, entity_templates["general"])


class EntityEnricher(BaseAgent):
    """Agent pour enrichir les entités avec des attributs intelligents"""
    
    def enrich_entity(self, entity_name: str, attributes: List[str], 
                 context: Dict = None) -> AgentResponse:
        """
        Enrichit une entité avec des attributs suggérés
        Utilise Llama pour des suggestions contextuelles
        """
        logger.info(f"Enrichissement de l'entité: {entity_name}")
        
        # 🦙 Utiliser Llama pour suggestions intelligentes
        context_str = json.dumps(context) if context else f"Entité dans une API REST"
        suggested_attrs_llama = self.llama_agent.suggest_attributes(entity_name, context_str)
        
        # Attributs de base recommandés (fallback)
        base_attrs = self._get_base_attributes(entity_name)
        specific_attrs = self._get_specific_attributes(entity_name)
        
        # Combiner suggestions Llama + règles
        all_attrs = suggested_attrs_llama + base_attrs + specific_attrs
        
        # Filtrer les attributs déjà présents
        current_attr_names = [a.lower() for a in attributes]
        suggested_attributes = []
        improvements = []
        
        seen = set()
        for attr in all_attrs:
            attr_name = attr.get('name', '').lower()
            if attr_name and attr_name not in current_attr_names and attr_name not in seen:
                # 🔒 PROTECTION: Normaliser l'attribut pour garantir toutes les clés
                normalized_attr = {
                    'name': attr.get('name', 'unknown'),
                    'type': attr.get('type', 'string'),
                    'reason': attr.get('reason', 'Recommandé pour cette entité')
                }
                suggested_attributes.append(normalized_attr)
                improvements.append(
                    f"Ajoutez '{normalized_attr['name']}' ({normalized_attr['type']}): {normalized_attr['reason']}"
                )
                seen.add(attr_name)
        
        # Détecter les relations manquantes
        missing_relations = self._suggest_relations(entity_name, attributes)
        
        # 🔒 PROTECTION: Normaliser les relations pour garantir toutes les clés
        normalized_relations = []
        for rel in missing_relations:
            normalized_rel = {
                'field': rel.get('field', 'unknown_field'),
                'to': rel.get('to', 'Unknown'),
                'reason': rel.get('reason', 'Relation recommandée')
            }
            normalized_relations.append(normalized_rel)
        
        return AgentResponse(
            success=True,
            data={
                "entity_name": entity_name,
                "suggested_attributes": suggested_attributes[:5],  # Top 5
                "missing_relations": normalized_relations
            },
            suggestions=[f"Attribut suggéré: {a['name']}" for a in suggested_attributes[:5]],
            improvements=improvements[:5]
        )
        
    
    def _get_base_attributes(self, entity_name: str) -> List[Dict]:
        """Retourne les attributs de base pour toute entité"""
        entity_lower = entity_name.lower()
        
        base = []
        
        # Tous les objets devraient avoir un slug si c'est une ressource publique
        if entity_lower in ['post', 'article', 'product', 'page', 'category']:
            base.append({
                "name": "slug",
                "type": "string",
                "reason": "Pour des URLs SEO-friendly"
            })
        
        # Status pour les contenus
        if entity_lower in ['post', 'article', 'product', 'order']:
            base.append({
                "name": "status",
                "type": "string",
                "reason": "Pour gérer l'état (draft, published, archived, etc.)"
            })
        
        # Champs de publication
        if entity_lower in ['post', 'article', 'news']:
            base.append({
                "name": "published_at",
                "type": "datetime",
                "reason": "Date de publication"
            })
        
        return base
    
    def _get_specific_attributes(self, entity_name: str) -> List[Dict]:
        """Retourne les attributs spécifiques à un type d'entité"""
        entity_lower = entity_name.lower()
        
        specific_attrs = {
            "user": [
                {"name": "avatar", "type": "image", "reason": "Photo de profil"},
                {"name": "bio", "type": "text", "reason": "Biographie"},
                {"name": "is_active", "type": "boolean", "reason": "Compte actif ou suspendu"},
                {"name": "last_login", "type": "datetime", "reason": "Dernière connexion"}
            ],
            "post": [
                {"name": "view_count", "type": "integer", "reason": "Nombre de vues"},
                {"name": "featured", "type": "boolean", "reason": "Article mis en avant"},
                {"name": "excerpt", "type": "text", "reason": "Résumé court"}
            ],
            "product": [
                {"name": "price", "type": "decimal", "reason": "Prix du produit"},
                {"name": "stock", "type": "integer", "reason": "Quantité en stock"},
                {"name": "sku", "type": "string", "reason": "Référence produit"},
                {"name": "image", "type": "image", "reason": "Photo du produit"}
            ],
            "order": [
                {"name": "total", "type": "decimal", "reason": "Montant total"},
                {"name": "payment_status", "type": "string", "reason": "Statut du paiement"},
                {"name": "shipping_address", "type": "text", "reason": "Adresse de livraison"}
            ],
            "comment": [
                {"name": "is_approved", "type": "boolean", "reason": "Modération des commentaires"},
                {"name": "rating", "type": "integer", "reason": "Note (si applicable)"}
            ]
        }
        
        return specific_attrs.get(entity_lower, [])
    
    def _suggest_relations(self, entity_name: str, attributes: List[str]) -> List[Dict]:
        """Suggère des relations manquantes"""
        entity_lower = entity_name.lower()
        attr_names = [a.lower() for a in attributes]
        
        suggestions = []
        
        # Relations communes
        common_relations = {
            "post": [
                {"field": "author_id", "to": "User", "reason": "Chaque article a un auteur"},
                {"field": "category_id", "to": "Category", "reason": "Organiser les articles"}
            ],
            "comment": [
                {"field": "author_id", "to": "User", "reason": "Auteur du commentaire"},
                {"field": "post_id", "to": "Post", "reason": "Article commenté"}
            ],
            "product": [
                {"field": "category_id", "to": "Category", "reason": "Catégorie du produit"},
                {"field": "vendor_id", "to": "User", "reason": "Vendeur (si marketplace)"}
            ],
            "order": [
                {"field": "user_id", "to": "User", "reason": "Client qui a passé commande"}
            ]
        }
        
        for rel in common_relations.get(entity_lower, []):
            field_name = rel['field'].lower()
            if field_name not in attr_names:
                suggestions.append(rel)
        
        return suggestions


class CodeOptimizer(BaseAgent):
    """Agent pour optimiser le code généré"""
    
    def optimize_models(self, models_code: str, context: Dict = None) -> AgentResponse:
        """
        Optimise les modèles générés
        """
        logger.info("Optimisation des modèles...")
        
        improvements = []
        optimized_code = models_code
        
        # Ajouter des indexes pour les recherches fréquentes
        if "email" in models_code and "db_index=True" not in models_code:
            improvements.append("Ajout d'index sur le champ email pour améliorer les performances")
        
        # Suggérer des validations
        if "price" in models_code.lower():
            improvements.append("Considérez d'ajouter une validation pour les prix négatifs")
        
        # Suggérer des méthodes utiles
        if "class User" in models_code:
            improvements.append("Ajoutez une méthode get_full_name() pour l'utilisateur")
        
        return AgentResponse(
            success=True,
            data={"optimized_code": optimized_code},
            improvements=improvements
        )
    
    def suggest_best_practices(self, project_specs: Dict) -> AgentResponse:
        """
        Suggère des best practices pour le projet
        Utilise Llama pour des suggestions contextuelles
        """
        framework = project_specs.get('framework', {})
        if isinstance(framework, dict):
            framework = framework.get('type', 'Django')
        
        domain = project_specs.get('detected_domain', 'general')
        
        # 🦙 Utiliser Llama pour best practices
        llama_practices = self.llama_agent.generate_best_practices(domain, framework)
        
        # Ajouter règles de base
        base_practices = [
            "Utilisez des variables d'environnement pour les secrets",
            "Implémentez la pagination sur les listes",
            "Ajoutez des tests unitaires et d'intégration",
            "Documentez votre API avec OpenAPI/Swagger"
        ]
        
        # Combiner et dédupliquer
        all_practices = llama_practices + base_practices
        unique_practices = []
        seen = set()
        
        for practice in all_practices:
            practice_lower = practice.lower()
            if practice_lower not in seen:
                unique_practices.append(practice)
                seen.add(practice_lower)
        
        return AgentResponse(
            success=True,
            data={"best_practices": unique_practices[:10]},  # Top 10
            suggestions=unique_practices[:10]
        )


class SecurityAuditor(BaseAgent):
    """Agent pour auditer la sécurité du code généré"""
    
    def audit_security(self, project_specs: Dict) -> AgentResponse:
        """
        Audit de sécurité du projet
        Utilise Llama pour analyse contextuelle
        """
        logger.info("Audit de sécurité...")
        
        # 🦙 Utiliser Llama pour l'audit
        llama_audit = self.llama_agent.audit_security(project_specs)
        
        return AgentResponse(
            success=True,
            data={"security_score": llama_audit.get('score', 'B')},
            warnings=llama_audit.get('warnings', []),
            suggestions=llama_audit.get('recommendations', [])
        )
        
        warnings = []
        recommendations = []
        
        # Vérifier l'authentification
        if project_specs.get('entities', {}).get('has_user_management'):
            recommendations.append("✅ Authentification JWT détectée")
            warnings.append("⚠️ Assurez-vous de définir des SECRET_KEY robustes")
            recommendations.append("Utilisez django-ratelimit pour limiter les tentatives de connexion")
        
        # Vérifier CORS
        recommendations.append("Configurez CORS_ALLOWED_ORIGINS en production")
        
        # Permissions
        recommendations.append("Utilisez des permissions granulaires (IsOwnerOrReadOnly)")
        
        # Validation
        warnings.append("⚠️ Validez toutes les entrées utilisateur")
        recommendations.append("Utilisez Django validators pour les champs sensibles")
        
        # HTTPS
        warnings.append("⚠️ Activez SECURE_SSL_REDIRECT=True en production")
        
        return AgentResponse(
            success=True,
            data={"security_score": "B+"},
            warnings=warnings,
            suggestions=recommendations
        )


class DocumentationGenerator(BaseAgent):
    """Agent pour générer une documentation enrichie"""
    
    def generate_enhanced_docs(self, project_specs: Dict) -> AgentResponse:
        """
        Génère une documentation enrichie
        """
        logger.info("Génération de documentation enrichie...")
        
        docs = {
            "quick_start": self._generate_quickstart(project_specs),
            "api_reference": self._generate_api_reference(project_specs),
            "architecture": self._generate_architecture_doc(project_specs),
            "deployment": self._generate_deployment_guide(project_specs)
        }
        
        return AgentResponse(
            success=True,
            data=docs,
            improvements=["Documentation complète générée avec exemples"]
        )
    
    def _generate_quickstart(self, specs: Dict) -> str:
        """Génère un guide de démarrage rapide"""
        framework = specs.get('framework', {}).get('type', 'Django')
        
        return f"""
# 🚀 Quick Start Guide

## Installation rapide

```bash
# 1. Créer l'environnement
python -m venv venv
source venv/bin/activate

# 2. Installer les dépendances
pip install -r requirements.txt

# 3. Configuration
cp .env.example .env
# Éditer .env avec vos paramètres

# 4. Base de données
{'python manage.py migrate' if framework == 'Django' else 'flask db upgrade'}

# 5. Lancer
{'python manage.py runserver' if framework == 'Django' else 'python run.py'}
```

## Premier test

```bash
# Créer un utilisateur
curl -X POST http://localhost:8000/api/users/register/ \\
  -H "Content-Type: application/json" \\
  -d '{{"username": "test", "email": "test@example.com", "password": "Test123!"}}'
```
"""
    
    def _generate_api_reference(self, specs: Dict) -> str:
        """Génère une référence API"""
        endpoints = specs.get('entities', {}).get('endpoints', [])
        
        doc = "# 📚 API Reference\n\n"
        
        # Grouper par ressource
        resources = {}
        for ep in endpoints:
            resource = ep['path'].split('/')[2] if len(ep['path'].split('/')) > 2 else 'root'
            if resource not in resources:
                resources[resource] = []
            resources[resource].append(ep)
        
        for resource, eps in resources.items():
            doc += f"\n## {resource.upper()}\n\n"
            for ep in eps:
                auth = "🔒" if ep.get('requires_auth') else "🔓"
                doc += f"- `{ep['method']}` `{ep['path']}` {auth} - {ep['description']}\n"
        
        return doc
    
    def _generate_architecture_doc(self, specs: Dict) -> str:
        """Génère une doc d'architecture"""
        return """
# 🏗️ Architecture

## Structure du projet
```
project/
├── api/              # Application principale
│   ├── models.py     # Modèles de données
│   ├── views.py      # Logique métier
│   ├── serializers.py # Sérialisation
│   └── urls.py       # Routing
├── config/           # Configuration
└── tests/            # Tests
```

## Flux de données
```
Client → API → Serializer → View → Model → Database
```
"""
    
    def _generate_deployment_guide(self, specs: Dict) -> str:
        """Génère un guide de déploiement"""
        return """
# 🚀 Deployment Guide

## Production Checklist

- [ ] DEBUG=False
- [ ] SECRET_KEY sécurisée
- [ ] ALLOWED_HOSTS configuré
- [ ] Base de données production
- [ ] HTTPS activé
- [ ] Backup automatique
- [ ] Monitoring (Sentry)
- [ ] Logs centralisés

## Déploiement avec Docker

```dockerfile
FROM python:3.11
WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
CMD ["gunicorn", "config.wsgi:application"]
```
"""


class AgentOrchestrator:
    """Orchestrateur qui coordonne tous les agents"""
    
    def __init__(self):
        self.spec_analyzer = SpecificationAnalyzer()
        self.entity_enricher = EntityEnricher()
        self.code_optimizer = CodeOptimizer()
        self.security_auditor = SecurityAuditor()
        self.doc_generator = DocumentationGenerator()
    
    def process_with_agents(self, specifications: Dict) -> Dict:
        """
        Traite les spécifications avec tous les agents
        """
        logger.info("🤖 Démarrage du traitement avec agents LLM...")
        
        results = {
            "original_specs": specifications,
            "agent_insights": {},
            "enhanced_specs": specifications.copy()
        }
        
        # 1. Analyser l'objectif
        if 'objectif' in specifications:
            analysis = self.spec_analyzer.analyze_objective(specifications['objectif'])
            results['agent_insights']['specification_analysis'] = {
                "domain": analysis.data.get('domain'),
                "complexity": analysis.data.get('complexity'),
                "suggestions": analysis.suggestions,
                "improvements": analysis.improvements
            }
            
            # Enrichir les specs
            results['enhanced_specs']['detected_domain'] = analysis.data.get('domain')
            results['enhanced_specs']['recommended_entities'] = analysis.data.get('recommended_entities')
        
        # 2. Enrichir les entités
        entities = specifications.get('entities', {}).get('entities', [])
        enriched_entities = []
        
        for entity in entities:
            enrichment = self.entity_enricher.enrich_entity(
                entity['name'],
                [a['name'] for a in entity.get('attributes', [])],
                context=specifications
            )
            
            entity_info = {
                "entity": entity['name'],
                "suggested_attributes": enrichment.data.get('suggested_attributes', []),
                "missing_relations": enrichment.data.get('missing_relations', []),
                "improvements": enrichment.improvements
            }
            enriched_entities.append(entity_info)
        
        results['agent_insights']['entity_enrichment'] = enriched_entities
        
        # 3. Best practices
        best_practices = self.code_optimizer.suggest_best_practices(specifications)
        results['agent_insights']['best_practices'] = best_practices.suggestions
        
        # 4. Audit de sécurité
        security = self.security_auditor.audit_security(specifications)
        results['agent_insights']['security_audit'] = {
            "score": security.data.get('security_score'),
            "warnings": security.warnings,
            "recommendations": security.suggestions
        }
        
        # 5. Documentation enrichie
        docs = self.doc_generator.generate_enhanced_docs(specifications)
        results['agent_insights']['documentation'] = docs.data
        
        logger.info("✅ Traitement avec agents terminé")
        
        return results
    
    def get_recommendations_summary(self, agent_results: Dict) -> str:
        """
        Génère un résumé des recommandations
        """
        insights = agent_results.get('agent_insights', {})
        
        summary = "# 🤖 Recommandations des Agents IA\n\n"
        
        # Analyse des spécifications
        if 'specification_analysis' in insights:
            spec_analysis = insights['specification_analysis']
            summary += f"## 🎯 Analyse du projet\n"
            summary += f"- **Domaine**: {spec_analysis.get('domain', 'N/A')}\n"
            summary += f"- **Complexité**: {spec_analysis.get('complexity', 'N/A')}\n\n"
            
            if spec_analysis.get('suggestions'):
                summary += "### 💡 Suggestions\n"
                for sugg in spec_analysis['suggestions']:
                    summary += f"- {sugg}\n"
                summary += "\n"
        
        # Enrichissement des entités
        if 'entity_enrichment' in insights:
            summary += "## 🔧 Améliorations des entités\n\n"
            for entity_info in insights['entity_enrichment']:
                if entity_info.get('suggested_attributes') or entity_info.get('missing_relations'):
                    summary += f"### {entity_info['entity']}\n"
                    
                    if entity_info.get('suggested_attributes'):
                        summary += "**Attributs suggérés:**\n"
                        for attr in entity_info['suggested_attributes'][:3]:  # Top 3
                            attr_name = attr.get('name', 'unknown')
                            attr_type = attr.get('type', 'string')
                            attr_reason = attr.get('reason', 'Recommandé')
                            summary += f"- `{attr_name}` ({attr_type}): {attr_reason}\n"
                    
                    if entity_info.get('missing_relations'):
                        summary += "**Relations manquantes:**\n"
                        for rel in entity_info['missing_relations']:
                            summary += f"- `{rel['field']}` → {rel['to']}: {rel['reason']}\n"
                    summary += "\n"
        
        # Best practices
        if 'best_practices' in insights:
            summary += "## ✨ Best Practices\n"
            for bp in insights['best_practices'][:5]:  # Top 5
                summary += f"- {bp}\n"
            summary += "\n"
        
        # Sécurité
        if 'security_audit' in insights:
            security = insights['security_audit']
            summary += f"## 🔒 Sécurité (Score: {security.get('score', 'N/A')})\n"
            
            if security.get('warnings'):
                summary += "**Avertissements:**\n"
                for warn in security['warnings'][:3]:
                    summary += f"- {warn}\n"
            
            if security.get('recommendations'):
                summary += "**Recommandations:**\n"
                for rec in security['recommendations'][:3]:
                    summary += f"- {rec}\n"
        
        return summary