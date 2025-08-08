# Garden University System - Quick Start Guide

## 🎯 Para Windows - Inicio Rápido

### Método 1: Ejecución Simple
1. **Doble clic** en `ejecutar.bat`
2. Espera a ver "Started GardenUniversityApplication"
3. Abre: **http://localhost:8080**

### Método 2: Instalación Completa
1. **Doble clic** en `instalar.bat` (recomendado)
2. Sigue las instrucciones en pantalla
3. El sistema se abrirá automáticamente

## 📁 Archivos Incluidos

```
📦 Garden University System/
├── 🚀 ejecutar.bat           # Inicio rápido
├── 🛠️  instalar.bat          # Instalador completo  
├── 📱 GardenUniversity.jar   # Aplicación principal
├── 📋 README-Windows.md      # Manual completo  
├── ⚙️  application-windows.properties # Configuración
└── 📚 documentación adicional
```

## 👥 Usuarios Predeterminados

| Rol | Usuario | Contraseña |
|-----|---------|------------|
| 👑 **Administrador** | `admin@garden.uni.edu` | `admin123` |
| 📊 **Director** | `director@garden.uni.edu` | `director123` |

## 🌐 URLs Importantes

- **Sistema Principal**: http://localhost:8080
- **Base de Datos H2**: http://localhost:8080/h2-console
- **Estado del Sistema**: http://localhost:8080/actuator/health

## ❓ Solución Rápida de Problemas

### No inicia la aplicación
```
1. Verifica Java: java --version
2. Si no tienes Java, descarga de: https://adoptium.net/
3. Reinicia tu PC después de instalar Java
```

### No puedo acceder desde el navegador
```
1. Espera el mensaje "Started GardenUniversityApplication"
2. Abre: http://localhost:8080
3. Si no funciona: http://127.0.0.1:8080
```

### Puerto ocupado
```
1. Cierra otros programas que usen puerto 8080
2. Reinicia tu PC si es necesario
3. Ejecuta como administrador
```

## 🎉 ¡Listo para usar!

El sistema está **100% funcional** y listo para producción con todas las funcionalidades implementadas:

✅ Gestión completa de estudiantes, maestros y materias  
✅ Sistema de asignaciones por alumno y maestro  
✅ Interfaz moderna con Bootstrap 5  
✅ Base de datos H2 integrada  
✅ Sistema de autenticación seguro  
✅ Optimizado para Windows  

---
*Garden University System v1.0 - Ejecutable para Windows*
