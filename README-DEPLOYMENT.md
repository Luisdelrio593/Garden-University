# 🌱 Garden University - Sistema de Gestión Académica

## 📋 Descripción
Sistema integral de gestión académica para Garden University, desarrollado con Spring Boot 3.1.0, que incluye gestión de estudiantes, docentes, personal administrativo, programas académicos y asignación de materias.

## 🚀 Características
- ✅ **Gestión de Estudiantes**: CRUD completo con inscripciones y seguimiento académico
- ✅ **Gestión de Docentes**: Administración de personal académico y asignaciones
- ✅ **Personal Administrativo**: Sistema de roles jerárquicos (Subdirección, Coordinaciones)
- ✅ **Programas Académicos**: Gestión de carreras, licenciaturas y especializaciones
- ✅ **Gestión de Materias**: Administración de asignaturas por cuatrimestre
- ✅ **Asignación de Materias**: Sistema de asignación docente-materia
- ✅ **Diseño Responsive**: Optimizado para PC, tablet y móviles
- ✅ **Seguridad**: Spring Security con autenticación basada en roles

## 🛠️ Tecnologías
- **Backend**: Java 17, Spring Boot 3.1.0, Spring Security
- **Frontend**: Thymeleaf, Bootstrap 5.1.3, Font Awesome 6.0
- **Base de Datos**: H2 Database (desarrollo), configurable para producción
- **Build**: Maven 3.8+

## 📱 Diseño Responsive
El sistema está completamente optimizado para diferentes dispositivos:

### 💻 PC/Desktop (>768px)
- Layout completo con sidebar y navegación extendida
- Tablas con todas las columnas visibles
- Modales de tamaño completo

### 📱 Tablet (768px - 576px)
- Navegación adaptativa con elementos colapsables
- Tablas responsive con scroll horizontal
- Botones y formularios optimizados

### 📱 Móvil (<576px)
- Navegación hamburger
- Cards apiladas verticalmente
- Formularios con inputs de 16px (evita zoom en iOS)
- Botones y textos adaptados al tamaño de pantalla

## 🌐 Deploy en Netlify

### Opción 1: Deploy Automático desde GitHub
1. **Conectar repositorio a Netlify:**
   - Ve a [netlify.com](https://netlify.com)
   - Click en "New site from Git"
   - Conecta tu repositorio de GitHub
   - Selecciona la rama principal

2. **Configuración de Build:**
   ```
   Build command: ./build.sh
   Publish directory: deploy
   ```

3. **Variables de entorno en Netlify:**
   ```
   JAVA_VERSION=17
   MAVEN_VERSION=3.8.6
   SPRING_PROFILES_ACTIVE=prod
   ```

### Opción 2: Deploy Manual
1. **Ejecutar build local:**
   ```bash
   chmod +x build.sh
   ./build.sh
   ```

2. **Subir archivos:**
   - Sube el contenido de la carpeta `deploy/` a Netlify
   - O usa Netlify CLI:
   ```bash
   npm install -g netlify-cli
   netlify deploy --prod --dir=deploy
   ```

### Configuración de Production
El archivo `application-prod.properties` debe incluir:
```properties
# Base de datos de producción (configura según tu proveedor)
spring.datasource.url=jdbc:postgresql://localhost:5432/garden_university
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# Configuración de servidor
server.port=${PORT:8080}
spring.jpa.hibernate.ddl-auto=validate
```

## 🔧 Desarrollo Local

### Requisitos Previos
- Java 17+
- Maven 3.8+
- Git

### Instalación
```bash
# Clonar repositorio
git clone <tu-repositorio>
cd Garden-University

# Ejecutar aplicación
./mvnw spring-boot:run
```

### Acceso Local
- **URL**: http://localhost:8080
- **Usuario Admin**: admin@garden.edu
- **Contraseña**: admin123

## 👥 Roles y Permisos

### SUBDIRECCION
- Acceso completo al sistema
- Gestión de personal administrativo
- Supervisión general

### COORDINACION_ACADEMICA
- Gestión de programas académicos
- Gestión de docentes y estudiantes
- Asignación de materias

### COORDINACION_SERVICIOS_ESCOLARES
- Gestión de estudiantes
- Gestión de materias
- Reportes académicos

## 📝 Estructura del Proyecto
```
Garden-University/
├── src/main/java/com/garden/university/
│   ├── config/          # Configuración de seguridad
│   ├── controller/      # Controladores REST
│   ├── entity/          # Entidades JPA
│   ├── repository/      # Repositorios de datos
│   └── service/         # Lógica de negocio
├── src/main/resources/
│   ├── templates/       # Plantillas Thymeleaf
│   ├── static/          # Recursos estáticos
│   └── application.properties
├── netlify.toml         # Configuración Netlify
├── build.sh            # Script de build
└── pom.xml             # Dependencias Maven
```

## 🚀 Features Implementadas

### ✅ Responsive Design Completo
- Dashboard adaptativo con navegación responsive
- Todas las páginas optimizadas para móviles
- Media queries para diferentes breakpoints
- Formularios optimizados para touch

### ✅ Branding Consistente
- Todas las pestañas muestran "Garden University"
- Colores y tipografía unificados
- Iconografía consistente

### ✅ Deployment Ready
- Configuración completa para Netlify
- Scripts de build automatizados
- Variables de entorno configuradas

## 🔄 Próximas Mejoras
- [ ] Base de datos en la nube (PostgreSQL/MySQL)
- [ ] Reportes PDF avanzados
- [ ] Sistema de notificaciones
- [ ] API REST completa
- [ ] Aplicación móvil nativa

## 📞 Soporte
Para soporte técnico o consultas sobre el sistema, contacta al equipo de desarrollo.

---
**Garden University - Sistema de Gestión Académica** 🌱  
*Desarrollado con ❤️ para la educación superior*
