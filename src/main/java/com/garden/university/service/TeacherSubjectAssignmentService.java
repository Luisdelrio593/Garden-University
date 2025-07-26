package com.garden.university.service;

import com.garden.university.model.TeacherSubjectAssignment;
import com.garden.university.model.Teacher;
import com.garden.university.model.Subject;
import com.garden.university.repository.TeacherSubjectAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherSubjectAssignmentService {
    
    @Autowired
    private TeacherSubjectAssignmentRepository assignmentRepository;
    
    // Guardar asignación
    public TeacherSubjectAssignment save(TeacherSubjectAssignment assignment) {
        // Validar que no exista la misma asignación
        TeacherSubjectAssignment existing = assignmentRepository
            .findByTeacherAndSubjectAndGrupo(assignment.getTeacher(), assignment.getSubject(), assignment.getGrupo());
        
        if (existing != null && !existing.getId().equals(assignment.getId())) {
            throw new RuntimeException("Ya existe esta asignación de materia para el docente y grupo especificado");
        }
        
        return assignmentRepository.save(assignment);
    }
    
    // Obtener todas las asignaciones
    public List<TeacherSubjectAssignment> findAll() {
        return assignmentRepository.findAll();
    }
    
    // Obtener asignaciones activas
    public List<TeacherSubjectAssignment> findActive() {
        return assignmentRepository.findByActivaTrue();
    }
    
    // Buscar por ID
    public Optional<TeacherSubjectAssignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }
    
    // Buscar asignaciones por docente
    public List<TeacherSubjectAssignment> findByTeacher(Teacher teacher) {
        return assignmentRepository.findByTeacherAndActivaTrue(teacher);
    }
    
    // Buscar asignaciones por materia
    public List<TeacherSubjectAssignment> findBySubject(Subject subject) {
        return assignmentRepository.findBySubjectAndActivaTrue(subject);
    }
    
    // Buscar por grupo
    public List<TeacherSubjectAssignment> findByGroup(String grupo) {
        return assignmentRepository.findByGrupoAndActivaTrue(grupo);
    }
    
    // Eliminar asignación (soft delete)
    public void delete(Long id) {
        Optional<TeacherSubjectAssignment> assignment = findById(id);
        if (assignment.isPresent()) {
            TeacherSubjectAssignment a = assignment.get();
            a.setActiva(false);
            assignmentRepository.save(a);
        }
    }
    
    // Reactivar asignación
    public void activate(Long id) {
        Optional<TeacherSubjectAssignment> assignment = findById(id);
        if (assignment.isPresent()) {
            TeacherSubjectAssignment a = assignment.get();
            a.setActiva(true);
            assignmentRepository.save(a);
        }
    }
    
    // Obtener grupos disponibles
    public List<String> getAvailableGroups() {
        return List.of("A", "B", "C", "D", "E", "F");
    }
}
