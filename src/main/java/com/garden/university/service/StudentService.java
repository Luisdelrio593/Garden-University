package com.garden.university.service;

import com.garden.university.model.Student;
import com.garden.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Student> findAllActiveStudents() {
        return studentRepository.findByActivoTrue();
    }
    
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
    
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student s = student.get();
            s.setActivo(false);
            studentRepository.save(s);
        }
    }
    
    public void activateStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student s = student.get();
            s.setActivo(true);
            studentRepository.save(s);
        }
    }
    
    public List<Student> searchStudents(String search) {
        if (search == null || search.trim().isEmpty()) {
            return findAllActiveStudents();
        }
        return studentRepository.searchActiveStudents(search.trim());
    }
    
    public List<Student> findByCuatrimestre(String cuatrimestre) {
        return studentRepository.findByCuatrimestre(cuatrimestre);
    }
    
    public boolean existsByCurp(String curp) {
        if (curp == null || curp.trim().isEmpty()) {
            return false;
        }
        return studentRepository.findByCurp(curp).isPresent();
    }
    
    public boolean existsByRfc(String rfc) {
        if (rfc == null || rfc.trim().isEmpty()) {
            return false;
        }
        return studentRepository.findByRfc(rfc).isPresent();
    }
    
    public boolean existsByCorreoAlumno(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return studentRepository.findByCorreoAlumno(correo).isPresent();
    }
    
    public boolean existsByCorreoInstitucional(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return studentRepository.findByCorreoInstitucional(correo).isPresent();
    }
    
    // Validaciones para edición (excluyendo el propio registro)
    public boolean existsByCurpExcludingId(String curp, Long excludeId) {
        if (curp == null || curp.trim().isEmpty()) {
            return false;
        }
        Optional<Student> student = studentRepository.findByCurp(curp);
        return student.isPresent() && !student.get().getId().equals(excludeId);
    }
    
    public boolean existsByRfcExcludingId(String rfc, Long excludeId) {
        if (rfc == null || rfc.trim().isEmpty()) {
            return false;
        }
        Optional<Student> student = studentRepository.findByRfc(rfc);
        return student.isPresent() && !student.get().getId().equals(excludeId);
    }
    
    public boolean existsByCorreoAlumnoExcludingId(String correo, Long excludeId) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        Optional<Student> student = studentRepository.findByCorreoAlumno(correo);
        return student.isPresent() && !student.get().getId().equals(excludeId);
    }
    
    public boolean existsByCorreoInstitucionalExcludingId(String correo, Long excludeId) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        Optional<Student> student = studentRepository.findByCorreoInstitucional(correo);
        return student.isPresent() && !student.get().getId().equals(excludeId);
    }
    
    public Long countActiveStudents() {
        return studentRepository.countActiveStudents();
    }
    
    public List<Object[]> getStudentStatsByCuatrimestre() {
        return studentRepository.countStudentsByCuatrimestre();
    }
    
    // Métodos helper para obtener opciones de modalidad y programa de estudios
    public List<String> getModalidadesEstudio() {
        return List.of("Escolarizada", "Semiescolarizada", "Abierta", "Por Examen CENEVAL");
    }
    
    public List<String> getProgramasEstudio() {
        return List.of(
            // Licenciaturas
            "Licenciatura en Enfermería",
            "Licenciatura en Psicología", 
            "Licenciatura en Derecho",
            "Licenciatura en Gestión Empresarial",
            // Ingenierías
            "Ingeniería Mecatrónica",
            "Ingeniería Industrial",
            // Maestría
            "Maestría en Gestión y Administración Pública"
        );
    }
}
