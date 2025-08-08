// Módulo para gestión de estudiantes
class Students {
    static currentEditId = null;
    
    static init() {
        this.loadStudents();
        this.setupEventListeners();
        this.loadProgramFilters();
    }
    
    static setupEventListeners() {
        // Search functionality
        document.getElementById('searchInput').addEventListener('input', () => {
            this.filterStudents();
        });
        
        // Program filter
        document.getElementById('programFilter').addEventListener('change', () => {
            this.filterStudents();
        });
        
        // Form submission
        document.getElementById('studentForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.saveStudent();
        });
    }
    
    static loadProgramFilters() {
        const programs = [...new Set(Database.getAll('students').map(s => s.programaEstudios))];
        const filter = document.getElementById('programFilter');
        
        programs.forEach(program => {
            if (program) {
                const option = document.createElement('option');
                option.value = program;
                option.textContent = program;
                filter.appendChild(option);
            }
        });
    }
    
    static loadStudents() {
        const students = Database.getAll('students');
        this.renderStudents(students);
    }
    
    static renderStudents(students) {
        const tbody = document.getElementById('studentsTable');
        
        if (students.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="7" class="text-center py-4">
                        <i class="fas fa-user-slash fa-3x text-muted mb-3"></i>
                        <p class="text-muted">No hay estudiantes registrados</p>
                    </td>
                </tr>
            `;
            return;
        }
        
        tbody.innerHTML = students.map(student => `
            <tr>
                <td>${student.id}</td>
                <td>${student.nombres} ${student.apellidos}</td>
                <td>
                    <small class="text-muted">${student.correoInstitucional}</small>
                </td>
                <td>
                    <small>${student.programaEstudios || 'No especificado'}</small>
                </td>
                <td>
                    <span class="badge bg-info">${student.cuatrimestre || 'N/A'}</span>
                </td>
                <td>
                    <span class="badge ${student.activo ? 'bg-success' : 'bg-danger'}">
                        ${student.activo ? 'Activo' : 'Inactivo'}
                    </span>
                </td>
                <td class="action-buttons">
                    <button class="btn btn-outline-primary btn-sm" onclick="Students.viewStudent(${student.id})" title="Ver detalles">
                        <i class="fas fa-eye"></i>
                    </button>
                    <button class="btn btn-outline-warning btn-sm" onclick="Students.editStudent(${student.id})" title="Editar">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-outline-danger btn-sm" onclick="Students.deleteStudent(${student.id})" title="Eliminar">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        `).join('');
    }
    
    static filterStudents() {
        const searchTerm = document.getElementById('searchInput').value.toLowerCase();
        const programFilter = document.getElementById('programFilter').value;
        
        let students = Database.getAll('students');
        
        if (searchTerm) {
            students = students.filter(student => 
                student.nombres.toLowerCase().includes(searchTerm) ||
                student.apellidos.toLowerCase().includes(searchTerm) ||
                student.correoInstitucional.toLowerCase().includes(searchTerm) ||
                (student.programaEstudios && student.programaEstudios.toLowerCase().includes(searchTerm))
            );
        }
        
        if (programFilter) {
            students = students.filter(student => student.programaEstudios === programFilter);
        }
        
        this.renderStudents(students);
    }
    
    static clearFilters() {
        document.getElementById('searchInput').value = '';
        document.getElementById('programFilter').value = '';
        this.loadStudents();
    }
    
    static openAddModal() {
        this.currentEditId = null;
        document.getElementById('modalTitle').innerHTML = '<i class="fas fa-user-plus me-2"></i>Nuevo Estudiante';
        document.getElementById('studentForm').reset();
        new bootstrap.Modal(document.getElementById('studentModal')).show();
    }
    
    static editStudent(id) {
        const student = Database.getById('students', id);
        if (!student) return;
        
        this.currentEditId = id;
        document.getElementById('modalTitle').innerHTML = '<i class="fas fa-user-edit me-2"></i>Editar Estudiante';
        
        // Fill form with student data
        document.getElementById('nombres').value = student.nombres || '';
        document.getElementById('apellidos').value = student.apellidos || '';
        document.getElementById('correoInstitucional').value = student.correoInstitucional || '';
        document.getElementById('correoAlumno').value = student.correoAlumno || '';
        document.getElementById('celular').value = student.celular || '';
        document.getElementById('programaEstudios').value = student.programaEstudios || '';
        document.getElementById('cuatrimestre').value = student.cuatrimestre || '';
        document.getElementById('edad').value = student.edad || '';
        
        new bootstrap.Modal(document.getElementById('studentModal')).show();
    }
    
    static saveStudent() {
        const formData = {
            nombres: document.getElementById('nombres').value,
            apellidos: document.getElementById('apellidos').value,
            correoInstitucional: document.getElementById('correoInstitucional').value,
            correoAlumno: document.getElementById('correoAlumno').value,
            celular: document.getElementById('celular').value,
            programaEstudios: document.getElementById('programaEstudios').value,
            cuatrimestre: document.getElementById('cuatrimestre').value,
            edad: parseInt(document.getElementById('edad').value) || null,
            activo: true
        };
        
        if (this.currentEditId) {
            formData.id = this.currentEditId;
        }
        
        Database.save('students', formData);
        
        // Close modal and refresh list
        bootstrap.Modal.getInstance(document.getElementById('studentModal')).hide();
        this.loadStudents();
        this.loadProgramFilters();
        
        // Show success message
        this.showAlert('Estudiante guardado exitosamente', 'success');
    }
    
    static viewStudent(id) {
        const student = Database.getById('students', id);
        if (!student) return;
        
        let details = `
            <div class="row">
                <div class="col-md-6">
                    <strong>Nombre completo:</strong><br>
                    ${student.nombres} ${student.apellidos}
                </div>
                <div class="col-md-6">
                    <strong>Email institucional:</strong><br>
                    ${student.correoInstitucional}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <strong>Programa:</strong><br>
                    ${student.programaEstudios || 'No especificado'}
                </div>
                <div class="col-md-6">
                    <strong>Cuatrimestre:</strong><br>
                    ${student.cuatrimestre || 'No especificado'}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <strong>Edad:</strong><br>
                    ${student.edad || 'No especificada'} años
                </div>
                <div class="col-md-6">
                    <strong>Estado:</strong><br>
                    <span class="badge ${student.activo ? 'bg-success' : 'bg-danger'}">
                        ${student.activo ? 'Activo' : 'Inactivo'}
                    </span>
                </div>
            </div>
        `;
        
        if (student.celular) {
            details += `
                <hr>
                <div class="row">
                    <div class="col-md-6">
                        <strong>Celular:</strong><br>
                        ${student.celular}
                    </div>
                    <div class="col-md-6">
                        <strong>Email personal:</strong><br>
                        ${student.correoAlumno || 'No especificado'}
                    </div>
                </div>
            `;
        }
        
        this.showModal('Detalles del Estudiante', details);
    }
    
    static deleteStudent(id) {
        const student = Database.getById('students', id);
        if (!student) return;
        
        if (confirm(`¿Está seguro de eliminar al estudiante ${student.nombres} ${student.apellidos}?`)) {
            Database.delete('students', id);
            this.loadStudents();
            this.showAlert('Estudiante eliminado exitosamente', 'success');
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

// Global functions for onclick events
window.openAddModal = () => Students.openAddModal();
window.clearFilters = () => Students.clearFilters();
