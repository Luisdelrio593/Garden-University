package com.garden.university.repository;

import com.garden.university.model.StudentSubjectAssignment;
import com.garden.university.model.Student;
import com.garden.university.model.Subject;
import com.garden.university.model.TeacherSubjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectAssignmentRepository extends JpaRepository<StudentSubjectAssignment, Long> {
    
    List<StudentSubjectAssignment> findByActivaTrue();
    
    List<StudentSubjectAssignment> findByStudentAndActivaTrue(Student student);
    
    List<StudentSubjectAssignment> findBySubjectAndActivaTrue(Subject subject);
    
    List<StudentSubjectAssignment> findByTeacherAssignmentAndActivaTrue(TeacherSubjectAssignment teacherAssignment);
    
    List<StudentSubjectAssignment> findByGrupoAndActivaTrue(String grupo);
    
    List<StudentSubjectAssignment> findByCuatrimestreAndActivaTrue(String cuatrimestre);
    
    List<StudentSubjectAssignment> findByProgramaEstudiosAndActivaTrue(String programaEstudios);
    
    List<StudentSubjectAssignment> findByModalidadAndActivaTrue(String modalidad);
    
    @Query("SELECT ssa FROM StudentSubjectAssignment ssa WHERE ssa.programaEstudios = :programa AND ssa.cuatrimestre = :cuatrimestre AND ssa.modalidad = :modalidad AND ssa.activa = true")
    List<StudentSubjectAssignment> findByProgramaCuatrimestreModalidad(@Param("programa") String programa, 
                                                                      @Param("cuatrimestre") String cuatrimestre,
                                                                      @Param("modalidad") String modalidad);
    
    @Query("SELECT ssa FROM StudentSubjectAssignment ssa WHERE ssa.teacherAssignment.teacher.id = :teacherId AND ssa.grupo = :grupo AND ssa.activa = true")
    List<StudentSubjectAssignment> findByTeacherIdAndGrupo(@Param("teacherId") Long teacherId, @Param("grupo") String grupo);
    
    boolean existsByStudentAndSubjectAndGrupoAndActivaTrue(Student student, Subject subject, String grupo);
}
