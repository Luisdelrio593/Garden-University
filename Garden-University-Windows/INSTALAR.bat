@echo off
title Garden University System - Instalador
cls
echo =====================================================
echo        Garden University System - INSTALADOR
echo =====================================================
echo.
echo Este asistente verificara los requisitos del sistema
echo y preparara la aplicacion para su uso.
echo.
pause

REM Crear carpeta de datos si no existe
if not exist "data" (
    mkdir data
    echo ✅ Carpeta de datos creada
)

REM Verificar Java
echo.
echo 🔍 Verificando Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR: Java no esta instalado
    echo.
    echo Garden University System requiere Java 17 o superior.
    echo.
    echo 📥 Descargalo desde:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    echo Despues de instalar Java, ejecuta este instalador nuevamente.
    echo.
    pause
    exit /b 1
) else (
    echo ✅ Java detectado correctamente
)

REM Verificar que el JAR existe
if not exist "GardenUniversity.jar" (
    echo.
    echo ❌ ERROR: No se encuentra el archivo GardenUniversity.jar
    echo.
    echo Asegurate de que todos los archivos esten en la misma carpeta:
    echo - GardenUniversity.jar
    echo - INICIAR-GARDEN-UNIVERSITY.bat
    echo - Este instalador
    echo.
    pause
    exit /b 1
) else (
    echo ✅ Archivo de aplicacion encontrado
)

REM Verificar conectividad de puertos
echo.
echo 🔍 Verificando disponibilidad del puerto 8080...
netstat -an | find "8080" >nul
if %errorlevel% equ 0 (
    echo ⚠️  ADVERTENCIA: El puerto 8080 parece estar en uso
    echo.
    echo Si tienes problemas para acceder a la aplicacion,
    echo puedes cambiar el puerto editando application-windows.properties
    echo.
) else (
    echo ✅ Puerto 8080 disponible
)

echo.
echo =====================================================
echo              ✅ INSTALACION COMPLETA
echo =====================================================
echo.
echo 🎯 Garden University System esta listo para usar
echo.
echo 🚀 Para INICIAR la aplicacion:
echo    - Haz doble clic en: INICIAR-GARDEN-UNIVERSITY.bat
echo.
echo 📖 Para mas informacion:
echo    - Lee el archivo: README-WINDOWS.md
echo.
echo 🌐 La aplicacion se ejecutara en:
echo    http://localhost:8080
echo.
echo 👤 Usuarios predeterminados:
echo    Admin: admin@garden.uni.edu / admin123
echo    Director: director@garden.uni.edu / director123
echo.
echo =====================================================

pause
