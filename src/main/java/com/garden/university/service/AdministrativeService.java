package com.garden.university.service;

import com.garden.university.model.Administrative;
import com.garden.university.repository.AdministrativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrativeService {
    
    @Autowired
    private AdministrativeRepository administrativeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Obtener todos los administrativos activos
    public List<Administrative> getAllActiveAdministratives() {
        return administrativeRepository.findByActivoTrueOrderByApellidosAscNombresAsc();
    }
    
    // Obtener administrativo por ID
    public Optional<Administrative> getAdministrativeById(Long id) {
        return administrativeRepository.findById(id);
    }
    
    // Obtener administrativo por correo institucional
    public Optional<Administrative> getAdministrativeByCorreoInstitucional(String correoInstitucional) {
        return administrativeRepository.findByCorreoInstitucional(correoInstitucional);
    }
    
    // Guardar administrativo
    public Administrative saveAdministrative(Administrative administrative) {
        // Encriptar la contraseña si es nueva o ha cambiado
        if (administrative.getId() == null || 
            !administrative.getPasswordAcceso().startsWith("$2a$")) {
            administrative.setPasswordAcceso(passwordEncoder.encode(administrative.getPasswordAcceso()));
        }
        
        // Establecer fecha de ingreso si es nuevo
        if (administrative.getId() == null) {
            administrative.setFechaIngreso(LocalDate.now());
        }
        
        return administrativeRepository.save(administrative);
    }
    
    // Actualizar administrativo
    public Administrative updateAdministrative(Administrative administrative) {
        return administrativeRepository.save(administrative);
    }
    
    // Desactivar administrativo (no eliminamos, solo desactivamos)
    public void deactivateAdministrative(Long id) {
        Optional<Administrative> administrative = administrativeRepository.findById(id);
        if (administrative.isPresent()) {
            Administrative admin = administrative.get();
            admin.setActivo(false);
            administrativeRepository.save(admin);
        }
    }
    
    // Activar administrativo
    public void activateAdministrative(Long id) {
        Optional<Administrative> administrative = administrativeRepository.findById(id);
        if (administrative.isPresent()) {
            Administrative admin = administrative.get();
            admin.setActivo(true);
            administrativeRepository.save(admin);
        }
    }
    
    // Obtener administrativos por área
    public List<Administrative> getAdministrativesByArea(String area) {
        return administrativeRepository.findByAreaAndActivoTrueOrderByApellidosAscNombresAsc(area);
    }
    
    // Buscar administrativos
    public List<Administrative> searchAdministratives(String searchTerm) {
        return administrativeRepository.findByNombresOrApellidosContainingIgnoreCase(searchTerm);
    }
    
    // Verificar si existe correo institucional
    public boolean existsByCorreoInstitucional(String correoInstitucional) {
        return administrativeRepository.findByCorreoInstitucional(correoInstitucional).isPresent();
    }
    
    // Verificar si existe correo personal
    public boolean existsByCorreoPersonal(String correoPersonal) {
        return administrativeRepository.findByCorreoPersonal(correoPersonal).isPresent();
    }
    
    // Verificar si existe CURP
    public boolean existsByCurp(String curp) {
        return administrativeRepository.findByCurp(curp).isPresent();
    }
    
    // Verificar si existe RFC
    public boolean existsByRfc(String rfc) {
        return administrativeRepository.findByRfc(rfc).isPresent();
    }
    
    // Obtener estadísticas
    public long getTotalActiveAdministratives() {
        return administrativeRepository.countActiveAdministratives();
    }
    
    public List<Object[]> getAdministrativeStatsByArea() {
        return administrativeRepository.countAdministrativesByArea();
    }
    
    // Métodos helper para obtener listas de opciones
    public List<String> getAreas() {
        return List.of(
            "SUBDIRECCION",
            "COORDINACION_ACADEMICA", 
            "COORDINACION_SERVICIOS_ESCOLARES"
        );
    }
    
    public List<String> getAreaDisplayNames() {
        return List.of(
            "Subdirección",
            "Coordinación Académica",
            "Coordinación de Servicios Escolares"
        );
    }
    
    // Obtener nombre de área para mostrar
    public String getAreaDisplayName(String area) {
        return switch (area) {
            case "SUBDIRECCION" -> "Subdirección";
            case "COORDINACION_ACADEMICA" -> "Coordinación Académica";
            case "COORDINACION_SERVICIOS_ESCOLARES" -> "Coordinación de Servicios Escolares";
            default -> area;
        };
    }
    
    // Validar permisos de administrativo
    public boolean hasFullAccess(String area) {
        return "SUBDIRECCION".equals(area);
    }
    
    public boolean hasStudentAccess(String area) {
        return "COORDINACION_ACADEMICA".equals(area) || 
               "COORDINACION_SERVICIOS_ESCOLARES".equals(area) ||
               "SUBDIRECCION".equals(area);
    }
    
    public boolean hasTeacherAssignmentAccess(String area) {
        return "COORDINACION_ACADEMICA".equals(area) || 
               "COORDINACION_SERVICIOS_ESCOLARES".equals(area) ||
               "SUBDIRECCION".equals(area);
    }
}
