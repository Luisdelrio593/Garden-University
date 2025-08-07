# Garden University - Versión Simplificada para Netlify

## 🔐 Acceso al Sistema

### Credenciales de Administrador
- **Usuario:** `admin`
- **Contraseña:** `garden2025`

⚠️ **Importante:** Estas credenciales están visibles en la página de login para facilitar la demostración.

## Descripción
Esta es una versión simplificada del sistema de registro de alumnos de Garden University, diseñada específicamente para ser desplegada en Netlify como una aplicación web estática.

## Características

## Funcionalidades

### 🔐 Sistema de Autenticación
- **Login requerido** para acceder al sistema
- **Sesión con expiración** automática (2 horas)
- **Detección de inactividad** y advertencias
- **Logout seguro** con confirmación
- **Opción "Recordar sesión"** para acceso automático

### 📋 Datos del Alumno
- **Nombre** (requerido)
- **Apellidos** (requerido)
- **Fecha de Nacimiento** (requerido)
- **CURP** (requerido, formato validado)

### 🎓 Programas Académicos
- **Bachillerato**
- **Licenciaturas:**
  - Enfermería
  - Derecho
  - Psicología
  - Gestión Empresarial
  - Ingeniería Industrial
  - Ingeniería Mecatrónica
- **Maestrías:**
  - Maestría en Gestión y Administración Pública

### 📚 Modalidades
- Escolarizada
- Semiescolarizada

### 💰 Convenio "Universidades del Bienestar"
- Checkbox especial que automáticamente asigna un pago de **$1,000.00 MXN**
- Concepto de pago: "Convenio Universidades del Bienestar"

## Funcionalidades

### ✅ Registro de Alumnos
- Formulario intuitivo con validación en tiempo real
- Validación de CURP única
- Asignación automática de pagos con convenio

### 📊 Visualización
- Lista en tiempo real de alumnos registrados
- Contador de alumnos totales
- Estadísticas de convenios activos
- Monto total de convenios

### 💾 Persistencia
- Los datos se almacenan en localStorage del navegador
- Los datos persisten entre sesiones
- Funciones de exportar/importar datos (JSON)

### 🎨 Diseño
- Interfaz moderna y responsiva
- Compatible con dispositivos móviles
- Gradientes y efectos visuales atractivos
- Iconos de Font Awesome

## Tecnologías Utilizadas
- HTML5
- CSS3 (Bootstrap 5.3.0)
- JavaScript Vanilla
- Font Awesome 6.0.0
- LocalStorage para persistencia

## Instalación y Despliegue

### Despliegue en Netlify
1. Sube la carpeta `netlify-simple` a tu repositorio
2. Conecta tu repositorio con Netlify
3. Configura la carpeta de publicación como `netlify-simple`
4. ¡Despliega!

### Uso Local
1. Abre `index.html` en tu navegador
2. El sistema funcionará completamente offline

## Estructura del Proyecto
```
netlify-simple/
├── index.html          # Página principal
├── README.md           # Este archivo
└── _redirects          # Configuración de Netlify
```

## Validaciones Implementadas
- CURP: Formato oficial mexicano (18 caracteres)
- Nombres/Apellidos: Solo letras y espacios
- Fecha de nacimiento: Formato de fecha válido
- CURP única: No permite duplicados

## Características del Convenio
- Al activar el convenio "Universidades del Bienestar":
  - Se asigna automáticamente $1,000.00 MXN
  - Se establece el concepto: "Convenio Universidades del Bienestar"
  - Se muestra visualmente con badge verde
  - Se cuenta en las estadísticas

## Diferencias con la Versión Completa
Esta versión simplificada:
- ❌ No incluye autenticación de usuarios
- ❌ No tiene base de datos backend
- ❌ No incluye campos adicionales (domicilio, contactos, etc.)
- ❌ No tiene gestión de maestros ni materias
- ✅ Mantiene solo los datos esenciales solicitados
- ✅ Es completamente funcional offline
- ✅ Perfecta para demostraciones rápidas

## Futuras Mejoras
- Exportación a PDF
- Importación desde Excel/CSV
- Filtros y búsquedas avanzadas
- Reportes estadísticos
- Integración con APIs externas

---

**Desarrollado para Garden University**  
*Versión simplificada para Netlify*
