#!/bin/bash
KOWIHAN_BIN="/home/wissalelalouan/eclipse-workspace/KowihanGenerator/bin"
ECLIPSE_PLUGINS="/home/wissalelalouan/opt/eclipse/plugins"
ECLIPSE_OLD="/home/wissalelalouan/eclipse/plugins"

java -cp "$KOWIHAN_BIN:$ECLIPSE_PLUGINS/*:$ECLIPSE_OLD/*" \
  KowihanGenerator.ManualGenerator "$@"
