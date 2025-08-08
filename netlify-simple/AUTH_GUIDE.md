# 🔐 Sistema de Autenticación - Garden University

## Credenciales de Acceso

### 👨‍💼 Administrador Principal
- **Usuario:** `admin`
- **Contraseña:** `garden2025`
- **Permisos:** Acceso completo al sistema

### 🔧 Configuración del Sistema

#### 🕐 Duración de Sesión
- **Tiempo activo:** 2 horas
- **Verificación automática:** Cada 5 minutos
- **Advertencia de expiración:** 30 minutos antes

#### 🔒 Características de Seguridad
- ✅ Autenticación requerida para acceder al dashboard
- ✅ Sesiones con tiempo de expiración automático
- ✅ Detección de inactividad
- ✅ Opción "Recordar sesión"
- ✅ Logout seguro con confirmación

#### 📱 Funcionalidades
- **Login automático:** Si se selecciona "Recordar sesión"
- **Redirección inteligente:** Los usuarios autenticados van directo al dashboard
- **Protección de rutas:** Las páginas protegidas redirigen al login
- **Actividad del usuario:** Se detecta clicks, teclas y scroll

## 🚀 Flujo de Autenticación

### 1. Acceso Inicial
```
index.html (Login) → Verificar credenciales → dashboard.html
```

### 2. Sesión Activa
```
Cualquier página → Verificar autenticación → Permitir acceso / Redirigir login
```

### 3. Logout
```
dashboard.html → Botón logout → Confirmar → Limpiar datos → index.html
```

## 📂 Archivos del Sistema

### `index.html`
- Página de login principal
- Formulario de autenticación
- Credenciales de demostración visibles
- Diseño moderno y responsivo

### `dashboard.html`
- Sistema de registro de alumnos
- Barra de navegación con logout
- Información del usuario autenticado
- Protección de acceso

### `auth.js`
- Clase GardenAuth para manejo de sesiones
- Verificación automática de expiración
- Métodos de protección de páginas
- Gestión de actividad del usuario

## 🎨 Características de Diseño

### 🌈 Página de Login
- Gradientes modernos y efectos visuales
- Formas flotantes animadas
- Tarjeta de credenciales visible
- Botón para mostrar/ocultar contraseña
- Responsivo para móviles

### 📊 Dashboard
- Navbar con información del usuario
- Botón de logout prominente
- Diseño consistente con el login
- Funcionalidad completa de registro

## 🔧 Personalización

### Cambiar Credenciales
```javascript
// En index.html, línea ~200
const ADMIN_CREDENTIALS = {
    username: 'admin',
    password: 'garden2025'
};
```

### Ajustar Tiempo de Sesión
```javascript
// En auth.js, línea ~6
this.SESSION_DURATION = 2 * 60 * 60 * 1000; // 2 horas
```

### Agregar Nuevos Usuarios
```javascript
// Extender el sistema para múltiples usuarios
const USERS_DATABASE = {
    'admin': { password: 'garden2025', role: 'admin' },
    'director': { password: 'director123', role: 'director' },
    'secretaria': { password: 'secret456', role: 'secretary' }
};
```

## 🚨 Seguridad

### ⚠️ Importante para Producción
- Cambiar credenciales por defecto
- Implementar hash de contraseñas
- Agregar validación en servidor
- Usar HTTPS obligatorio
- Implementar rate limiting

### 🛡️ Medidas Actuales
- Validación en frontend
- Sesiones con expiración
- Limpieza automática de datos
- Confirmación antes de logout

---

**💡 Tip:** Las credenciales se muestran en la página de login para facilidad de demostración. En producción, estas deben ser privadas y seguras.
