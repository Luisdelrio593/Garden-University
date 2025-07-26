package com.garden.university.repository;

import com.garden.university.model.AcademicProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Long> {
    
    // Buscar programas activos
    List<AcademicProgram> findByActivoTrue();
    
    // Buscar por tipo (Licenciatura, Ingeniería, Maestría)
    List<AcademicProgram> findByTipoAndActivoTrue(String tipo);
    
    // Buscar por nombre
    Optional<AcademicProgram> findByNombre(String nombre);
    
    // Buscar por nombre (solo activos)
    Optional<AcademicProgram> findByNombreAndActivoTrue(String nombre);
    
    // Contar programas activos
    @Query("SELECT COUNT(ap) FROM AcademicProgram ap WHERE ap.activo = true")
    Long countActivePrograms();
    
    // Estadísticas por tipo
    @Query("SELECT ap.tipo, COUNT(ap) FROM AcademicProgram ap WHERE ap.activo = true GROUP BY ap.tipo")
    List<Object[]> countProgramsByType();
    
    // Buscar programas ordenados por tipo y nombre
    List<AcademicProgram> findByActivoTrueOrderByTipoAscNombreAsc();
    
    // Buscar programas con modalidad específica
    List<AcademicProgram> findByModalidadAndActivoTrue(String modalidad);
}
