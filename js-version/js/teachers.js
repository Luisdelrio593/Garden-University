// Otros módulos simplificados
function getTeachersContent() {
    return `<div class="row"><div class="col-12"><h2 class="text-success fw-bold"><i class="fas fa-chalkboard-teacher me-2"></i>Gestión de Docentes</h2><div class="card"><div class="card-body"><div id="teachersTable"></div></div></div></div></div>`;
}

function getAdministrativesContent() {
    return `<div class="row"><div class="col-12"><h2 class="text-success fw-bold"><i class="fas fa-user-tie me-2"></i>Personal Administrativo</h2><div class="card"><div class="card-body"><div id="administrativesTable"></div></div></div></div></div>`;
}

function getProgramsContent() {
    return `<div class="row"><div class="col-12"><h2 class="text-success fw-bold"><i class="fas fa-graduation-cap me-2"></i>Programas Académicos</h2><div class="card"><div class="card-body"><div id="programsTable"></div></div></div></div></div>`;
}

function getSubjectsContent() {
    return `<div class="row"><div class="col-12"><h2 class="text-success fw-bold"><i class="fas fa-book me-2"></i>Gestión de Materias</h2><div class="card"><div class="card-body"><div id="subjectsTable"></div></div></div></div></div>`;
}

function loadTeachersData() {
    const teachers = db.getTeachers();
    document.getElementById('teachersTable').innerHTML = `
        <table class="table table-hover">
            <thead class="table-success">
                <tr><th>ID</th><th>Nombre</th><th>Especialidad</th><th>Email</th><th>Departamento</th></tr>
            </thead>
            <tbody>
                ${teachers.map(t => `<tr><td>${t.id}</td><td>${t.nombre} ${t.apellido}</td><td>${t.especialidad}</td><td>${t.email}</td><td>${t.departamento}</td></tr>`).join('')}
            </tbody>
        </table>
    `;
}

function loadAdministrativesData() {
    const administratives = db.getAdministratives();
    document.getElementById('administrativesTable').innerHTML = `
        <table class="table table-hover">
            <thead class="table-success">
                <tr><th>ID</th><th>Nombre</th><th>Cargo</th><th>Email</th><th>Departamento</th></tr>
            </thead>
            <tbody>
                ${administratives.map(a => `<tr><td>${a.id}</td><td>${a.nombre} ${a.apellido}</td><td>${a.cargo}</td><td>${a.email}</td><td>${a.departamento}</td></tr>`).join('')}
            </tbody>
        </table>
    `;
}

function loadProgramsData() {
    const programs = db.getPrograms();
    document.getElementById('programsTable').innerHTML = `
        <table class="table table-hover">
            <thead class="table-success">
                <tr><th>ID</th><th>Nombre</th><th>Código</th><th>Duración</th><th>Créditos</th><th>Modalidad</th></tr>
            </thead>
            <tbody>
                ${programs.map(p => `<tr><td>${p.id}</td><td>${p.nombre}</td><td>${p.codigo}</td><td>${p.duracion} semestres</td><td>${p.creditos}</td><td>${p.modalidad}</td></tr>`).join('')}
            </tbody>
        </table>
    `;
}

function loadSubjectsData() {
    const subjects = db.getSubjects();
    document.getElementById('subjectsTable').innerHTML = `
        <table class="table table-hover">
            <thead class="table-success">
                <tr><th>ID</th><th>Nombre</th><th>Código</th><th>Créditos</th><th>Semestre</th><th>Programa</th></tr>
            </thead>
            <tbody>
                ${subjects.map(s => `<tr><td>${s.id}</td><td>${s.nombre}</td><td>${s.codigo}</td><td>${s.creditos}</td><td>${s.semestre}</td><td>${s.programa}</td></tr>`).join('')}
            </tbody>
        </table>
    `;
}
