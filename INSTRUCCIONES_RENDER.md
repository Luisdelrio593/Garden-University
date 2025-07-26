# 🚀 Despliegue en Render.com - Garden University Spring Boot

## ✅ **VENTAJAS DE RENDER**
- 🆓 **COMPLETAMENTE GRATIS** (hasta 750 horas/mes)
- 🎯 **CERO modificaciones** a tu código Spring Boot
- 🗄️ **Base de datos PostgreSQL gratis**
- 🔄 **Auto-deploy** desde GitHub
- 🛡️ **HTTPS automático**
- ⚡ **Muy rápido y confiable**

## 📋 **Pasos para Desplegar**

### Paso 1: Preparar el Repositorio
1. Ve a [GitHub.com](https://github.com)
2. Crea un nuevo repositorio llamado `garden-university`
3. Sube todos los archivos de tu proyecto Spring Boot actual

### Paso 2: Configurar Render
1. Ve a [Render.com](https://render.com)
2. Regístrate con tu cuenta GitHub
3. Haz click en **"New +"** → **"Web Service"**
4. Conecta tu repositorio `garden-university`
5. Configura así:

```yaml
Name: garden-university
Environment: Java
Build Command: ./mvnw clean package -DskipTests
Start Command: java -jar target/garden-university-0.0.1-SNAPSHOT.jar
```

### Paso 3: Variables de Entorno
En Render, agrega estas variables:
```
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
SPRING_DATASOURCE_URL=<La URL de PostgreSQL que Render te dará>
```

### Paso 4: Base de Datos
1. En Render, crea una **PostgreSQL Database**
2. Copia la **Internal Database URL**
3. Pégala en las variables de entorno de tu Web Service

## 🔧 **Pequeños Ajustes Necesarios**

Solo necesitas hacer estos **MÍNIMOS** cambios a tu `application.properties`:

### Para PostgreSQL en producción:
```properties
# Producción (Render)
spring.profiles.active=prod

# Desarrollo local (H2)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Producción (PostgreSQL) - se configura automáticamente con variables de entorno
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### Agregar dependencia PostgreSQL en `pom.xml`:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

## ⚡ **Resultado Final**
- ✅ Tu aplicación Spring Boot funcionará **EXACTAMENTE igual**
- ✅ Todas las características intactas
- ✅ Base de datos real (no localStorage)
- ✅ URL pública: `https://garden-university.onrender.com`
- ✅ **GRATIS PARA SIEMPRE**

## 🆚 **Render vs Netlify**

| Característica | Render | Netlify |
|---|---|---|
| Spring Boot | ✅ Sí | ❌ No |
| Base de Datos | ✅ PostgreSQL | ❌ Solo localStorage |
| Backend APIs | ✅ Completo | ❌ Solo estático |
| Sesiones | ✅ Reales | ❌ Solo localStorage |
| Seguridad | ✅ Completa | ❌ Limitada |
| **TU CÓDIGO** | ✅ **SIN MODIFICAR** | ❌ **HAY QUE REESCRIBIR** |

## 🎯 **¿Quieres que te ayude a configurarlo?**

Puedo ayudarte a:
1. Preparar tu código para Render
2. Configurar la base de datos
3. Hacer el deploy paso a paso
4. Verificar que todo funcione perfecto

**¿Procedemos con Render? Es la mejor opción para mantener tu Spring Boot intacto.**
