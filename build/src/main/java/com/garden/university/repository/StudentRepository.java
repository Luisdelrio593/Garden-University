package com.garden.university.repository;

import com.garden.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByCurp(String curp);
    
    Optional<Student> findByRfc(String rfc);
    
    Optional<Student> findByCorreoAlumno(String correoAlumno);
    
    Optional<Student> findByCorreoInstitucional(String correoInstitucional);
    
    List<Student> findByActivoTrue();
    
    List<Student> findByCuatrimestre(String cuatrimestre);
    
    @Query("SELECT s FROM Student s WHERE s.activo = true AND " +
           "(LOWER(s.nombres) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.apellidos) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.curp) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.correoAlumno) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Student> searchActiveStudents(@Param("search") String search);
    
    @Query("SELECT COUNT(s) FROM Student s WHERE s.activo = true")
    Long countActiveStudents();
    
    @Query("SELECT s.cuatrimestre, COUNT(s) FROM Student s WHERE s.activo = true GROUP BY s.cuatrimestre")
    List<Object[]> countStudentsByCuatrimestre();
}
