# Garden University - Netlify Version

Este es el sistema Garden University convertido para despliegue en Netlify, manteniendo exactamente el mismo diseño y funcionalidad del sistema Spring Boot original.

## Funcionalidades

### ✅ Completamente Funcional
- **Sistema de Autenticación**: Login con usuarios admin y director
- **Dashboard Principal**: Estadísticas y navegación
- **Gestión de Alumnos**: CRUD completo con búsqueda y filtros
- **Gestión de Docentes**: CRUD completo con funcionalidades avanzadas

### 🔧 En Desarrollo
- Gestión de Administrativos (placeholder)
- Gestión de Materias (placeholder)
- Gestión de Programas (placeholder)
- Gestión de Asignaciones (placeholder)

## Usuarios de Prueba

- **Administrador**: admin@garden.uni.edu / admin123
- **Director**: director@garden.uni.edu / director123

## Características Técnicas

- **Frontend**: HTML5, CSS3, JavaScript ES6+
- **UI Framework**: Bootstrap 5
- **Base de Datos**: LocalStorage (simulación)
- **Despliegue**: Compatible con Netlify
- **Responsive**: Diseño adaptable para todos los dispositivos

## Instrucciones de Despliegue en Netlify

1. Comprima todos los archivos en un ZIP
2. Vaya a su panel de Netlify
3. Arrastre el archivo ZIP a la sección "Deploy"
4. Su sitio estará disponible inmediatamente

## Estructura del Proyecto

```
netlify-version/
├── index.html              # Página de login
├── dashboard.html           # Panel principal
├── alumnos.html            # Gestión de estudiantes
├── docentes.html           # Gestión de docentes
├── administrativos.html     # Gestión de administrativos
├── materias.html           # Gestión de materias
├── programas.html          # Gestión de programas
├── asignaciones.html       # Gestión de asignaciones
├── js/
│   ├── auth.js             # Sistema de autenticación
│   ├── database.js         # Base de datos simulada
│   ├── students.js         # Lógica de estudiantes
│   └── teachers.js         # Lógica de docentes
└── README.md               # Este archivo
```

## Conversión Técnica

Esta versión mantiene:
- ✅ Exactamente el mismo diseño visual
- ✅ Misma navegación y UX
- ✅ Mismos colores y estilos
- ✅ Misma responsividad
- ✅ Mismas funcionalidades principales

## Soporte

El sistema utiliza tecnologías estándar web y no requiere configuración adicional para funcionar en Netlify.
