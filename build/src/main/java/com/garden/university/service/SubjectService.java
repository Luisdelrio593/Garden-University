package com.garden.university.service;

import com.garden.university.model.Subject;
import com.garden.university.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    // Guardar materia
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }
    
    // Obtener todas las materias
    public List<Subject> findAll() {
        return subjectRepository.findAllByOrderByCuatrimestreAscNombreAsc();
    }
    
    // Obtener materias activas
    public List<Subject> findActive() {
        return subjectRepository.findByActivaTrue();
    }
    
    // Buscar por ID
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }
    
    // Buscar por cuatrimestre
    public List<Subject> findByCuatrimestre(String cuatrimestre) {
        return subjectRepository.findByCuatrimestre(cuatrimestre);
    }
    
    // Buscar por cuatrimestre (solo activas)
    public List<Subject> findActiveByCuatrimestre(String cuatrimestre) {
        return subjectRepository.findByCuatrimestreAndActivaTrue(cuatrimestre);
    }
    
    // Buscar por nombre
    public List<Subject> searchByName(String searchTerm) {
        return subjectRepository.findByNombreContainingIgnoreCase(searchTerm);
    }
    
    // Eliminar materia (soft delete)
    public void delete(Long id) {
        Optional<Subject> subject = findById(id);
        if (subject.isPresent()) {
            Subject s = subject.get();
            s.setActiva(false);
            subjectRepository.save(s);
        }
    }
    
    // Reactivar materia
    public void activate(Long id) {
        Optional<Subject> subject = findById(id);
        if (subject.isPresent()) {
            Subject s = subject.get();
            s.setActiva(true);
            subjectRepository.save(s);
        }
    }
    
    // Obtener lista de cuatrimestres disponibles
    public List<String> getAvailableCuatrimestres() {
        return List.of("1°", "2°", "3°", "4°", "5°", "6°", "7°", "8°", "9°");
    }
    
    // Método para obtener materias por programa de estudio
    public List<String> getMateriasByPrograma(String programa) {
        switch (programa) {
            case "Licenciatura en Enfermería":
                return List.of(
                    "Anatomía y Fisiología I",
                    "Fundamentos de Enfermería",
                    "Microbiología y Parasitología",
                    "Farmacología",
                    "Enfermería Médico-Quirúrgica",
                    "Enfermería Materno-Infantil",
                    "Enfermería en Salud Mental",
                    "Administración en Enfermería"
                );
            case "Licenciatura en Psicología":
                return List.of(
                    "Psicología General",
                    "Neuropsicología",
                    "Psicología del Desarrollo",
                    "Psicología Social",
                    "Psicología Clínica",
                    "Psicometría",
                    "Terapia Cognitivo-Conductual",
                    "Ética Profesional"
                );
            case "Licenciatura en Derecho":
                return List.of(
                    "Derecho Constitucional",
                    "Derecho Civil",
                    "Derecho Penal",
                    "Derecho Mercantil",
                    "Derecho Laboral",
                    "Derecho Internacional",
                    "Práctica Forense",
                    "Ética Jurídica"
                );
            case "Licenciatura en Gestión Empresarial":
                return List.of(
                    "Administración General",
                    "Contabilidad Financiera",
                    "Marketing",
                    "Recursos Humanos",
                    "Finanzas Corporativas",
                    "Gestión de Proyectos",
                    "Emprendimiento",
                    "Liderazgo Empresarial"
                );
            case "Ingeniería Mecatrónica":
                return List.of(
                    "Matemáticas Aplicadas",
                    "Física",
                    "Mecánica",
                    "Electrónica",
                    "Programación",
                    "Control Automático",
                    "Robótica",
                    "Sistemas Embebidos"
                );
            case "Ingeniería Industrial":
                return List.of(
                    "Investigación de Operaciones",
                    "Estadística",
                    "Ingeniería de Métodos",
                    "Control de Calidad",
                    "Logística",
                    "Seguridad Industrial",
                    "Ergonomía",
                    "Gestión de la Producción"
                );
            case "Maestría en Gestión y Administración Pública":
                return List.of(
                    "Políticas Públicas",
                    "Administración Pública",
                    "Derecho Administrativo",
                    "Gestión Financiera Pública",
                    "Transparencia y Rendición de Cuentas",
                    "Planeación Estratégica",
                    "Metodología de la Investigación",
                    "Seminario de Tesis"
                );
            default:
                return List.of();
        }
    }
}
