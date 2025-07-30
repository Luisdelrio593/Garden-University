# Garden University System - Ejecutar con PowerShell
# =====================================================

Write-Host "=====================================================" -ForegroundColor Green
Write-Host "           Garden University System" -ForegroundColor Cyan
Write-Host "                 Iniciando..." -ForegroundColor Yellow
Write-Host "=====================================================" -ForegroundColor Green
Write-Host ""

# Verificar si Java esta instalado
try {
    $javaVersion = java -version 2>&1
    if ($LASTEXITCODE -ne 0) {
        throw "Java no encontrado"
    }
    Write-Host "✅ Java detectado correctamente" -ForegroundColor Green
} catch {
    Write-Host "❌ ERROR: Java no esta instalado o no esta en el PATH" -ForegroundColor Red
    Write-Host ""
    Write-Host "Por favor instala Java 17 o superior desde:" -ForegroundColor Yellow
    Write-Host "https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Blue
    Write-Host ""
    Read-Host "Presiona Enter para salir"
    exit 1
}

Write-Host ""
Write-Host "🚀 Iniciando Garden University System..." -ForegroundColor Green
Write-Host ""
Write-Host "🌐 La aplicacion se abrira en tu navegador en:" -ForegroundColor Cyan
Write-Host "   http://localhost:8080" -ForegroundColor Blue
Write-Host ""
Write-Host "👤 Usuarios predeterminados:" -ForegroundColor Cyan
Write-Host "   - Admin: admin@garden.uni.edu / admin123" -ForegroundColor Yellow
Write-Host "   - Director: director@garden.uni.edu / director123" -ForegroundColor Yellow
Write-Host ""
Write-Host "=====================================================" -ForegroundColor Green
Write-Host "          PRESIONA CTRL+C PARA DETENER" -ForegroundColor Red
Write-Host "=====================================================" -ForegroundColor Green
Write-Host ""

# Ejecutar la aplicacion
try {
    & java -jar "GardenUniversity.jar"
} catch {
    Write-Host "❌ Error al ejecutar la aplicacion: $_" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
}

Read-Host "Presiona Enter para salir"
