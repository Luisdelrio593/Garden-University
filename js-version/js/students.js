// Gestión de estudiantes
function getStudentsContent() {
    return `
        <div class="row">
            <div class="col-12">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="text-success fw-bold">
                        <i class="fas fa-users me-2"></i>
                        Gestión de Estudiantes
                    </h2>
                    <button class="btn btn-success" onclick="showAddStudentModal()">
                        <i class="fas fa-plus me-2"></i>Nuevo Estudiante
                    </button>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="table-success">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre Completo</th>
                                        <th>Email</th>
                                        <th>Programa</th>
                                        <th>Semestre</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody id="studentsTableBody">
                                    <!-- Los datos se cargarán dinámicamente -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal para agregar/editar estudiante -->
        <div class="modal fade" id="studentModal" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="studentModalTitle">
                            <i class="fas fa-user-plus me-2"></i>Nuevo Estudiante
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form id="studentForm">
                            <input type="hidden" id="studentId">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="studentNombre" class="form-label">Nombre</label>
                                    <input type="text" class="form-control" id="studentNombre" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="studentApellido" class="form-label">Apellido</label>
                                    <input type="text" class="form-control" id="studentApellido" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="studentEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="studentEmail" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="studentTelefono" class="form-label">Teléfono</label>
                                    <input type="tel" class="form-control" id="studentTelefono" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="studentFechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                                    <input type="date" class="form-control" id="studentFechaNacimiento" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="studentPrograma" class="form-label">Programa</label>
                                    <select class="form-select" id="studentPrograma" required>
                                        <option value="">Seleccione un programa</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="studentSemestre" class="form-label">Semestre</label>
                                    <input type="number" class="form-control" id="studentSemestre" min="1" max="12" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="studentFechaIngreso" class="form-label">Fecha de Ingreso</label>
                                    <input type="date" class="form-control" id="studentFechaIngreso" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="studentDireccion" class="form-label">Dirección</label>
                                <textarea class="form-control" id="studentDireccion" rows="2" required></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-success" onclick="saveStudent()">
                            <i class="fas fa-save me-2"></i>Guardar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `;
}

function loadStudentsData() {
    const students = db.getStudents();
    const tbody = document.getElementById('studentsTableBody');
    
    tbody.innerHTML = students.map(student => `
        <tr>
            <td>${student.id}</td>
            <td>${student.nombre} ${student.apellido}</td>
            <td>${student.email}</td>
            <td>${student.programa}</td>
            <td>${student.semestre}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary me-1" onclick="editStudent(${student.id})">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger" onclick="deleteStudent(${student.id})">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
    
    // Cargar programas en el select
    loadProgramsInSelect();
}

function loadProgramsInSelect() {
    const programs = db.getPrograms();
    const select = document.getElementById('studentPrograma');
    
    if (select) {
        select.innerHTML = '<option value="">Seleccione un programa</option>' +
            programs.map(program => `<option value="${program.nombre}">${program.nombre}</option>`).join('');
    }
}

function showAddStudentModal() {
    document.getElementById('studentModalTitle').innerHTML = '<i class="fas fa-user-plus me-2"></i>Nuevo Estudiante';
    document.getElementById('studentForm').reset();
    document.getElementById('studentId').value = '';
    
    const modal = new bootstrap.Modal(document.getElementById('studentModal'));
    modal.show();
}

function editStudent(id) {
    const students = db.getStudents();
    const student = students.find(s => s.id == id);
    
    if (student) {
        document.getElementById('studentModalTitle').innerHTML = '<i class="fas fa-user-edit me-2"></i>Editar Estudiante';
        document.getElementById('studentId').value = student.id;
        document.getElementById('studentNombre').value = student.nombre;
        document.getElementById('studentApellido').value = student.apellido;
        document.getElementById('studentEmail').value = student.email;
        document.getElementById('studentTelefono').value = student.telefono;
        document.getElementById('studentFechaNacimiento').value = student.fechaNacimiento;
        document.getElementById('studentPrograma').value = student.programa;
        document.getElementById('studentSemestre').value = student.semestre;
        document.getElementById('studentFechaIngreso').value = student.fechaIngreso;
        document.getElementById('studentDireccion').value = student.direccion;
        
        const modal = new bootstrap.Modal(document.getElementById('studentModal'));
        modal.show();
    }
}

function saveStudent() {
    const form = document.getElementById('studentForm');
    
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const studentData = {
        nombre: document.getElementById('studentNombre').value,
        apellido: document.getElementById('studentApellido').value,
        email: document.getElementById('studentEmail').value,
        telefono: document.getElementById('studentTelefono').value,
        fechaNacimiento: document.getElementById('studentFechaNacimiento').value,
        programa: document.getElementById('studentPrograma').value,
        semestre: parseInt(document.getElementById('studentSemestre').value),
        fechaIngreso: document.getElementById('studentFechaIngreso').value,
        direccion: document.getElementById('studentDireccion').value
    };
    
    const studentId = document.getElementById('studentId').value;
    
    try {
        if (studentId) {
            // Actualizar estudiante existente
            db.updateStudent(studentId, studentData);
            showAlert('Estudiante actualizado exitosamente', 'success');
        } else {
            // Crear nuevo estudiante
            db.addStudent(studentData);
            showAlert('Estudiante creado exitosamente', 'success');
        }
        
        // Cerrar modal y recargar datos
        const modal = bootstrap.Modal.getInstance(document.getElementById('studentModal'));
        modal.hide();
        
        loadStudentsData();
    } catch (error) {
        showAlert('Error al guardar el estudiante: ' + error.message, 'danger');
    }
}

function deleteStudent(id) {
    if (confirm('¿Está seguro de que desea eliminar este estudiante?')) {
        try {
            db.deleteStudent(id);
            showAlert('Estudiante eliminado exitosamente', 'success');
            loadStudentsData();
        } catch (error) {
            showAlert('Error al eliminar el estudiante: ' + error.message, 'danger');
        }
    }
}
