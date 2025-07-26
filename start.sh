#!/bin/bash

# Script de inicio para Railway
echo "🚀 Iniciando Garden University System..."

# Verificar que el JAR existe
if [ ! -f "target/garden-university-system-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ Error: No se encontró el archivo JAR"
    exit 1
fi

# Configurar variables de entorno
export SPRING_PROFILES_ACTIVE=prod
export JAVA_OPTS="-Xmx512m -Xms256m"

# Mostrar información del sistema
echo "📊 Puerto: ${PORT:-8080}"
echo "📊 Perfil: ${SPRING_PROFILES_ACTIVE}"
echo "📊 Opciones Java: ${JAVA_OPTS}"

# Iniciar la aplicación
echo "🌱 Ejecutando aplicación..."
exec java $JAVA_OPTS \
    -Dserver.port=${PORT:-8080} \
    -Dserver.address=0.0.0.0 \
    -Dspring.profiles.active=prod \
    -jar target/garden-university-system-0.0.1-SNAPSHOT.jar
