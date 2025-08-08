// Módulo para gestión de docentes
class Teachers {
    static currentEditId = null;
    
    static init() {
        this.loadTeachers();
        this.setupEventListeners();
    }
    
    static setupEventListeners() {
        // Search functionality
        document.getElementById('searchInput').addEventListener('input', () => {
            this.filterTeachers();
        });
        
        // Form submission
        document.getElementById('teacherForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.saveTeacher();
        });
    }
    
    static loadTeachers() {
        const teachers = Database.getAll('teachers');
        this.renderTeachers(teachers);
    }
    
    static renderTeachers(teachers) {
        const tbody = document.getElementById('teachersTable');
        
        if (teachers.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="7" class="text-center py-4">
                        <i class="fas fa-chalkboard-teacher fa-3x text-muted mb-3"></i>
                        <p class="text-muted">No hay docentes registrados</p>
                    </td>
                </tr>
            `;
            return;
        }
        
        tbody.innerHTML = teachers.map(teacher => `
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.nombres} ${teacher.apellidos}</td>
                <td>
                    <small class="text-muted">${teacher.correoInstitucional}</small>
                </td>
                <td>
                    <small>${teacher.gradoEstudio || 'No especificado'}</small>
                </td>
                <td>
                    <small>${teacher.cedulaProfesional || 'N/A'}</small>
                </td>
                <td>
                    <span class="badge ${teacher.activo ? 'bg-success' : 'bg-danger'}">
                        ${teacher.activo ? 'Activo' : 'Inactivo'}
                    </span>
                </td>
                <td class="action-buttons">
                    <button class="btn btn-outline-primary btn-sm" onclick="Teachers.viewTeacher(${teacher.id})" title="Ver detalles">
                        <i class="fas fa-eye"></i>
                    </button>
                    <button class="btn btn-outline-warning btn-sm" onclick="Teachers.editTeacher(${teacher.id})" title="Editar">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-outline-danger btn-sm" onclick="Teachers.deleteTeacher(${teacher.id})" title="Eliminar">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        `).join('');
    }
    
    static filterTeachers() {
        const searchTerm = document.getElementById('searchInput').value.toLowerCase();
        
        let teachers = Database.getAll('teachers');
        
        if (searchTerm) {
            teachers = teachers.filter(teacher => 
                teacher.nombres.toLowerCase().includes(searchTerm) ||
                teacher.apellidos.toLowerCase().includes(searchTerm) ||
                teacher.correoInstitucional.toLowerCase().includes(searchTerm) ||
                (teacher.gradoEstudio && teacher.gradoEstudio.toLowerCase().includes(searchTerm)) ||
                (teacher.cedulaProfesional && teacher.cedulaProfesional.toLowerCase().includes(searchTerm))
            );
        }
        
        this.renderTeachers(teachers);
    }
    
    static clearFilters() {
        document.getElementById('searchInput').value = '';
        this.loadTeachers();
    }
    
    static openAddModal() {
        this.currentEditId = null;
        document.getElementById('modalTitle').innerHTML = '<i class="fas fa-user-plus me-2"></i>Nuevo Docente';
        document.getElementById('teacherForm').reset();
        new bootstrap.Modal(document.getElementById('teacherModal')).show();
    }
    
    static editTeacher(id) {
        const teacher = Database.getById('teachers', id);
        if (!teacher) return;
        
        this.currentEditId = id;
        document.getElementById('modalTitle').innerHTML = '<i class="fas fa-user-edit me-2"></i>Editar Docente';
        
        // Fill form with teacher data
        document.getElementById('nombres').value = teacher.nombres || '';
        document.getElementById('apellidos').value = teacher.apellidos || '';
        document.getElementById('correoInstitucional').value = teacher.correoInstitucional || '';
        document.getElementById('correoPersonal').value = teacher.correoPersonal || '';
        document.getElementById('celular').value = teacher.celular || '';
        document.getElementById('cedulaProfesional').value = teacher.cedulaProfesional || '';
        document.getElementById('gradoEstudio').value = teacher.gradoEstudio || '';
        document.getElementById('edad').value = teacher.edad || '';
        document.getElementById('genero').value = teacher.genero || '';
        
        new bootstrap.Modal(document.getElementById('teacherModal')).show();
    }
    
    static saveTeacher() {
        const formData = {
            nombres: document.getElementById('nombres').value,
            apellidos: document.getElementById('apellidos').value,
            correoInstitucional: document.getElementById('correoInstitucional').value,
            correoPersonal: document.getElementById('correoPersonal').value,
            celular: document.getElementById('celular').value,
            cedulaProfesional: document.getElementById('cedulaProfesional').value,
            gradoEstudio: document.getElementById('gradoEstudio').value,
            edad: parseInt(document.getElementById('edad').value) || null,
            genero: document.getElementById('genero').value,
            activo: true
        };
        
        if (this.currentEditId) {
            formData.id = this.currentEditId;
        }
        
        Database.save('teachers', formData);
        
        // Close modal and refresh list
        bootstrap.Modal.getInstance(document.getElementById('teacherModal')).hide();
        this.loadTeachers();
        
        // Show success message
        this.showAlert('Docente guardado exitosamente', 'success');
    }
    
    static viewTeacher(id) {
        const teacher = Database.getById('teachers', id);
        if (!teacher) return;
        
        let details = `
            <div class="row">
                <div class="col-md-6">
                    <strong>Nombre completo:</strong><br>
                    ${teacher.nombres} ${teacher.apellidos}
                </div>
                <div class="col-md-6">
                    <strong>Email institucional:</strong><br>
                    ${teacher.correoInstitucional}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <strong>Grado de estudio:</strong><br>
                    ${teacher.gradoEstudio || 'No especificado'}
                </div>
                <div class="col-md-6">
                    <strong>Cédula profesional:</strong><br>
                    ${teacher.cedulaProfesional || 'No especificada'}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <strong>Edad:</strong><br>
                    ${teacher.edad || 'No especificada'} años
                </div>
                <div class="col-md-6">
                    <strong>Género:</strong><br>
                    ${teacher.genero || 'No especificado'}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <strong>Estado:</strong><br>
                    <span class="badge ${teacher.activo ? 'bg-success' : 'bg-danger'}">
                        ${teacher.activo ? 'Activo' : 'Inactivo'}
                    </span>
                </div>
                <div class="col-md-6">
                    <strong>Celular:</strong><br>
                    ${teacher.celular || 'No especificado'}
                </div>
            </div>
        `;
        
        if (teacher.correoPersonal) {
            details += `
                <hr>
                <div class="row">
                    <div class="col-md-12">
                        <strong>Email personal:</strong><br>
                        ${teacher.correoPersonal}
                    </div>
                </div>
            `;
        }
        
        this.showModal('Detalles del Docente', details);
    }
    
    static deleteTeacher(id) {
        const teacher = Database.getById('teachers', id);
        if (!teacher) return;
        
        if (confirm(`¿Está seguro de eliminar al docente ${teacher.nombres} ${teacher.apellidos}?`)) {
            Database.delete('teachers', id);
            this.loadTeachers();
            this.showAlert('Docente eliminado exitosamente', 'success');
        }
    }
    
    static showAlert(message, type) {
        // Create and show a temporary alert
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
        alertDiv.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        
        const container = document.querySelector('.container');
        container.insertBefore(alertDiv, container.firstChild);
        
        // Auto remove after 3 seconds
        setTimeout(() => {
            if (alertDiv.parentNode) {
                alertDiv.remove();
            }
        }, 3000);
    }
    
    static showModal(title, content) {
        // Create a temporary modal for showing information
        const modalHtml = `
            <div class="modal fade" id="tempModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">${title}</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            ${content}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        // Remove previous temp modal if exists
        const existingModal = document.getElementById('tempModal');
        if (existingModal) {
            existingModal.remove();
        }
        
        // Add new modal
        document.body.insertAdjacentHTML('beforeend', modalHtml);
        const modal = new bootstrap.Modal(document.getElementById('tempModal'));
        modal.show();
        
        // Remove modal when hidden
        document.getElementById('tempModal').addEventListener('hidden.bs.modal', function() {
            this.remove();
        });
    }
}
