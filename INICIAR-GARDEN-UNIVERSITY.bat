@echo off
title Garden University System - Iniciador Completo
cls
echo =====================================================
echo           Garden University System
echo              INICIADOR AUTOMATICO
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

echo ✅ Java detectado correctamente
echo.
echo 🚀 Iniciando Garden University System...
echo.
echo ⏳ Esto puede tomar 1-2 minutos la primera vez...
echo.

REM Iniciar la aplicacion en segundo plano con configuracion de Windows
start /b java -jar GardenUniversity.jar --spring.config.additional-location=application-windows.properties

REM Esperar a que la aplicacion se inicie
echo 🔄 Esperando a que la aplicacion se inicie...
timeout /t 30 /nobreak >nul

REM Intentar abrir el navegador
echo 🌐 Abriendo en el navegador...
start http://localhost:8080

echo.
echo =====================================================
echo          ✅ APLICACION INICIADA
echo =====================================================
echo.
echo 🌐 URL: http://localhost:8080
echo.
echo 👤 Usuarios:
echo    - Admin: admin@garden.uni.edu / admin123
echo    - Director: director@garden.uni.edu / director123
echo.
echo 🛑 Para DETENER la aplicacion:
echo    - Cierra esta ventana o presiona Ctrl+C
echo.
echo =====================================================

REM Mantener la ventana abierta
echo Presiona cualquier tecla para cerrar la aplicacion...
pause >nul

REM Intentar cerrar Java (funciona en algunos casos)
taskkill /f /im java.exe >nul 2>&1
echo Aplicacion cerrada.
