@echo off
title Garden University System
cls
echo =====================================================
echo           Garden University System
echo                 Iniciando...
echo =====================================================
echo.

REM Verificar si Java esta instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java no esta instalado o no esta en el PATH
    echo.
    echo Por favor instala Java 17 o superior desde:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

echo Iniciando Garden University System...
echo.
echo La aplicacion se abrira en tu navegador en:
echo http://localhost:8080
echo.
echo Usuarios predeterminados:
echo - Admin: admin@garden.uni.edu / admin123
echo - Director: director@garden.uni.edu / director123
echo.
echo =====================================================
echo          PRESIONA CTRL+C PARA DETENER
echo =====================================================
echo.

REM Ejecutar la aplicacion
java -jar GardenUniversity.jar

pause
