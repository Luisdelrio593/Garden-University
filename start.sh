#!/bin/bash

echo "🚀 Starting Garden University System on Railway..."

# Set default port if not provided
export PORT=${PORT:-8080}

# Check if JAR exists
if [ ! -f "target/garden-university-system-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ JAR file not found!"
    ls -la target/
    exit 1
fi

echo "✅ JAR file found"
echo "📊 Port: $PORT"
echo "📊 Profile: prod"

# Start the application with Railway-specific settings
echo "🌱 Starting application..."
exec java -Xmx512m -Xms256m \
    -Dserver.port=$PORT \
    -Dserver.address=0.0.0.0 \
    -Dspring.profiles.active=prod \
    -Djava.awt.headless=true \
    -Dfile.encoding=UTF-8 \
    -jar target/garden-university-system-0.0.1-SNAPSHOT.jar
