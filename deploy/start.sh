#!/bin/bash
java -Dserver.port=${PORT:-8080} -Dspring.profiles.active=prod -jar garden-university.jar
