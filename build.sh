#!/bin/bash

echo "🏫 Garden University - Netlify Build Script Actualizado"
echo "======================================================="

# Variables
APP_NAME="garden-university-system"
VERSION="0.0.1-SNAPSHOT"
BUILD_DIR="build"
STATIC_DIR="static"

echo "📦 Paso 1: Limpieza de directorios anteriores"
rm -rf $BUILD_DIR
rm -rf $STATIC_DIR
mkdir -p $BUILD_DIR
mkdir -p $STATIC_DIR

echo "🔨 Paso 2: Compilación con Maven"
mvn clean package -DskipTests -q

if [ $? -ne 0 ]; then
    echo "❌ Error en la compilación Maven"
    exit 1
fi

echo "📁 Paso 3: Creación de estructura para Netlify"

# Crear archivo JAR ejecutable
cp target/$APP_NAME-$VERSION.jar $BUILD_DIR/app.jar

# Crear script de inicio
cat > $BUILD_DIR/start.sh << 'EOF'
#!/bin/bash
export PORT=${PORT:-8080}
export SPRING_PROFILES_ACTIVE=prod
export DATABASE_URL=${DATABASE_URL:-"jdbc:h2:mem:garden_university"}
java -Dserver.port=$PORT -jar app.jar
EOF

chmod +x $BUILD_DIR/start.sh

# Crear página estática de bienvenida para Netlify
cat > $STATIC_DIR/index.html << 'EOF'
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Garden University - Sistema de Gestión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .hero-section {
            background: linear-gradient(135deg, #2e7d32, #4caf50);
            color: white;
            padding: 100px 0;
            text-align: center;
        }
        .feature-card {
            transition: transform 0.3s;
            border: none;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .feature-card:hover {
            transform: translateY(-5px);
        }
        .btn-garden {
            background-color: #2e7d32;
            border-color: #2e7d32;
            color: white;
        }
        .btn-garden:hover {
            background-color: #1b5e20;
            border-color: #1b5e20;
            color: white;
        }
        @media (max-width: 768px) {
            .hero-section {
                padding: 60px 0;
            }
            .hero-section h1 {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
    <div class="hero-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h1 class="display-4 fw-bold mb-4">
                        <i class="fas fa-university me-3"></i>
                        Garden University
                    </h1>
                    <p class="lead mb-4">Sistema Integral de Gestión Universitaria</p>
                    <p class="mb-4">Plataforma completa para la administración de estudiantes, docentes, programas académicos y más.</p>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                        <button class="btn btn-light btn-lg px-4 me-md-2" onclick="showLoginInfo()">
                            <i class="fas fa-sign-in-alt me-2"></i>
                            Acceso al Sistema
                        </button>
                        <button class="btn btn-outline-light btn-lg px-4" onclick="showFeatures()">
                            <i class="fas fa-info-circle me-2"></i>
                            Características
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container my-5">
        <div id="loginInfo" class="row justify-content-center" style="display: none;">
            <div class="col-lg-8">
                <div class="card border-success">
                    <div class="card-header bg-success text-white">
                        <h5 class="card-title mb-0">
                            <i class="fas fa-key me-2"></i>
                            Credenciales de Acceso
                        </h5>
                    </div>
                    <div class="card-body">
                        <div class="alert alert-info">
                            <strong>Nota:</strong> Esta es una aplicación Spring Boot desplegada en Netlify. 
                            Para un funcionamiento completo, se requiere un servidor Java.
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <h6 class="fw-bold text-success">👨‍💼 Administrador</h6>
                                <p class="mb-1"><strong>Usuario:</strong> admin@garden.uni.edu</p>
                                <p class="mb-3"><strong>Contraseña:</strong> admin123</p>
                            </div>
                            <div class="col-md-6">
                                <h6 class="fw-bold text-success">👨‍🎓 Director</h6>
                                <p class="mb-1"><strong>Usuario:</strong> director@garden.uni.edu</p>
                                <p class="mb-3"><strong>Contraseña:</strong> director123</p>
                            </div>
                        </div>
                        <div class="text-center mt-3">
                            <p class="text-muted">Para desarrollo local, ejecutar: <code>java -jar app.jar</code></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="features" class="row" style="display: none;">
            <div class="col-12 text-center mb-5">
                <h2 class="text-success fw-bold">Características del Sistema</h2>
                <p class="text-muted">Sistema completo de gestión universitaria con diseño responsivo</p>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-users fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Gestión de Estudiantes</h5>
                        <p class="card-text">Administra información completa de estudiantes, inscripciones y expedientes académicos.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-chalkboard-teacher fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Gestión de Docentes</h5>
                        <p class="card-text">Control de profesores, especialidades, asignaciones de materias y horarios.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-graduation-cap fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Programas Académicos</h5>
                        <p class="card-text">Administración de carreras, planes de estudio y programas universitarios.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-book fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Gestión de Materias</h5>
                        <p class="card-text">Control de asignaturas, prerrequisitos y contenidos curriculares.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-user-tie fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Personal Administrativo</h5>
                        <p class="card-text">Gestión de empleados administrativos y estructura organizacional.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card feature-card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-mobile-alt fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Diseño Responsivo</h5>
                        <p class="card-text">Interfaz adaptable para dispositivos móviles, tablets y computadoras.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="bg-success text-white text-center py-4 mt-5">
        <div class="container">
            <p class="mb-0">
                <i class="fas fa-university me-2"></i>
                Garden University © 2025 - Sistema de Gestión Universitaria
            </p>
            <p class="mt-2 mb-0">
                <small>Desarrollado con Spring Boot, Spring Security y Bootstrap</small>
            </p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function showLoginInfo() {
            document.getElementById('loginInfo').style.display = 'block';
            document.getElementById('features').style.display = 'none';
            document.getElementById('loginInfo').scrollIntoView({ behavior: 'smooth' });
        }

        function showFeatures() {
            document.getElementById('features').style.display = 'block';
            document.getElementById('loginInfo').style.display = 'none';
            document.getElementById('features').scrollIntoView({ behavior: 'smooth' });
        }
    </script>
</body>
</html>
EOF

# Crear archivo de redirección para Spring Boot
cat > $STATIC_DIR/_redirects << 'EOF'
# Redirecciones para Spring Boot en Netlify
/api/* http://localhost:8080/api/:splat 200
/login http://localhost:8080/login 200
/dashboard http://localhost:8080/dashboard 200
/alumnos http://localhost:8080/alumnos 200
/docentes http://localhost:8080/docentes 200
/administrativos http://localhost:8080/administrativos 200
/programas http://localhost:8080/programas 200
/materias http://localhost:8080/materias 200
/asignaciones http://localhost:8080/asignaciones 200

# Fallback para SPA
/* /index.html 200
EOF

echo "📋 Paso 4: Copiando archivos de configuración"

# Copiar archivos importantes al directorio build
cp pom.xml $BUILD_DIR/
cp -r src $BUILD_DIR/
cp README.md $BUILD_DIR/ 2>/dev/null || echo "README.md no encontrado"

echo "✅ Paso 5: Build completado exitosamente"
echo ""
echo "📁 Archivos generados:"
echo "   - $BUILD_DIR/app.jar (Aplicación ejecutable)"
echo "   - $BUILD_DIR/start.sh (Script de inicio)"
echo "   - $STATIC_DIR/index.html (Página de bienvenida)"
echo "   - $STATIC_DIR/_redirects (Configuración de rutas)"
echo ""
echo "🚀 Para desplegar en Netlify:"
echo "   1. Comprimir carpeta 'static' en un ZIP"
echo "   2. Arrastrar el ZIP a Netlify"
echo "   3. Para funcionalidad completa, usar un servicio que soporte Java"
echo ""
echo "🔧 Para desarrollo local:"
echo "   cd $BUILD_DIR && ./start.sh"
