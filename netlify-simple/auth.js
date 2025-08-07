// Garden University Authentication System
// Archivo: auth.js

class GardenAuth {
    constructor() {
        this.SESSION_DURATION = 2 * 60 * 60 * 1000; // 2 horas en milisegundos
        this.CHECK_INTERVAL = 5 * 60 * 1000; // Verificar cada 5 minutos
        this.init();
    }

    init() {
        // Verificar sesión periódicamente
        setInterval(() => {
            this.checkSessionValidity();
        }, this.CHECK_INTERVAL);
    }

    // Verificar si el usuario está autenticado
    isAuthenticated() {
        const isAuth = localStorage.getItem('garden_auth');
        const loginTime = localStorage.getItem('garden_login_time');
        
        if (isAuth !== 'true' || !loginTime) {
            return false;
        }

        // Verificar si la sesión ha expirado
        const currentTime = new Date().getTime();
        const sessionTime = new Date(loginTime).getTime();
        const timeDiff = currentTime - sessionTime;

        if (timeDiff > this.SESSION_DURATION) {
            this.logout('Sesión expirada. Por favor, inicia sesión nuevamente.');
            return false;
        }

        return true;
    }

    // Actualizar tiempo de última actividad
    updateActivity() {
        if (this.isAuthenticated()) {
            localStorage.setItem('garden_last_activity', new Date().toISOString());
        }
    }

    // Verificar validez de la sesión
    checkSessionValidity() {
        if (!this.isAuthenticated()) {
            return;
        }

        const lastActivity = localStorage.getItem('garden_last_activity');
        if (lastActivity) {
            const currentTime = new Date().getTime();
            const activityTime = new Date(lastActivity).getTime();
            const timeDiff = currentTime - activityTime;

            // Si no hay actividad por más de 30 minutos, mostrar advertencia
            if (timeDiff > 30 * 60 * 1000) {
                const remainingTime = this.SESSION_DURATION - timeDiff;
                if (remainingTime > 0) {
                    this.showSessionWarning(Math.floor(remainingTime / 60000));
                }
            }
        }
    }

    // Mostrar advertencia de sesión
    showSessionWarning(minutesLeft) {
        if (minutesLeft > 0) {
            const message = `Tu sesión expirará en ${minutesLeft} minutos. ¿Deseas extenderla?`;
            if (confirm(message)) {
                this.extendSession();
            }
        }
    }

    // Extender sesión
    extendSession() {
        localStorage.setItem('garden_login_time', new Date().toISOString());
        localStorage.setItem('garden_last_activity', new Date().toISOString());
    }

    // Cerrar sesión
    logout(message = null) {
        // Limpiar datos de autenticación
        localStorage.removeItem('garden_auth');
        localStorage.removeItem('garden_admin_user');
        localStorage.removeItem('garden_login_time');
        localStorage.removeItem('garden_last_activity');
        
        // No eliminar garden_remember para mantener la preferencia

        if (message) {
            alert(message);
        }

        // Redirigir al login
        window.location.href = 'index.html';
    }

    // Obtener información del usuario
    getUserInfo() {
        if (!this.isAuthenticated()) {
            return null;
        }

        return {
            username: localStorage.getItem('garden_admin_user'),
            loginTime: localStorage.getItem('garden_login_time'),
            lastActivity: localStorage.getItem('garden_last_activity')
        };
    }

    // Proteger página (llamar en páginas que requieren autenticación)
    protectPage() {
        if (!this.isAuthenticated()) {
            window.location.href = 'index.html';
            return false;
        }
        
        this.updateActivity();
        return true;
    }

    // Redirigir si ya está autenticado (para página de login)
    redirectIfAuthenticated() {
        if (this.isAuthenticated()) {
            window.location.href = 'dashboard.html';
            return true;
        }
        return false;
    }
}

// Instancia global
const gardenAuth = new GardenAuth();

// Eventos para actualizar actividad
document.addEventListener('click', () => gardenAuth.updateActivity());
document.addEventListener('keypress', () => gardenAuth.updateActivity());
document.addEventListener('scroll', () => gardenAuth.updateActivity());

// Exportar para uso global
window.gardenAuth = gardenAuth;
