// Sistema de autenticación para Garden University
class Auth {
    static users = [
        {
            email: 'admin@garden.uni.edu',
            password: 'admin123',
            firstName: 'Admin',
            lastName: 'System',
            role: 'ADMIN'
        },
        {
            email: 'director@garden.uni.edu',
            password: 'director123',
            firstName: 'Director',
            lastName: 'Garden',
            role: 'DIRECTOR'
        }
    ];
    
    static login(email, password) {
        const user = this.users.find(u => u.email === email && u.password === password);
        if (user) {
            localStorage.setItem('currentUser', JSON.stringify({
                email: user.email,
                firstName: user.firstName,
                lastName: user.lastName,
                role: user.role
            }));
            return true;
        }
        return false;
    }
    
    static logout() {
        localStorage.removeItem('currentUser');
        window.location.href = 'index.html';
    }
    
    static isAuthenticated() {
        return localStorage.getItem('currentUser') !== null;
    }
    
    static getCurrentUser() {
        const userStr = localStorage.getItem('currentUser');
        return userStr ? JSON.parse(userStr) : null;
    }
    
    static requireAuth() {
        if (!this.isAuthenticated()) {
            window.location.href = 'index.html';
            return false;
        }
        return true;
    }
}
