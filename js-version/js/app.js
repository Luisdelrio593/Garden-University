// Sistema de autenticación
class AuthManager {
    constructor() {
        this.currentUser = this.getCurrentUser();
    }

    login(email, password) {
        const user = db.authenticateUser(email, password);
        if (user) {
            sessionStorage.setItem('garden_current_user', JSON.stringify(user));
            this.currentUser = user;
            return true;
        }
        return false;
    }

    logout() {
        sessionStorage.removeItem('garden_current_user');
        this.currentUser = null;
        window.location.href = 'index.html';
    }

    getCurrentUser() {
        const userStr = sessionStorage.getItem('garden_current_user');
        return userStr ? JSON.parse(userStr) : null;
    }

    isAuthenticated() {
        return this.currentUser !== null;
    }

    hasRole(role) {
        return this.currentUser && this.currentUser.role === role;
    }

    requireAuth() {
        if (!this.isAuthenticated()) {
            window.location.href = 'index.html';
            return false;
        }
        return true;
    }
}

// Instancia global del manejador de autenticación
const auth = new AuthManager();

// Funciones de utilidad para el UI
function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('.container') || document.body;
    container.insertBefore(alertDiv, container.firstChild);
    
    // Auto-remove after 5 seconds
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-ES');
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP'
    }).format(amount);
}

// Validaciones
function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function validateRequired(value) {
    return value && value.trim().length > 0;
}

function validatePhone(phone) {
    const phoneRegex = /^[\d\-\+\(\)\s]+$/;
    return phoneRegex.test(phone);
}

// Función para cargar contenido dinámico
function loadContent(section) {
    const contentArea = document.getElementById('content-area');
    
    switch(section) {
        case 'dashboard':
            contentArea.innerHTML = getDashboardContent();
            break;
        case 'students':
            contentArea.innerHTML = getStudentsContent();
            loadStudentsData();
            break;
        case 'teachers':
            contentArea.innerHTML = getTeachersContent();
            loadTeachersData();
            break;
        case 'administratives':
            contentArea.innerHTML = getAdministrativesContent();
            loadAdministrativesData();
            break;
        case 'programs':
            contentArea.innerHTML = getProgramsContent();
            loadProgramsData();
            break;
        case 'subjects':
            contentArea.innerHTML = getSubjectsContent();
            loadSubjectsData();
            break;
        default:
            contentArea.innerHTML = getDashboardContent();
    }
}

// Templates de contenido
function getDashboardContent() {
    const students = db.getStudents();
    const teachers = db.getTeachers();
    const programs = db.getPrograms();
    const subjects = db.getSubjects();

    return `
        <div class="row">
            <div class="col-12">
                <h2 class="text-success fw-bold mb-4">
                    <i class="fas fa-tachometer-alt me-2"></i>
                    Dashboard - Garden University
                </h2>
            </div>
        </div>
        
        <div class="row">
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card bg-primary text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="fw-bold">${students.length}</h4>
                                <span>Estudiantes</span>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-users fa-2x"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card bg-success text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="fw-bold">${teachers.length}</h4>
                                <span>Docentes</span>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-chalkboard-teacher fa-2x"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card bg-warning text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="fw-bold">${programs.length}</h4>
                                <span>Programas</span>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-graduation-cap fa-2x"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card bg-info text-white">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h4 class="fw-bold">${subjects.length}</h4>
                                <span>Materias</span>
                            </div>
                            <div class="align-self-center">
                                <i class="fas fa-book fa-2x"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h5 class="card-title mb-0">
                            <i class="fas fa-info-circle me-2"></i>
                            Bienvenido al Sistema de Gestión - Garden University
                        </h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                            Sistema completo de gestión universitaria desarrollado en JavaScript. 
                            Utiliza el menú lateral para navegar entre las diferentes secciones.
                        </p>
                        <div class="row">
                            <div class="col-md-6">
                                <h6 class="text-success">Funcionalidades disponibles:</h6>
                                <ul>
                                    <li>Gestión de estudiantes</li>
                                    <li>Administración de docentes</li>
                                    <li>Control de personal administrativo</li>
                                    <li>Programas académicos</li>
                                    <li>Materias y asignaturas</li>
                                </ul>
                            </div>
                            <div class="col-md-6">
                                <h6 class="text-success">Usuario actual:</h6>
                                <p><strong>Nombre:</strong> ${auth.currentUser.name}</p>
                                <p><strong>Email:</strong> ${auth.currentUser.email}</p>
                                <p><strong>Rol:</strong> ${auth.currentUser.role}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
}
