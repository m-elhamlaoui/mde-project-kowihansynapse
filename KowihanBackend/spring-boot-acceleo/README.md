# Spring Boot Acceleo Generator

Service Spring Boot pour la génération de code API via Acceleo à partir de spécifications JSON ou fichiers XMI.

## 🚀 Fonctionnalités

- Génération de projets Django/Flask/FastAPI à partir de spécifications JSON
- Support des fichiers XMI directs
- Génération de modèles XMI conformes à APIMetamodel.ecore
- Exécution de templates Acceleo via wrapper Python (évite les dépendances Java problématiques)
- Création d'archives ZIP pour téléchargement

## ⚙️ Architecture sans dépendances Acceleo Java

Ce projet **n'utilise PAS** les dépendances Acceleo Java (qui posent des problèmes d'artifacts).
À la place, il utilise :
- **Python wrapper** : Exécute Acceleo via le script Python `acceleo_runner.py`
- **ProcessBuilder** : Spring Boot appelle le script Python en ligne de commande
- **Fallback** : Si Python n'est pas disponible, génère une structure basique

## 📋 Prérequis

- Java 17+
- Maven 3.6+
- (Optionnel) Docker pour containerisation

## 🏗️ Structure du Projet

```
spring-boot-acceleo/
├── src/main/java/com/kowihan/acceleo/
│   ├── SpringBootAcceleoApplication.java
│   ├── controller/
│   │   └── GenerationController.java
│   ├── service/
│   │   ├── ModelGenerationService.java
│   │   ├── AcceleoRunnerService.java
│   │   ├── FileStorageService.java
│   │   └── ZipService.java
│   ├── model/
│   │   ├── dto/
│   │   └── metamodel/
│   └── runner/
│       └── JavaAcceleoRunner.java
├── src/main/resources/
│   ├── application.properties
│   ├── templates/acceleo/
│   │   └── main.mtl
│   └── metamodels/
│       └── APIMetamodel.ecore
└── pom.xml
```

## 🔧 Configuration

Modifiez `src/main/resources/application.properties` pour configurer :

- Port du serveur (défaut: 8080)
- Chemins de stockage
- Chemins des templates et métamodèles

## 🚀 Démarrage

### Local

```bash
mvn clean install
mvn spring-boot:run
```

### Docker

```bash
docker build -t spring-boot-acceleo .
docker run -p 8080:8080 spring-boot-acceleo
```

## 📡 Endpoints API

### POST `/api/generation/generate-from-spec`

Génère un projet à partir d'une spécification JSON.

**Body (JSON):**
```json
{
  "projectName": "HotelAPI",
  "framework": "DJANGO",
  "description": "API pour gestion hôtelière",
  "pythonVersion": "3.9",
  "database": {
    "type": "POSTGRESQL",
    "host": "localhost",
    "port": 5432,
    "name": "hotel_db"
  },
  "authentication": {
    "enabled": true,
    "method": "JWT",
    "tokenExpiryMinutes": 60
  },
  "entities": [
    {
      "name": "Hotel",
      "tableName": "hotels",
      "attributes": [
        {"name": "id", "type": "STRING", "isPrimaryKey": true},
        {"name": "name", "type": "STRING", "maxLength": 255}
      ]
    }
  ]
}
```

### POST `/api/generation/generate-from-xmi`

Génère un projet à partir d'un fichier XMI (multipart/form-data).

**Form Data:**
- `xmi_file`: Fichier XMI
- `projectName`: (optionnel) Nom du projet

### GET `/api/generation/download/{fileName}`

Télécharge le ZIP généré.

### GET `/api/generation/health`

Vérifie l'état du service.

### GET `/api/generation/frameworks`

Liste les frameworks supportés.

## 🔗 Intégration avec Flask

Le service Flask peut appeler ce service Spring Boot pour générer des projets :

```python
import requests

response = requests.post(
    'http://localhost:8080/api/generation/generate-from-spec',
    json=project_specification
)
```

## 📝 Notes

- Le service génère des fichiers XMI conformes à APIMetamodel.ecore
- **Acceleo est exécuté via Python** : Le service utilise `acceleo_runner.py` (script Python) au lieu des dépendances Java
- Les projets générés sont archivés en ZIP pour téléchargement
- **Prérequis Python** : Python 3 doit être installé pour l'exécution complète d'Acceleo
- Si Python n'est pas disponible, une structure basique est générée comme fallback

## 🔧 Configuration Python

Pour utiliser la génération complète Acceleo :

1. **Installer Python 3** :
   ```bash
   python3 --version
   ```

2. **S'assurer que `acceleo_runner.py` est disponible** :
   - Soit dans le projet Flask parent (`../acceleo_runner.py`)
   - Soit dans `src/main/resources/scripts/acceleo_runner.py`

3. **Configurer dans `application.properties`** (optionnel) :
   ```properties
   acceleo.python.script=python3 acceleo_runner.py
   ```

