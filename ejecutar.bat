@echo off
chcp 65001 >nul
title Garden University System

echo.
echo ========================================
echo   🎓 Garden University System
echo ========================================
echo.

REM Verificar si Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR: Java no está instalado o no está en el PATH del sistema.
    echo.
    echo 📥 Por favor instala Java 17 o superior desde:
    echo    https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo ✅ Java encontrado
echo 🚀 Iniciando el sistema Garden University...
echo.
echo 📋 Información:
echo    • URL: http://localhost:8080
echo    • Consola H2: http://localhost:8080/h2-console
echo.
echo 👥 Usuarios:
echo    • admin@garden.uni.edu / admin123
echo    • director@garden.uni.edu / director123
echo.
echo 🔧 Para detener: presiona Ctrl+C
echo ========================================
echo.

REM Ejecutar con configuración optimizada para Windows
java -Dspring.profiles.active=windows -Dfile.encoding=UTF-8 -Djava.awt.headless=true -Xmx512m -jar GardenUniversity.jar

echo.
echo 🛑 Sistema detenido.
pause
