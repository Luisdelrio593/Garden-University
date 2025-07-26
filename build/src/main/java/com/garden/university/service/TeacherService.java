package com.garden.university.service;

import com.garden.university.model.Teacher;
import com.garden.university.model.User;
import com.garden.university.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private UserService userService;
    
    // Guardar docente
    public Teacher save(Teacher teacher) {
        // Validar datos únicos antes de guardar
        if (teacher.getId() == null) {
            if (existsByCurp(teacher.getCurp())) {
                throw new RuntimeException("Ya existe un docente con este CURP");
            }
            if (existsByRfc(teacher.getRfc())) {
                throw new RuntimeException("Ya existe un docente con este RFC");
            }
            if (existsByCorreoInstitucional(teacher.getCorreoInstitucional())) {
                throw new RuntimeException("Ya existe un docente con este correo institucional");
            }
            if (existsByCedulaProfesional(teacher.getCedulaProfesional())) {
                throw new RuntimeException("Ya existe un docente con esta cédula profesional");
            }
        }
        
        Teacher savedTeacher = teacherRepository.save(teacher);
        
        // Crear usuario automáticamente si es un docente nuevo y tiene password
        if (teacher.getId() == null && teacher.getPasswordAcceso() != null && !teacher.getPasswordAcceso().isEmpty()) {
            createUserForTeacher(savedTeacher);
        }
        
        return savedTeacher;
    }
    
    // Crear usuario para el docente
    private void createUserForTeacher(Teacher teacher) {
        try {
            User user = new User();
            user.setEmail(teacher.getCorreoInstitucional()); // Usar correo institucional como email
            user.setPassword(teacher.getPasswordAcceso());
            user.setFirstName(teacher.getNombres());
            user.setLastName(teacher.getApellidos());
            user.setRole("TEACHER");
            user.setEnabled(true);
            userService.createUser(user);
        } catch (Exception e) {
            System.err.println("Error creando usuario para docente: " + e.getMessage());
        }
    }
    
    // Obtener todos los docentes
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    
    // Obtener docentes activos
    public List<Teacher> findActive() {
        return teacherRepository.findByActivoTrue();
    }
    
    // Buscar por ID
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }
    
    // Buscar por CURP
    public Teacher findByCurp(String curp) {
        return teacherRepository.findByCurp(curp);
    }
    
    // Buscar por RFC
    public Teacher findByRfc(String rfc) {
        return teacherRepository.findByRfc(rfc);
    }
    
    // Buscar por correo institucional
    public Teacher findByCorreoInstitucional(String correo) {
        return teacherRepository.findByCorreoInstitucional(correo);
    }
    
    // Buscar por nombre
    public List<Teacher> searchByName(String searchTerm) {
        return teacherRepository.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(searchTerm, searchTerm);
    }
    
    // Validaciones de existencia
    public boolean existsByCurp(String curp) {
        return teacherRepository.findByCurp(curp) != null;
    }
    
    public boolean existsByRfc(String rfc) {
        return teacherRepository.findByRfc(rfc) != null;
    }
    
    public boolean existsByCorreoInstitucional(String correo) {
        return teacherRepository.findByCorreoInstitucional(correo) != null;
    }
    
    public boolean existsByCedulaProfesional(String cedula) {
        return teacherRepository.findByCedulaProfesional(cedula) != null;
    }
    
    // Validar formato CURP
    public boolean isValidCurp(String curp) {
        if (curp == null || curp.length() != 18) return false;
        return curp.matches("^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9A-Z][0-9]$");
    }
    
    // Validar formato RFC
    public boolean isValidRfc(String rfc) {
        if (rfc == null) return false;
        return rfc.matches("^[A-ZÑ&]{3,4}[0-9]{6}[A-V1-9][A-Z1-9][0-9A]$");
    }
    
    // Validar correo
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    // Eliminar docente (soft delete)
    public void delete(Long id) {
        Optional<Teacher> teacher = findById(id);
        if (teacher.isPresent()) {
            Teacher t = teacher.get();
            t.setActivo(false);
            teacherRepository.save(t);
        }
    }
    
    // Reactivar docente
    public void activate(Long id) {
        Optional<Teacher> teacher = findById(id);
        if (teacher.isPresent()) {
            Teacher t = teacher.get();
            t.setActivo(true);
            teacherRepository.save(t);
        }
    }
}
