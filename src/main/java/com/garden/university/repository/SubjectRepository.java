package com.garden.university.repository;

import com.garden.university.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    // Buscar por cuatrimestre
    List<Subject> findByCuatrimestre(String cuatrimestre);
    
    // Buscar materias activas
    List<Subject> findByActivaTrue();
    List<Subject> findByActivaFalse();
    
    // Buscar por nombre (para búsquedas)
    List<Subject> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar por cuatrimestre y activas
    List<Subject> findByCuatrimestreAndActivaTrue(String cuatrimestre);
    
    // Ordenar por cuatrimestre y nombre
    List<Subject> findAllByOrderByCuatrimestreAscNombreAsc();
}
