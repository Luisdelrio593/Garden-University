package com.garden.university.repository;

import com.garden.university.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    // Buscar por CURP (único)
    Teacher findByCurp(String curp);
    
    // Buscar por RFC
    Teacher findByRfc(String rfc);
    
    // Buscar por correo institucional
    Teacher findByCorreoInstitucional(String correoInstitucional);
    
    // Buscar por cédula profesional
    Teacher findByCedulaProfesional(String cedulaProfesional);
    
    // Buscar activos/inactivos
    List<Teacher> findByActivoTrue();
    List<Teacher> findByActivoFalse();
    
    // Buscar por nombre (para búsquedas)
    List<Teacher> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombres, String apellidos);
    
    // Buscar por grado de estudio
    List<Teacher> findByGradoEstudio(String gradoEstudio);
}
