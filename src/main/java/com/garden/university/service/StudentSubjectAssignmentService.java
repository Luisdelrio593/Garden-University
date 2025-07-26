package com.garden.university.service;

import com.garden.university.model.*;
import com.garden.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentSubjectAssignmentService {
    
    @Autowired
    private StudentSubjectAssignmentRepository studentSubjectAssignmentRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private TeacherSubjectAssignmentRepository teacherSubjectAssignmentRepository;
    
    public List<StudentSubjectAssignment> findAll() {
        return studentSubjectAssignmentRepository.findByActivaTrue();
    }
    
    public Optional<StudentSubjectAssignment> findById(Long id) {
        return studentSubjectAssignmentRepository.findById(id);
    }
    
    public StudentSubjectAssignment save(StudentSubjectAssignment assignment) {
        return studentSubjectAssignmentRepository.save(assignment);
    }
    
    public void deleteById(Long id) {
        Optional<StudentSubjectAssignment> assignment = studentSubjectAssignmentRepository.findById(id);
        if (assignment.isPresent()) {
            assignment.get().setActiva(false);
            studentSubjectAssignmentRepository.save(assignment.get());
        }
    }
    
    public List<StudentSubjectAssignment> findByStudent(Student student) {
        return studentSubjectAssignmentRepository.findByStudentAndActivaTrue(student);
    }
    
    public List<StudentSubjectAssignment> findByTeacherAndGrupo(Long teacherId, String grupo) {
        return studentSubjectAssignmentRepository.findByTeacherIdAndGrupo(teacherId, grupo);
    }
    
    public List<StudentSubjectAssignment> findByPrograma(String programa, String cuatrimestre, String modalidad) {
        return studentSubjectAssignmentRepository.findByProgramaCuatrimestreModalidad(programa, cuatrimestre, modalidad);
    }
    
    public boolean existsAssignment(Student student, Subject subject, String grupo) {
        return studentSubjectAssignmentRepository.existsByStudentAndSubjectAndGrupoAndActivaTrue(student, subject, grupo);
    }
    
    public StudentSubjectAssignment createAssignment(Long studentId, Long subjectId, Long teacherAssignmentId, 
                                                   String grupo, String cuatrimestre, String programaEstudios, String modalidad) {
        
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        Optional<TeacherSubjectAssignment> teacherAssignment = teacherSubjectAssignmentRepository.findById(teacherAssignmentId);
        
        if (student.isPresent() && subject.isPresent() && teacherAssignment.isPresent()) {
            // Verificar que no exista ya la asignación
            if (!existsAssignment(student.get(), subject.get(), grupo)) {
                StudentSubjectAssignment assignment = new StudentSubjectAssignment(
                    student.get(), 
                    subject.get(), 
                    teacherAssignment.get(), 
                    grupo, 
                    cuatrimestre, 
                    programaEstudios, 
                    modalidad
                );
                return save(assignment);
            }
        }
        return null;
    }
}
