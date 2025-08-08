@echo off
setlocal enableDelayedExpansion
chcp 65001 >nul

echo.
echo ===============================================================
echo                    🎓 GARDEN UNIVERSITY SYSTEM
echo                    Instalador para Windows
echo ===============================================================
echo.

REM Verificar privilegios de administrador
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  NOTA: Se recomienda ejecutar como administrador para una mejor experiencia
    timeout /t 3 >nul
)

REM Verificar si Java está instalado
echo 🔍 Verificando Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ❌ ERROR: Java no está instalado o no está en el PATH del sistema.
    echo.
    echo 📥 Por favor instala Java 17 o superior desde:
    echo    https://adoptium.net/
    echo    - Descarga "OpenJDK 17 (LTS)"
    echo    - Instala y reinicia tu computadora
    echo    - Vuelve a ejecutar este instalador
    echo.
    pause
    exit /b 1
) else (
    for /f "tokens=3" %%i in ('java -version 2^>^&1 ^| findstr /i "version"') do (
        set JAVA_VERSION=%%i
        set JAVA_VERSION=!JAVA_VERSION:"=!
    )
    echo ✅ Java encontrado: !JAVA_VERSION!
)

REM Verificar la versión de Java
for /f "tokens=1,2 delims=." %%i in ("!JAVA_VERSION!") do (
    if %%i geq 17 (
        echo ✅ Versión de Java compatible
    ) else if %%i equ 1 (
        if %%j geq 8 (
            echo ⚠️  Java 8 encontrado, se recomienda Java 17 o superior
        ) else (
            echo ❌ Java version muy antigua, se requiere Java 17 o superior
            pause
            exit /b 1
        )
    ) else (
        echo ❌ Java version no compatible, se requiere Java 17 o superior
        pause
        exit /b 1
    )
)

echo.
echo 🚀 Iniciando Garden University System...
echo.
echo 📋 Información del Sistema:
echo    • Puerto: 8080
echo    • Base de datos: H2 (memoria)
echo    • Interfaz web: http://localhost:8080
echo.
echo 👥 Usuarios de prueba:
echo    • Administrador: admin@garden.uni.edu / admin123
echo    • Director: director@garden.uni.edu / director123
echo.
echo 🔧 Para detener el sistema: presiona Ctrl+C
echo ===============================================================
echo.

REM Verificar si el puerto 8080 está libre
netstat -an | findstr ":8080" >nul
if %errorlevel% equ 0 (
    echo ⚠️  ADVERTENCIA: El puerto 8080 parece estar en uso
    echo    Si tienes problemas, cierra otras aplicaciones que usen este puerto
    echo.
    timeout /t 3 >nul
)

echo 🌟 Iniciando aplicación...
echo    Una vez que veas "Started GardenUniversityApplication", 
echo    abre tu navegador en: http://localhost:8080
echo.

REM Ejecutar la aplicación
java -Dfile.encoding=UTF-8 -Djava.awt.headless=true -jar GardenUniversity.jar

echo.
echo 🛑 El sistema Garden University se ha detenido.
echo.
pause
