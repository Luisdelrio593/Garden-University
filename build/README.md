# Garden University - Sistema de Gestión Universitaria

Sistema web desarrollado en Java con Spring Boot para la administración de una universidad.

## Características

- **Autenticación segura**: Solo usuarios con email terminado en `@garden.uni.edu`
- **Panel de administración** con acceso a:
  - Gestión de Alumnos
  - Gestión de Docentes  
  - Personal Administrativo
  - Programas de Estudio
- **Interfaz moderna** con Bootstrap y FontAwesome
- **Base de datos** H2 en memoria (para desarrollo)
- **Seguridad** implementada con Spring Security

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.1.0
- Spring Security
- Spring Data JPA
- Thymeleaf (Motor de plantillas)
- Bootstrap 5.1.3
- FontAwesome 6.0.0
- Base de datos H2

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Instalación y Ejecución

1. **Clonar o descargar el proyecto**

2. **Navegar al directorio del proyecto**
   ```bash
   cd Garden-University
   ```

3. **Compilar y ejecutar con Maven**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - URL: http://localhost:8080
   - Se redirige automáticamente a la página de login

## Usuarios de Prueba

El sistema incluye usuarios preconfigurados:

### Usuario Administrador
- **Email**: `admin@garden.uni.edu`
- **Contraseña**: `admin123`

### Usuario Director
- **Email**: `director@garden.uni.edu`
- **Contraseña**: `director123`

## Acceso a la Base de Datos (Desarrollo)

Para acceder a la consola H2 durante el desarrollo:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:gardenuniversity`
- Usuario: `sa`
- Contraseña: `password`

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/garden/university/
│   │   ├── config/          # Configuraciones (Security, Data Initializer)
│   │   ├── controller/      # Controladores REST
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositorios de datos
│   │   ├── service/        # Lógica de negocio
│   │   └── GardenUniversityApplication.java
│   └── resources/
│       ├── templates/      # Plantillas Thymeleaf
│       └── application.properties
```

## Funcionalidades

### Panel de Login
- Validación de email con dominio `@garden.uni.edu`
- Autenticación segura con Spring Security
- Mensajes de error informativos

### Dashboard Principal
- Estadísticas rápidas del sistema
- Navegación a los diferentes módulos
- Información del usuario logueado

### Módulos de Gestión
- **Alumnos**: Lista, búsqueda, filtros, formularios de alta
- **Docentes**: Gestión de profesores por facultad y especialidad
- **Administrativos**: Personal por departamento y cargo
- **Programas**: Carreras universitarias con detalles académicos

## Personalización

### Agregar Nuevos Usuarios
Los usuarios se pueden agregar desde el `DataInitializer.java` o mediante la consola H2.

### Modificar Estilos
Los estilos están definidos inline en cada plantilla HTML para facilitar la personalización.

### Configurar Base de Datos
Para usar una base de datos persistente, modificar `application.properties`:

```properties
# Ejemplo para MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/garden_university
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## Desarrollo

### Compilar
```bash
mvn clean compile
```

### Ejecutar Tests
```bash
mvn test
```

### Generar JAR
```bash
mvn clean package
```

### Ejecutar JAR
```bash
java -jar target/garden-university-system-0.0.1-SNAPSHOT.jar
```

## Próximas Mejoras

- Implementar CRUD completo para todas las entidades
- Agregar reportes y estadísticas avanzadas
- Sistema de notificaciones
- API REST para integración móvil
- Carga de archivos y documentos
- Sistema de roles más granular

## Soporte

Para soporte técnico o reportar issues, contactar al equipo de desarrollo.

---

**Garden University System** - Desarrollado con ❤️ en Java Spring Boot
