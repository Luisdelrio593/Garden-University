# 🏫 Garden University - Versión JavaScript

## 🌟 Sistema Completo que SÍ Funciona en Netlify

Esta es la versión **JavaScript puro** del sistema Garden University que funciona **completamente en Netlify** sin necesidad de servidor Java.

## 🚀 Características

### ✅ **Funcionalidades Completas:**
- 🔐 **Login funcional** con credenciales reales
- 📊 **Dashboard interactivo** con estadísticas
- 👥 **Gestión de estudiantes** (CRUD completo)
- 👨‍🏫 **Administración de docentes**
- 👨‍💼 **Personal administrativo**
- 🎓 **Programas académicos**
- 📚 **Gestión de materias**
- 📱 **100% Responsivo**

### ✅ **Tecnologías:**
- **JavaScript puro** (sin frameworks)
- **Bootstrap 5** para UI responsivo
- **LocalStorage** como base de datos
- **Font Awesome** para iconos
- **Compatible con Netlify**

## 🔑 Credenciales de Acceso

```
👨‍💼 Administrador:
Email: admin@garden.uni.edu
Contraseña: admin123

👨‍🎓 Director:
Email: director@garden.uni.edu
Contraseña: director123
```

## 🌐 Deploy en Netlify

### Opción 1: Arrastra la carpeta `js-version`
1. Ve a [netlify.com](https://netlify.com)
2. Arrastra toda la carpeta `js-version` al área de deploy
3. ¡Listo! Tu sitio estará funcionando completamente

### Opción 2: Desde GitHub
1. Sube este código a GitHub
2. En Netlify: "New site from Git"
3. Selecciona el repositorio
4. **Publish directory:** `js-version`
5. Deploy

## 📁 Estructura del Proyecto

```
js-version/
├── index.html          # Login
├── dashboard.html      # Dashboard principal
├── package.json        # Configuración del proyecto
├── netlify.toml        # Configuración de Netlify
└── js/
    ├── database.js     # Base de datos en localStorage
    ├── app.js          # Funciones principales
    ├── students.js     # Gestión de estudiantes
    ├── teachers.js     # Gestión de docentes
    ├── administratives.js
    ├── programs.js
    └── subjects.js
```

## 🎯 Datos de Demostración

El sistema incluye datos iniciales:
- 3 estudiantes de ejemplo
- 2 docentes
- 1 administrativo
- 3 programas académicos
- 3 materias

## 📱 Responsive Design

- 📱 **Móviles:** Sidebar colapsable, navegación optimizada
- 📱 **Tablets:** Layout adaptado
- 💻 **Desktop:** Experiencia completa

## 🔧 Desarrollo Local

```bash
# Instalar dependencias (opcional)
npm install

# Servidor local
npm run dev
# o simplemente abrir index.html en el navegador
```

## 🆚 Diferencias con la versión Java

| Característica | Versión Java | Versión JavaScript |
|---|---|---|
| **Servidor** | Spring Boot | No requiere servidor |
| **Base de datos** | H2/MySQL | LocalStorage |
| **Deploy** | Heroku/Railway | Netlify ✅ |
| **Login** | Spring Security | JavaScript nativo ✅ |
| **CRUD** | JPA | JavaScript ✅ |
| **Responsivo** | Thymeleaf + CSS | Bootstrap 5 ✅ |

## ✨ Ventajas de esta versión

1. **🚀 Deploy inmediato** en Netlify
2. **💰 Totalmente gratuito**
3. **⚡ Carga rápida** (sin servidor)
4. **📱 100% funcional** en móviles
5. **🔧 Fácil de personalizar**
6. **💾 Persistencia** con localStorage

---

**🌱 Garden University JavaScript Edition**  
*Sistema completo de gestión universitaria que funciona en cualquier hosting estático*
