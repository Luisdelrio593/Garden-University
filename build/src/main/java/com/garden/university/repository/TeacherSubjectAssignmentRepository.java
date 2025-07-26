package com.garden.university.repository;

import com.garden.university.model.TeacherSubjectAssignment;
import com.garden.university.model.Teacher;
import com.garden.university.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherSubjectAssignmentRepository extends JpaRepository<TeacherSubjectAssignment, Long> {
    
    // Buscar asignaciones por docente
    List<TeacherSubjectAssignment> findByTeacher(Teacher teacher);
    List<TeacherSubjectAssignment> findByTeacherAndActivaTrue(Teacher teacher);
    
    // Buscar asignaciones por materia
    List<TeacherSubjectAssignment> findBySubject(Subject subject);
    List<TeacherSubjectAssignment> findBySubjectAndActivaTrue(Subject subject);
    
    // Buscar por docente y materia específica
    TeacherSubjectAssignment findByTeacherAndSubjectAndGrupo(Teacher teacher, Subject subject, String grupo);
    
    // Buscar asignaciones activas
    List<TeacherSubjectAssignment> findByActivaTrue();
    
    // Buscar por grupo
    List<TeacherSubjectAssignment> findByGrupoAndActivaTrue(String grupo);
}
