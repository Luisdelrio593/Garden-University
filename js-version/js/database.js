// Base de datos simulada en localStorage
class GardenUniversityDB {
    constructor() {
        this.initializeData();
    }

    initializeData() {
        // Verificar si ya existen datos
        if (!localStorage.getItem('garden_users')) {
            // Usuarios por defecto
            const defaultUsers = [
                {
                    id: 1,
                    email: 'admin@garden.uni.edu',
                    password: 'admin123',
                    role: 'ADMIN',
                    name: 'Administrador Principal'
                },
                {
                    id: 2,
                    email: 'director@garden.uni.edu',
                    password: 'director123',
                    role: 'DIRECTOR',
                    name: 'Director Académico'
                }
            ];

            // Estudiantes por defecto
            const defaultStudents = [
                {
                    id: 1,
                    nombre: 'María González',
                    apellido: 'Rodríguez',
                    email: 'maria.gonzalez@garden.uni.edu',
                    telefono: '555-0101',
                    fechaNacimiento: '2000-05-15',
                    direccion: 'Calle Principal 123',
                    programa: 'Ingeniería de Sistemas',
                    semestre: 6,
                    fechaIngreso: '2022-01-15'
                },
                {
                    id: 2,
                    nombre: 'Carlos López',
                    apellido: 'Martínez',
                    email: 'carlos.lopez@garden.uni.edu',
                    telefono: '555-0102',
                    fechaNacimiento: '1999-08-22',
                    direccion: 'Avenida Central 456',
                    programa: 'Administración de Empresas',
                    semestre: 4,
                    fechaIngreso: '2023-01-15'
                },
                {
                    id: 3,
                    nombre: 'Ana Fernández',
                    apellido: 'Silva',
                    email: 'ana.fernandez@garden.uni.edu',
                    telefono: '555-0103',
                    fechaNacimiento: '2001-03-10',
                    direccion: 'Plaza Mayor 789',
                    programa: 'Psicología',
                    semestre: 2,
                    fechaIngreso: '2024-01-15'
                }
            ];

            // Docentes por defecto
            const defaultTeachers = [
                {
                    id: 1,
                    nombre: 'Dr. Roberto',
                    apellido: 'Jiménez',
                    email: 'roberto.jimenez@garden.uni.edu',
                    telefono: '555-0201',
                    especialidad: 'Ingeniería de Software',
                    titulo: 'Doctor en Ciencias de la Computación',
                    fechaContratacion: '2020-03-01',
                    departamento: 'Ingeniería'
                },
                {
                    id: 2,
                    nombre: 'Dra. Patricia',
                    apellido: 'Morales',
                    email: 'patricia.morales@garden.uni.edu',
                    telefono: '555-0202',
                    especialidad: 'Gestión Empresarial',
                    titulo: 'Doctora en Administración',
                    fechaContratacion: '2019-08-15',
                    departamento: 'Administración'
                }
            ];

            // Administrativos por defecto
            const defaultAdministratives = [
                {
                    id: 1,
                    nombre: 'Luis',
                    apellido: 'Ramírez',
                    email: 'luis.ramirez@garden.uni.edu',
                    telefono: '555-0301',
                    cargo: 'Secretario Académico',
                    departamento: 'Secretaría Académica',
                    fechaContratacion: '2021-06-01',
                    salario: 2500000
                }
            ];

            // Programas académicos por defecto
            const defaultPrograms = [
                {
                    id: 1,
                    nombre: 'Ingeniería de Sistemas',
                    codigo: 'IS001',
                    duracion: 10,
                    creditos: 160,
                    descripcion: 'Programa de ingeniería enfocado en desarrollo de software y sistemas',
                    modalidad: 'Presencial'
                },
                {
                    id: 2,
                    nombre: 'Administración de Empresas',
                    codigo: 'AE001',
                    duracion: 8,
                    creditos: 144,
                    descripcion: 'Programa de administración y gestión empresarial',
                    modalidad: 'Presencial'
                },
                {
                    id: 3,
                    nombre: 'Psicología',
                    codigo: 'PS001',
                    duracion: 10,
                    creditos: 170,
                    descripcion: 'Programa de psicología clínica y organizacional',
                    modalidad: 'Presencial'
                }
            ];

            // Materias por defecto
            const defaultSubjects = [
                {
                    id: 1,
                    nombre: 'Programación I',
                    codigo: 'PROG001',
                    creditos: 4,
                    semestre: 1,
                    programa: 'Ingeniería de Sistemas',
                    descripcion: 'Fundamentos de programación'
                },
                {
                    id: 2,
                    nombre: 'Matemáticas I',
                    codigo: 'MAT001',
                    creditos: 3,
                    semestre: 1,
                    programa: 'Ingeniería de Sistemas',
                    descripcion: 'Cálculo diferencial e integral'
                },
                {
                    id: 3,
                    nombre: 'Fundamentos de Administración',
                    codigo: 'ADM001',
                    creditos: 3,
                    semestre: 1,
                    programa: 'Administración de Empresas',
                    descripcion: 'Principios básicos de administración'
                }
            ];

            // Guardar datos iniciales
            localStorage.setItem('garden_users', JSON.stringify(defaultUsers));
            localStorage.setItem('garden_students', JSON.stringify(defaultStudents));
            localStorage.setItem('garden_teachers', JSON.stringify(defaultTeachers));
            localStorage.setItem('garden_administratives', JSON.stringify(defaultAdministratives));
            localStorage.setItem('garden_programs', JSON.stringify(defaultPrograms));
            localStorage.setItem('garden_subjects', JSON.stringify(defaultSubjects));
        }
    }

    // Métodos para usuarios
    authenticateUser(email, password) {
        const users = JSON.parse(localStorage.getItem('garden_users') || '[]');
        return users.find(user => user.email === email && user.password === password);
    }

    // Métodos para estudiantes
    getStudents() {
        return JSON.parse(localStorage.getItem('garden_students') || '[]');
    }

    addStudent(student) {
        const students = this.getStudents();
        student.id = Date.now();
        students.push(student);
        localStorage.setItem('garden_students', JSON.stringify(students));
        return student;
    }

    updateStudent(id, updatedStudent) {
        const students = this.getStudents();
        const index = students.findIndex(s => s.id == id);
        if (index !== -1) {
            students[index] = { ...students[index], ...updatedStudent };
            localStorage.setItem('garden_students', JSON.stringify(students));
            return students[index];
        }
        return null;
    }

    deleteStudent(id) {
        const students = this.getStudents();
        const filtered = students.filter(s => s.id != id);
        localStorage.setItem('garden_students', JSON.stringify(filtered));
        return true;
    }

    // Métodos para docentes
    getTeachers() {
        return JSON.parse(localStorage.getItem('garden_teachers') || '[]');
    }

    addTeacher(teacher) {
        const teachers = this.getTeachers();
        teacher.id = Date.now();
        teachers.push(teacher);
        localStorage.setItem('garden_teachers', JSON.stringify(teachers));
        return teacher;
    }

    updateTeacher(id, updatedTeacher) {
        const teachers = this.getTeachers();
        const index = teachers.findIndex(t => t.id == id);
        if (index !== -1) {
            teachers[index] = { ...teachers[index], ...updatedTeacher };
            localStorage.setItem('garden_teachers', JSON.stringify(teachers));
            return teachers[index];
        }
        return null;
    }

    deleteTeacher(id) {
        const teachers = this.getTeachers();
        const filtered = teachers.filter(t => t.id != id);
        localStorage.setItem('garden_teachers', JSON.stringify(filtered));
        return true;
    }

    // Métodos para administrativos
    getAdministratives() {
        return JSON.parse(localStorage.getItem('garden_administratives') || '[]');
    }

    addAdministrative(administrative) {
        const administratives = this.getAdministratives();
        administrative.id = Date.now();
        administratives.push(administrative);
        localStorage.setItem('garden_administratives', JSON.stringify(administratives));
        return administrative;
    }

    updateAdministrative(id, updatedAdministrative) {
        const administratives = this.getAdministratives();
        const index = administratives.findIndex(a => a.id == id);
        if (index !== -1) {
            administratives[index] = { ...administratives[index], ...updatedAdministrative };
            localStorage.setItem('garden_administratives', JSON.stringify(administratives));
            return administratives[index];
        }
        return null;
    }

    deleteAdministrative(id) {
        const administratives = this.getAdministratives();
        const filtered = administratives.filter(a => a.id != id);
        localStorage.setItem('garden_administratives', JSON.stringify(filtered));
        return true;
    }

    // Métodos para programas
    getPrograms() {
        return JSON.parse(localStorage.getItem('garden_programs') || '[]');
    }

    addProgram(program) {
        const programs = this.getPrograms();
        program.id = Date.now();
        programs.push(program);
        localStorage.setItem('garden_programs', JSON.stringify(programs));
        return program;
    }

    updateProgram(id, updatedProgram) {
        const programs = this.getPrograms();
        const index = programs.findIndex(p => p.id == id);
        if (index !== -1) {
            programs[index] = { ...programs[index], ...updatedProgram };
            localStorage.setItem('garden_programs', JSON.stringify(programs));
            return programs[index];
        }
        return null;
    }

    deleteProgram(id) {
        const programs = this.getPrograms();
        const filtered = programs.filter(p => p.id != id);
        localStorage.setItem('garden_programs', JSON.stringify(filtered));
        return true;
    }

    // Métodos para materias
    getSubjects() {
        return JSON.parse(localStorage.getItem('garden_subjects') || '[]');
    }

    addSubject(subject) {
        const subjects = this.getSubjects();
        subject.id = Date.now();
        subjects.push(subject);
        localStorage.setItem('garden_subjects', JSON.stringify(subjects));
        return subject;
    }

    updateSubject(id, updatedSubject) {
        const subjects = this.getSubjects();
        const index = subjects.findIndex(s => s.id == id);
        if (index !== -1) {
            subjects[index] = { ...subjects[index], ...updatedSubject };
            localStorage.setItem('garden_subjects', JSON.stringify(subjects));
            return subjects[index];
        }
        return null;
    }

    deleteSubject(id) {
        const subjects = this.getSubjects();
        const filtered = subjects.filter(s => s.id != id);
        localStorage.setItem('garden_subjects', JSON.stringify(filtered));
        return true;
    }
}

// Instancia global de la base de datos
const db = new GardenUniversityDB();
