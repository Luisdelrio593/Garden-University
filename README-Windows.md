# Garden University System - Versión Ejecutable para Windows

## 🎓 Sistema de Gestión Universitaria

Este es el sistema completo de Garden University listo para ejecutar en Windows.

## 📋 Requisitos

- **Java 17 o superior** (descargar desde: https://adoptium.net/)
- **Windows 7/8/10/11**
- **4 GB de RAM mínimo**

## 🚀 Instrucciones de Instalación y Uso

### 1. Verificar Java
Abre el **Símbolo del sistema** (cmd) y ejecuta:
```
java --version
```
Si no tienes Java instalado, descárgalo desde https://adoptium.net/

### 2. Ejecutar el Sistema
1. Haz **doble clic** en `ejecutar.bat`
2. Espera a que aparezca el mensaje "Started GardenUniversityApplication"
3. Abre tu navegador en: **http://localhost:8080**

### 3. Usuarios de Prueba
- **Administrador**: 
  - Usuario: `admin@garden.uni.edu`
  - Contraseña: `admin123`
  
- **Director**: 
  - Usuario: `director@garden.uni.edu`
  - Contraseña: `director123`

## 🎯 Funcionalidades

✅ **Panel de Administración**
- Gestión de estudiantes
- Gestión de maestros
- Gestión de materias
- Gestión de programas de estudio

✅ **Sistema de Asignaciones**
- Asignaciones por alumno y maestro
- Materias organizadas por grupos y programas
- Interfaz con pestañas separadas

✅ **Seguridad**
- Sistema de autenticación
- Roles de usuario diferenciados
- Sesiones seguras

## 🔧 Solución de Problemas

### El sistema no inicia
1. Verifica que Java esté instalado: `java --version`
2. Asegúrate de que el puerto 8080 esté libre
3. Ejecuta como administrador si es necesario

### No puedo acceder desde el navegador
1. Espera a que aparezca "Started GardenUniversityApplication" en la consola
2. Ve a: http://localhost:8080
3. Si no funciona, prueba: http://127.0.0.1:8080

### El sistema se cierra inmediatamente
1. Abre el **Símbolo del sistema** como administrador
2. Navega hasta la carpeta del sistema
3. Ejecuta: `java -jar GardenUniversity.jar`
4. Revisa los mensajes de error

## 📞 Soporte

Si tienes problemas:
1. Verifica que Java 17+ esté instalado
2. Asegúrate de que no hay otros programas usando el puerto 8080
3. Ejecuta el archivo .bat como administrador

## 🛑 Para Detener el Sistema

Presiona **Ctrl + C** en la ventana de comandos o simplemente cierra la ventana.

---

**¡Listo para usar!** 🎉
