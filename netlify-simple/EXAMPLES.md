# 📚 Ejemplos de Uso - Garden University

## 🚀 Flujo Completo de Demostración

### 1. Acceso al Sistema
1. Visita `index.html` (página de login)
2. Observa las credenciales mostradas:
   - Usuario: `admin`
   - Contraseña: `garden2025`
3. Haz clic en las credenciales para auto-completar
4. Activa "Recordar sesión" si deseas
5. Clic en "Iniciar Sesión"

### 2. Registro de Alumnos
Una vez en el dashboard, puedes registrar alumnos de ejemplo:

#### 👨‍🎓 Alumno 1: Estudiante de Enfermería
- **Nombres:** Ana María
- **Apellidos:** González López
- **Fecha de Nacimiento:** 2000-03-15
- **CURP:** GOLA000315MDFLPN05
- **Programa:** Enfermería
- **Modalidad:** Escolarizada
- **Convenio:** ✅ Activar (asigna $1,000 automáticamente)

#### 👨‍🎓 Alumno 2: Estudiante de Derecho
- **Nombres:** Carlos Eduardo
- **Apellidos:** Ramírez Sánchez
- **Fecha de Nacimiento:** 1999-11-22
- **CURP:** RASC991122HDFMNR04
- **Programa:** Derecho
- **Modalidad:** Semiescolarizada
- **Convenio:** ❌ No activar

#### 👨‍🎓 Alumno 3: Estudiante de Maestría
- **Nombres:** María Elena
- **Apellidos:** Torres Méndez
- **Fecha de Nacimiento:** 1995-07-08
- **CURP:** TOME950708MDFRRL01
- **Programa:** Maestría en Gestión y Administración Pública
- **Modalidad:** Escolarizada
- **Convenio:** ✅ Activar

## 🔍 Características a Probar

### ✅ Funcionalidades del Login
- [ ] Auto-completar credenciales al hacer clic
- [ ] Mostrar/ocultar contraseña
- [ ] Validación de credenciales incorrectas
- [ ] Opción "Recordar sesión"
- [ ] Redirección automática si ya está autenticado

### ✅ Funcionalidades del Dashboard
- [ ] Verificación de autenticación al cargar
- [ ] Formulario de registro completo
- [ ] Validación de CURP única
- [ ] Convenio automático de $1,000
- [ ] Lista en tiempo real de alumnos
- [ ] Estadísticas actualizadas
- [ ] Botón de logout funcional

### ✅ Sistema de Autenticación
- [ ] Sesión con expiración (2 horas)
- [ ] Detección de actividad del usuario
- [ ] Protección de páginas
- [ ] Limpieza de datos al logout

## 📊 Datos de Prueba Adicionales

### 🎓 Más Estudiantes de Ejemplo

#### Bachillerato
```
Nombres: José Antonio
Apellidos: Morales Cruz
Fecha: 2005-12-03
CURP: MOCJ051203HDFRRSA1
Programa: Bachillerato
Modalidad: Escolarizada
Convenio: Sí
```

#### Ingeniería Industrial
```
Nombres: Sofía Isabel
Apellidos: Hernández Ruiz
Fecha: 1998-09-18
CURP: HERS980918MDFRRF06
Programa: Ingeniería Industrial
Modalidad: Semiescolarizada
Convenio: No
```

#### Psicología
```
Nombres: Diego Alejandro
Apellidos: Castillo Vega
Fecha: 2001-04-27
CURP: CAVD010427HDFSGD02
Programa: Psicología
Modalidad: Escolarizada
Convenio: Sí
```

## 🎯 Casos de Prueba

### 1. Prueba de Validación CURP
- Intenta registrar dos alumnos con la misma CURP
- Resultado esperado: Error "Ya existe un alumno registrado con esta CURP"

### 2. Prueba de Convenio
- Registra alumno sin convenio
- Luego registra otro con convenio
- Observa las estadísticas: contador de convenios y monto total

### 3. Prueba de Sesión
- Inicia sesión sin marcar "Recordar sesión"
- Cierra navegador y abre nuevamente
- Resultado esperado: Debe pedir login nuevamente

### 4. Prueba de Logout
- Haz clic en "Cerrar Sesión"
- Confirma la acción
- Resultado esperado: Redirección al login

### 5. Prueba de Responsividad
- Abre el sistema en dispositivo móvil
- Verifica que funciona correctamente
- Prueba todas las funcionalidades

## 📱 Compatibilidad

### ✅ Navegadores Soportados
- Chrome/Edge (recomendado)
- Firefox
- Safari
- Opera

### ✅ Dispositivos
- 💻 Desktop (Windows, Mac, Linux)
- 📱 Móvil (Android, iOS)
- 📱 Tablet

## 🔧 Personalización Rápida

### Cambiar Logo Universidad
```css
/* En index.html o dashboard.html */
.university-logo {
    background-image: url('tu-logo.png');
}
```

### Agregar Más Programas
```javascript
// En dashboard.html, buscar el select de programas
<option value="Nuevo Programa">Nuevo Programa</option>
```

### Modificar Colores
```css
/* Cambiar gradiente principal */
background: linear-gradient(135deg, #tu-color1, #tu-color2);
```

---

**🎉 ¡Disfruta probando el sistema!**  
*Todos los datos se guardan localmente en tu navegador*
