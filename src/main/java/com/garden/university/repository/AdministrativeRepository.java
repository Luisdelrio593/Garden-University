package com.garden.university.repository;

import com.garden.university.model.Administrative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministrativeRepository extends JpaRepository<Administrative, Long> {
    
    // Buscar por correo institucional
    Optional<Administrative> findByCorreoInstitucional(String correoInstitucional);
    
    // Buscar por correo personal
    Optional<Administrative> findByCorreoPersonal(String correoPersonal);
    
    // Buscar por CURP
    Optional<Administrative> findByCurp(String curp);
    
    // Buscar por RFC
    Optional<Administrative> findByRfc(String rfc);
    
    // Encontrar todos los administrativos activos
    List<Administrative> findByActivoTrueOrderByApellidosAscNombresAsc();
    
    // Encontrar por área
    List<Administrative> findByAreaAndActivoTrueOrderByApellidosAscNombresAsc(String area);
    
    // Contar administrativos activos
    @Query("SELECT COUNT(a) FROM Administrative a WHERE a.activo = true")
    long countActiveAdministratives();
    
    // Contar por área
    @Query("SELECT COUNT(a) FROM Administrative a WHERE a.area = :area AND a.activo = true")
    long countByAreaAndActivo(String area);
    
    // Estadísticas por área
    @Query("SELECT a.area, COUNT(a) FROM Administrative a WHERE a.activo = true GROUP BY a.area")
    List<Object[]> countAdministrativesByArea();
    
    // Buscar por nombre o apellido (para búsquedas)
    @Query("SELECT a FROM Administrative a WHERE a.activo = true AND " +
           "(LOWER(a.nombres) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.apellidos) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Administrative> findByNombresOrApellidosContainingIgnoreCase(String searchTerm);
}
