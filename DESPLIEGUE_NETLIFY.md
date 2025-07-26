# 🏫 Garden University - Guía de Despliegue en Netlify

## 📋 Descripción
Sistema completo de gestión universitaria desarrollado con Spring Boot 3.1.0, Spring Security, Thymeleaf y diseño responsivo con Bootstrap 5.

## 🚀 Archivos de Despliegue Generados

### 1. `garden-university-netlify-deploy.zip` (8.5KB)
**Archivo principal para arrastrar a Netlify**
- Contiene página estática de presentación
- Configuración de redirecciones
- Scripts de build automatizados
- **Recomendado para demo/presentación**

### 2. `garden-university-deploy.zip` (43MB)
**Archivo completo con aplicación Java**
- Contiene el JAR ejecutable de Spring Boot
- Código fuente completo
- **Recomendado para servidores que soporten Java**

## 🌐 Opción 1: Despliegue Estático en Netlify (Recomendado)

### Pasos:
1. Ve a [Netlify](https://netlify.com)
2. Arrastra `garden-university-netlify-deploy.zip` al área de deploy
3. ¡Listo! Tu sitio estará disponible con:
   - Página de presentación profesional
   - Información de credenciales de acceso
   - Características del sistema
   - Diseño completamente responsivo

### Características del sitio estático:
- ✅ Página de bienvenida profesional
- ✅ Información de credenciales
- ✅ Descripción de funcionalidades
- ✅ Diseño responsivo (móvil, tablet, desktop)
- ✅ Branding de Garden University
- ✅ Enlaces y navegación interactiva

## 🖥️ Opción 2: Despliegue Completo con Java

### Para servidores que soporten Java (Heroku, Railway, DigitalOcean, etc.):
1. Subir `garden-university-deploy.zip`
2. Extraer archivos
3. Ejecutar: `java -jar build/app.jar`
4. Acceder a `http://tu-dominio:8080`

## 🔑 Credenciales de Acceso

### Administrador Principal
- **Usuario:** admin@garden.uni.edu
- **Contraseña:** admin123
- **Permisos:** Acceso completo al sistema

### Director Académico
- **Usuario:** director@garden.uni.edu
- **Contraseña:** director123
- **Permisos:** Gestión académica

## 🎯 Funcionalidades Incluidas

### Gestión Completa:
- 👥 **Estudiantes:** Registro, edición, consulta de alumnos
- 👨‍🏫 **Docentes:** Administración de profesores y especialidades
- 👨‍💼 **Administrativos:** Personal de la universidad
- 🎓 **Programas Académicos:** Carreras y planes de estudio
- 📚 **Materias:** Asignaturas y contenidos curriculares
- 📝 **Asignaciones:** Docentes asignados a materias

### Características Técnicas:
- 🔐 **Seguridad:** Spring Security con roles
- 📱 **Responsivo:** Bootstrap 5 + CSS personalizado
- 💾 **Base de Datos:** H2 en memoria (configurable)
- 🚀 **Framework:** Spring Boot 3.1.0
- 🎨 **UI/UX:** Thymeleaf + Font Awesome

## 📱 Compatibilidad Responsiva

### Dispositivos Soportados:
- 📱 **Móviles:** 320px - 575px
- 📱 **Tablets:** 576px - 767px
- 💻 **Desktop:** 768px+

### Optimizaciones:
- Navegación colapsable en móviles
- Formularios adaptados por pantalla
- Botones y texto escalables
- Imágenes y contenido optimizado

## 🛠️ Desarrollo Local

### Requisitos:
- Java 17+
- Maven 3.8+

### Comandos:
```bash
# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target/garden-university-system-0.0.1-SNAPSHOT.jar

# Acceder
http://localhost:8080
```

## 📞 Soporte

Para cualquier consulta o personalización adicional:
- El sistema está completamente funcional
- Código fuente disponible en el ZIP completo
- Base de datos inicializada automáticamente
- Usuarios de prueba creados al inicio

---

**🌱 Garden University © 2025**  
*Sistema de Gestión Universitaria desarrollado con Spring Boot*
