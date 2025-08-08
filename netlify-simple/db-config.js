// 🗄️ Configuración de Base de Datos Neon para Garden University
// Este archivo maneja toda la conectividad con la base de datos PostgreSQL

class GardenDatabase {
    constructor() {
        // Configuración de la base de datos Neon
        this.config = {
            // URL de conexión a Neon (debes reemplazar con tu propia URL)
            connectionString: 'postgresql://garden_admin:password@ep-divine-cloud-12345.us-east-1.aws.neon.tech/garden_university?sslmode=require',
            
            // Para el demo, usaremos una API REST como alternativa
            apiUrl: 'https://api.jsonbin.io/v3/b/676c8f2ead19ca34f8c8a157',
            apiKey: '$2a$10$9vWnO0WgF.qFRFgFcHmNteF6vY2lF5sHlKcQHyuJmPgKB8PwqLqgW'
        };
        
        this.isConnected = false;
        this.students = [];
        this.lastSync = null;
    }

    // 🔌 Inicializar conexión a la base de datos
    async initialize() {
        try {
            console.log('🔌 Conectando a la base de datos...');
            
            // Intentar cargar datos existentes
            await this.loadStudents();
            
            this.isConnected = true;
            console.log('✅ Base de datos conectada exitosamente');
            return true;
        } catch (error) {
            console.error('❌ Error conectando a la base de datos:', error);
            this.isConnected = false;
            return false;
        }
    }

    // 📥 Cargar estudiantes desde la base de datos
    async loadStudents() {
        try {
            const response = await fetch(`${this.config.apiUrl}/latest`, {
                method: 'GET',
                headers: {
                    'X-Master-Key': this.config.apiKey,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const result = await response.json();
                const data = result.record || {};
                
                this.students = data.students || [];
                this.lastSync = new Date().toISOString();
                
                console.log(`📥 Cargados ${this.students.length} estudiantes desde la base de datos`);
                return this.students;
            } else {
                throw new Error('Error al cargar datos');
            }
        } catch (error) {
            console.warn('⚠️ No se pudieron cargar datos desde la nube, usando datos locales');
            // Fallback a localStorage
            const localData = localStorage.getItem('garden_students');
            this.students = localData ? JSON.parse(localData) : [];
            return this.students;
        }
    }

    // 💾 Guardar un estudiante en la base de datos
    async saveStudent(studentData) {
        try {
            // Generar ID único
            const studentId = this.generateId();
            
            // Agregar ID único y timestamp
            const student = {
                ...studentData,
                id: studentId,
                created_at: new Date().toISOString(),
                updated_at: new Date().toISOString()
            };

            // Verificar que no existe duplicado por CURP
            const existingStudent = this.students.find(s => s.curp === student.curp);
            if (existingStudent) {
                throw new Error('Ya existe un estudiante con esta CURP');
            }

            // Agregar a la lista local
            this.students.push(student);

            // Guardar en la base de datos
            const syncResult = await this.syncToDatabase();
            
            if (!syncResult) {
                // Si falla la sincronización, guardar localmente
                localStorage.setItem('garden_students', JSON.stringify(this.students));
            }

            console.log('💾 Estudiante guardado exitosamente:', student.nombres, student.apellidos);
            return student;
        } catch (error) {
            console.error('❌ Error guardando estudiante:', error);
            
            // Si el error es por duplicado, no guardarlo
            if (error.message.includes('CURP')) {
                throw error;
            }
            
            // Para otros errores, intentar guardar localmente
            localStorage.setItem('garden_students', JSON.stringify(this.students));
            throw error;
        }
    }

    // 🔄 Sincronizar con la base de datos
    async syncToDatabase() {
        try {
            const dataToSave = {
                students: this.students,
                metadata: {
                    total_students: this.students.length,
                    last_updated: new Date().toISOString(),
                    version: '2.0'
                }
            };

            const response = await fetch(this.config.apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'X-Master-Key': this.config.apiKey
                },
                body: JSON.stringify(dataToSave)
            });

            if (response.ok) {
                this.lastSync = new Date().toISOString();
                console.log('🔄 Datos sincronizados con la base de datos');
                
                // También guardar localmente para acceso offline
                localStorage.setItem('garden_students', JSON.stringify(this.students));
                localStorage.setItem('garden_last_sync', this.lastSync);
                
                return true;
            } else {
                throw new Error('Error en la sincronización');
            }
        } catch (error) {
            console.error('❌ Error sincronizando:', error);
            return false;
        }
    }

    // 🔍 Buscar estudiantes
    async searchStudents(criteria = {}) {
        const { programa, modalidad, convenio, searchText } = criteria;
        
        let filtered = [...this.students];

        if (programa && programa !== '') {
            filtered = filtered.filter(s => s.programa === programa);
        }

        if (modalidad && modalidad !== '') {
            filtered = filtered.filter(s => s.modalidad === modalidad);
        }

        if (convenio !== undefined) {
            filtered = filtered.filter(s => s.convenio === convenio);
        }

        if (searchText && searchText.trim() !== '') {
            const search = searchText.toLowerCase();
            filtered = filtered.filter(s => 
                (s.nombres?.toLowerCase().includes(search)) ||
                (s.apellidos?.toLowerCase().includes(search)) ||
                (s.curp?.toLowerCase().includes(search))
            );
        }

        return filtered;
    }

    // 📊 Obtener estadísticas
    getStatistics() {
        const stats = {
            total: this.students.length,
            porPrograma: {},
            porModalidad: {},
            conConvenio: this.students.filter(s => s.convenio).length,
            ultimaActualizacion: this.lastSync
        };

        // Contar por programa
        this.students.forEach(student => {
            const programa = student.programa || 'Sin especificar';
            stats.porPrograma[programa] = (stats.porPrograma[programa] || 0) + 1;
        });

        // Contar por modalidad
        this.students.forEach(student => {
            const modalidad = student.modalidad || 'Sin especificar';
            stats.porModalidad[modalidad] = (stats.porModalidad[modalidad] || 0) + 1;
        });

        return stats;
    }

    // 🆔 Generar ID único
    generateId() {
        // Generar ID numérico secuencial
        const maxId = this.students.length > 0 ? Math.max(...this.students.map(s => parseInt(s.id) || 0)) : 0;
        return maxId + 1;
    }

    // 🏥 Verificar estado de la conexión
    getConnectionStatus() {
        return {
            connected: this.isConnected,
            lastSync: this.lastSync,
            totalStudents: this.students.length,
            status: this.isConnected ? 'Conectado' : 'Desconectado'
        };
    }

    // 📤 Exportar datos
    exportData() {
        return {
            students: this.students,
            statistics: this.getStatistics(),
            exportDate: new Date().toISOString()
        };
    }
}

// 🌐 Instancia global de la base de datos
window.gardenDB = new GardenDatabase();
