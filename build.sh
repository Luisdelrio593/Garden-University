#!/bin/bash

echo "🏫 Garden University - Build Script"
echo "=================================="

# Variables
APP_NAME="garden-university-system"
VERSION="0.0.1-SNAPSHOT"

echo "📦 Paso 1: Limpieza de builds anteriores"
mvn clean

echo "🔨 Paso 2: Compilación del proyecto"
mvn package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build completado exitosamente"
    echo "📁 Archivo JAR generado: target/$APP_NAME-$VERSION.jar"
    echo ""
    echo "🚀 Para ejecutar la aplicación:"
    echo "   java -jar target/$APP_NAME-$VERSION.jar"
    echo ""
    echo "🌐 Una vez iniciada, acceder a: http://localhost:8080"
else
    echo "❌ Error en la compilación"
    exit 1
fi
