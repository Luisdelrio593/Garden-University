# 🚀 Guía de Despliegue en Netlify

## Opción 1: Despliegue Directo desde el Navegador

### Paso 1: Preparar los archivos
1. Descarga o clona este repositorio
2. Ve a la carpeta `netlify-simple`
3. Comprime todos los archivos en un ZIP:
   - `index.html` (página de login)
   - `dashboard.html` (sistema de registro)
   - `auth.js` (sistema de autenticación)
   - `README.md`
   - `_redirects`
   - `AUTH_GUIDE.md`
   - `DEPLOY.md`

### Paso 2: Desplegar en Netlify
1. Ve a [Netlify](https://www.netlify.com/)
2. Regístrate o inicia sesión
3. En el dashboard, haz clic en "Add new site" → "Deploy manually"
4. Arrastra el archivo ZIP o selecciona la carpeta `netlify-simple`
5. ¡Netlify automáticamente desplegará tu sitio!

---

## Opción 2: Despliegue desde GitHub

### Paso 1: Subir a GitHub
1. Sube todo el proyecto a tu repositorio de GitHub
2. Asegúrate de que la carpeta `netlify-simple` esté en la raíz

### Paso 2: Conectar con Netlify
1. En Netlify, clic en "Add new site" → "Import from Git"
2. Conecta tu cuenta de GitHub
3. Selecciona tu repositorio
4. Configura:
   - **Build command:** (dejar vacío)
   - **Publish directory:** `netlify-simple`
5. Clic en "Deploy site"

---

## Configuración Automática

El proyecto incluye:
- ✅ `netlify.toml` - Configuración automática
- ✅ `_redirects` - Redirecciones SPA
- ✅ Headers de seguridad
- ✅ Optimización automática

---

## URL de Ejemplo

Una vez desplegado, tu sitio estará disponible en una URL como:
```
https://garden-university-simple.netlify.app
```

---

## Características del Sitio Desplegado

### ✨ Funcionalidades
- 📱 **Responsivo** - Funciona en móviles y escritorio
- 💾 **Persistente** - Los datos se guardan en el navegador
- ⚡ **Rápido** - Carga instantánea (estático)
- 🔒 **Seguro** - Headers de seguridad configurados

### 📊 Datos que Captura
- Nombre completo del alumno
- Fecha de nacimiento
- CURP (con validación)
- Programa de estudios (Bachillerato, Licenciaturas, Maestrías)
- Modalidad (Escolarizada/Semiescolarizada)
- Convenio "Universidades del Bienestar" ($1,000 automático)

### 🎯 Programas Disponibles
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

---

## Mantenimiento

### Actualizar Contenido
1. Modifica los archivos en `netlify-simple/`
2. Si usas GitHub, solo haz push - se actualiza automáticamente
3. Si subes manual, vuelve a arrastrar los archivos

### Monitoreo
- Netlify te dará estadísticas de visitas
- Los datos de alumnos se almacenan localmente en cada navegador

---

## Soporte

Si tienes problemas:
1. Verifica que todos los archivos estén en la carpeta correcta
2. Revisa la consola del navegador para errores
3. Consulta los logs de despliegue en Netlify

---

**¡Tu sistema de registro estará funcionando en minutos!** 🎉
