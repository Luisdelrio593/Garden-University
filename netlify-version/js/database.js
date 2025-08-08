// Base de datos simulada para Garden University
class Database {
    static initializeData() {
        // Inicializar datos si no existen
        if (!localStorage.getItem('students')) {
            localStorage.setItem('students', JSON.stringify([
                {
                    id: 1,
                    nombres: 'Juan Carlos',
                    apellidos: 'García López',
                    correoInstitucional: 'juan.garcia@garden.uni.edu',
                    correoAlumno: 'juan.garcia@gmail.com',
                    celular: '5551234567',
                    programaEstudios: 'Ingeniería en Sistemas',
                    cuatrimestre: '6to',
                    edad: 22,
                    genero: 'Masculino',
                    fechaNacimiento: '2002-03-15',
                    curp: 'GAGL020315HDFRPN01',
                    rfc: 'GAGL020315A12',
                    estadoCivil: 'Soltero',
                    domicilio: 'Calle Principal 123, Col. Centro',
                    celularEmergencia: '5559876543',
                    nombrePadreTutor: 'Carlos García',
                    modalidadEstudios: 'Presencial',
                    conceptoPago: 'Colegiatura',
                    montoPago: 3500.00,
                    metodoPago: 'Transferencia',
                    activo: true
                },
                {
                    id: 2,
                    nombres: 'María Elena',
                    apellidos: 'Rodríguez Silva',
                    correoInstitucional: 'maria.rodriguez@garden.uni.edu',
                    correoAlumno: 'maria.rodriguez@gmail.com',
                    celular: '5552345678',
                    programaEstudios: 'Administración de Empresas',
                    cuatrimestre: '4to',
                    edad: 20,
                    genero: 'Femenino',
                    fechaNacimiento: '2004-07-22',
                    curp: 'ROSM040722MDFRLR03',
                    rfc: 'ROSM040722B34',
                    estadoCivil: 'Soltera',
                    domicilio: 'Av. Universidad 456, Col. Educación',
                    celularEmergencia: '5558765432',
                    nombrePadreTutor: 'Elena Silva',
                    modalidadEstudios: 'Presencial',
                    conceptoPago: 'Colegiatura',
                    montoPago: 3200.00,
                    metodoPago: 'Efectivo',
                    activo: true
                }
            ]));
        }
        
        if (!localStorage.getItem('teachers')) {
            localStorage.setItem('teachers', JSON.stringify([
                {
                    id: 1,
                    nombres: 'Dr. Roberto',
                    apellidos: 'Fernández Martínez',
                    correoInstitucional: 'roberto.fernandez@garden.uni.edu',
                    correoPersonal: 'roberto.fernandez@gmail.com',
                    celular: '5553456789',
                    cedulaProfesional: 'CED123456789',
                    gradoEstudio: 'Doctorado en Ciencias de la Computación',
                    edad: 45,
                    genero: 'Masculino',
                    fechaNacimiento: '1979-11-10',
                    curp: 'FERM791110HDFRRT04',
                    rfc: 'FERM791110C56',
                    domicilio: 'Calle Académica 789, Col. Profesores',
                    celularEmergencia: '5554567890',
                    activo: true
                },
                {
                    id: 2,
                    nombres: 'Dra. Ana Patricia',
                    apellidos: 'López González',
                    correoInstitucional: 'ana.lopez@garden.uni.edu',
                    correoPersonal: 'ana.lopez@gmail.com',
                    celular: '5554567890',
                    cedulaProfesional: 'CED987654321',
                    gradoEstudio: 'Maestría en Administración',
                    edad: 38,
                    genero: 'Femenino',
                    fechaNacimiento: '1986-05-18',
                    curp: 'LOGA860518MDFPNR07',
                    rfc: 'LOGA860518D78',
                    domicilio: 'Av. Conocimiento 321, Col. Sabiduría',
                    celularEmergencia: '5556789012',
                    activo: true
                }
            ]));
        }
        
        if (!localStorage.getItem('administratives')) {
            localStorage.setItem('administratives', JSON.stringify([
                {
                    id: 1,
                    nombres: 'Carmen',
                    apellidos: 'Sánchez Torres',
                    correoInstitucional: 'carmen.sanchez@garden.uni.edu',
                    correoPersonal: 'carmen.sanchez@gmail.com',
                    celular: '5555678901',
                    area: 'Recursos Humanos',
                    extension: '101',
                    fechaIngreso: '2020-01-15',
                    curp: 'SATC850312MDFNRR09',
                    rfc: 'SATC850312E90',
                    direccion: 'Calle Administrativa 654, Col. Gestión',
                    passwordAcceso: 'admin123',
                    activo: true
                },
                {
                    id: 2,
                    nombres: 'Luis Fernando',
                    apellidos: 'Morales Jiménez',
                    correoInstitucional: 'luis.morales@garden.uni.edu',
                    correoPersonal: 'luis.morales@gmail.com',
                    celular: '5556789012',
                    area: 'Finanzas',
                    extension: '102',
                    fechaIngreso: '2019-08-20',
                    curp: 'MOJL820715HDFRMR02',
                    rfc: 'MOJL820715F12',
                    direccion: 'Av. Finanzas 987, Col. Economía',
                    passwordAcceso: 'admin123',
                    activo: true
                }
            ]));
        }
        
        if (!localStorage.getItem('programs')) {
            localStorage.setItem('programs', JSON.stringify([
                {
                    id: 1,
                    nombre: 'Ingeniería en Sistemas Computacionales',
                    tipo: 'Licenciatura',
                    modalidad: 'Presencial',
                    numeroCuatrimestres: 12,
                    creditosTotales: 240,
                    descripcion: 'Forma profesionales capaces de diseñar, desarrollar e implementar sistemas computacionales.',
                    perfilEgreso: 'Profesionales con conocimientos en programación, bases de datos, redes y desarrollo de software.',
                    campoLaboral: 'Empresas de tecnología, consultoría en sistemas, desarrollo de software.',
                    activo: true
                },
                {
                    id: 2,
                    nombre: 'Administración de Empresas',
                    tipo: 'Licenciatura',
                    modalidad: 'Presencial',
                    numeroCuatrimestres: 10,
                    creditosTotales: 200,
                    descripcion: 'Forma líderes empresariales con visión estratégica y habilidades gerenciales.',
                    perfilEgreso: 'Administradores con capacidad de gestión, liderazgo y toma de decisiones.',
                    campoLaboral: 'Empresas privadas, sector público, consultoría empresarial.',
                    activo: true
                }
            ]));
        }
        
        if (!localStorage.getItem('subjects')) {
            localStorage.setItem('subjects', JSON.stringify([
                {
                    id: 1,
                    nombre: 'Programación Orientada a Objetos',
                    claveAsignatura: 'POO001',
                    cuatrimestre: '3ro',
                    creditos: 8,
                    areaCurricular: 'Desarrollo de Software',
                    descripcion: 'Fundamentos de la programación orientada a objetos usando Java.',
                    academicProgramId: 1,
                    teacherId: 1,
                    servicioSocial: false,
                    practicasProfesionales: false,
                    activa: true
                },
                {
                    id: 2,
                    nombre: 'Base de Datos',
                    claveAsignatura: 'BD001',
                    cuatrimestre: '4to',
                    creditos: 7,
                    areaCurricular: 'Gestión de Datos',
                    descripcion: 'Diseño e implementación de bases de datos relacionales.',
                    academicProgramId: 1,
                    teacherId: 1,
                    servicioSocial: false,
                    practicasProfesionales: false,
                    activa: true
                },
                {
                    id: 3,
                    nombre: 'Administración Estratégica',
                    claveAsignatura: 'AE001',
                    cuatrimestre: '2do',
                    creditos: 6,
                    areaCurricular: 'Gestión Empresarial',
                    descripcion: 'Planeación estratégica y dirección empresarial.',
                    academicProgramId: 2,
                    teacherId: 2,
                    servicioSocial: false,
                    practicasProfesionales: false,
                    activa: true
                }
            ]));
        }
        
        if (!localStorage.getItem('assignments')) {
            localStorage.setItem('assignments', JSON.stringify([
                {
                    id: 1,
                    teacherId: 1,
                    subjectId: 1,
                    grupo: 'A',
                    activa: true
                },
                {
                    id: 2,
                    teacherId: 1,
                    subjectId: 2,
                    grupo: 'A',
                    activa: true
                },
                {
                    id: 3,
                    teacherId: 2,
                    subjectId: 3,
                    grupo: 'B',
                    activa: true
                }
            ]));
        }
    }
    
    // Métodos CRUD genéricos
    static getAll(entity) {
        const data = localStorage.getItem(entity);
        return data ? JSON.parse(data) : [];
    }
    
    static getById(entity, id) {
        const items = this.getAll(entity);
        return items.find(item => item.id === parseInt(id));
    }
    
    static save(entity, item) {
        const items = this.getAll(entity);
        if (item.id) {
            // Actualizar
            const index = items.findIndex(i => i.id === item.id);
            if (index !== -1) {
                items[index] = item;
            }
        } else {
            // Nuevo
            item.id = Math.max(...items.map(i => i.id || 0), 0) + 1;
            items.push(item);
        }
        localStorage.setItem(entity, JSON.stringify(items));
        return item;
    }
    
    static delete(entity, id) {
        const items = this.getAll(entity);
        const filteredItems = items.filter(item => item.id !== parseInt(id));
        localStorage.setItem(entity, JSON.stringify(filteredItems));
        return true;
    }
    
    static getStats() {
        return {
            students: this.getAll('students').filter(s => s.activo).length,
            teachers: this.getAll('teachers').filter(t => t.activo).length,
            administratives: this.getAll('administratives').filter(a => a.activo).length,
            programs: this.getAll('programs').filter(p => p.activo).length,
            subjects: this.getAll('subjects').filter(s => s.activa).length,
            assignments: this.getAll('assignments').filter(a => a.activa).length
        };
    }
}

// Inicializar datos al cargar
Database.initializeData();
