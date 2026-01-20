#!/bin/bash

# ✅ Détecter automatiquement le chemin du script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# ✅ Chemins relatifs depuis le projet KowihanProject
PROJECT_ROOT="$SCRIPT_DIR/../../.."
KOWIHAN_BIN="$PROJECT_ROOT/KowihanGenerator/bin"

# Chemins Eclipse (à adapter selon votre installation)
ECLIPSE_PLUGINS="/home/wissalelalouan/opt/eclipse/plugins"
ECLIPSE_OLD="/home/wissalelalouan/eclipse/plugins"

# Vérifier que KowihanGenerator existe
if [ ! -d "$KOWIHAN_BIN" ]; then
    echo "❌ Erreur: KowihanGenerator/bin n'existe pas à: $KOWIHAN_BIN"
    echo "📁 Script dir: $SCRIPT_DIR"
    echo "📁 Project root: $PROJECT_ROOT"
    exit 1
fi

echo "✅ Using Acceleo from: $KOWIHAN_BIN"
echo "🚀 Launching Acceleo generator..."

# Lancer le générateur Java
java -cp "$KOWIHAN_BIN:$ECLIPSE_PLUGINS/*:$ECLIPSE_OLD/*" \
    KowihanGenerator.ManualGenerator "$@"

EXIT_CODE=$?

if [ $EXIT_CODE -eq 0 ]; then
    echo "✅ Generation completed successfully!"
else
    echo "❌ Generation failed with exit code: $EXIT_CODE"
fi

exit $EXIT_CODE
