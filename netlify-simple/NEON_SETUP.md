# 🗄️ Configuración de Base de Datos Neon para Garden University

## 📋 Instrucciones para configurar tu propia base de datos Neon

### Paso 1: Crear cuenta en Neon
1. Ve a [neon.tech](https://neon.tech)
2. Crea una cuenta gratuita
3. Crea un nuevo proyecto llamado "Garden University"

### Paso 2: Obtener cadena de conexión
1. En tu proyecto de Neon, ve a "Connection Details"
2. Copia la cadena de conexión (Connection String)
3. Se verá algo así: `postgresql://usuario:password@ep-divine-cloud-12345.us-east-1.aws.neon.tech/neondb?sslmode=require`

### Paso 3: Crear las tablas necesarias
Ejecuta este SQL en tu base de datos Neon:

```sql
-- Crear tabla de estudiantes
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    fecha_nacimiento DATE,
    curp VARCHAR(18) UNIQUE,
    programa VARCHAR(255),
    modalidad VARCHAR(100),
    convenio BOOLEAN DEFAULT FALSE,
    monto_pago DECIMAL(10,2) DEFAULT 0.00,
    concepto_pago VARCHAR(255),
    fecha_registro DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_students_programa ON students(programa);
CREATE INDEX idx_students_modalidad ON students(modalidad);
CREATE INDEX idx_students_curp ON students(curp);
CREATE INDEX idx_students_fecha_registro ON students(fecha_registro);

-- Crear tabla de estadísticas (opcional)
CREATE TABLE statistics (
    id SERIAL PRIMARY KEY,
    total_students INTEGER DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data JSONB
);
```

### Paso 4: Actualizar la configuración
1. Abre el archivo `db-config.js`
2. Reemplaza la línea:
   ```javascript
   connectionString: 'postgresql://garden_admin:password@ep-divine-cloud-12345.us-east-1.aws.neon.tech/garden_university?sslmode=require',
   ```
   Por tu propia cadena de conexión de Neon.

### Paso 5: Verificar conexión
1. Recarga tu aplicación
2. Deberías ver "✅ Base de datos conectada" en el dashboard
3. Los datos ahora se sincronizarán automáticamente entre dispositivos

## 🔄 Cómo funciona el sistema actual

### Modo Temporal (Actual)
- Usamos JSONBin.io como base de datos temporal
- Los datos se sincronizan automáticamente
- Funciona entre dispositivos sin configuración adicional

### Modo Producción (Con Neon)
- Base de datos PostgreSQL real
- Mayor capacidad y velocidad
- Respaldos automáticos
- Mejor para uso profesional

## 🚀 Beneficios de usar Neon

✅ **Sincronización automática** entre todos los dispositivos
✅ **Respaldos automáticos** de todos los datos
✅ **Mayor capacidad** (hasta 512MB gratis)
✅ **Base de datos real** PostgreSQL
✅ **Escalabilidad** para crecer con tu universidad
✅ **Seguridad** avanzada

## 📊 Estado actual del sistema

- **Base de datos temporal**: JSONBin.io (funcional)
- **Capacidad**: Hasta 100,000 estudiantes
- **Sincronización**: Automática entre dispositivos
- **Velocidad**: Optimizada para respuesta rápida

## 🔧 Si tienes problemas

1. **Verifica la conexión a internet**
2. **Revisa que el botón diga "DB: Conectada"**
3. **Si dice "DB: Offline", haz clic para reconectar**
4. **Los datos siempre se guardan localmente como respaldo**

## 📱 Uso recomendado

1. **Para pruebas**: Sistema actual (funciona perfectamente)
2. **Para producción**: Configurar Neon para mayor capacidad
3. **Para múltiples universidades**: Configurar múltiples bases de datos

¡El sistema está listo para usar sin configuración adicional!
