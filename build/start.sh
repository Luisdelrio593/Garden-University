#!/bin/bash
export PORT=${PORT:-8080}
export SPRING_PROFILES_ACTIVE=prod
export DATABASE_URL=${DATABASE_URL:-"jdbc:h2:mem:garden_university"}
java -Dserver.port=$PORT -jar app.jar
