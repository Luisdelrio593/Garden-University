#!/bin/bash

echo "🚀 Starting Garden University System..."

# Check if JAR exists
if [ ! -f "target/garden-university-system-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ JAR file not found!"
    exit 1
fi

echo "✅ JAR file found"
echo "📊 Port: ${PORT:-8080}"

# Start the application
exec java -Xmx512m -Xms256m \
    -Dserver.port=${PORT:-8080} \
    -Dserver.address=0.0.0.0 \
    -Dspring.profiles.active=prod \
    -jar target/garden-university-system-0.0.1-SNAPSHOT.jar
