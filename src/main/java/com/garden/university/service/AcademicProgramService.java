package com.garden.university.service;

import com.garden.university.model.AcademicProgram;
import com.garden.university.repository.AcademicProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicProgramService {
    
    @Autowired
    private AcademicProgramRepository academicProgramRepository;
    
    // Guardar programa académico
    public AcademicProgram save(AcademicProgram program) {
        return academicProgramRepository.save(program);
    }
    
    // Obtener todos los programas
    public List<AcademicProgram> findAll() {
        return academicProgramRepository.findByActivoTrueOrderByTipoAscNombreAsc();
    }
    
    // Obtener programas activos
    public List<AcademicProgram> findActive() {
        return academicProgramRepository.findByActivoTrue();
    }
    
    // Buscar por ID
    public Optional<AcademicProgram> findById(Long id) {
        return academicProgramRepository.findById(id);
    }
    
    // Buscar por tipo
    public List<AcademicProgram> findByType(String tipo) {
        return academicProgramRepository.findByTipoAndActivoTrue(tipo);
    }
    
    // Buscar por nombre
    public Optional<AcademicProgram> findByName(String nombre) {
        return academicProgramRepository.findByNombreAndActivoTrue(nombre);
    }
    
    // Eliminar programa (soft delete)
    public void delete(Long id) {
        Optional<AcademicProgram> program = findById(id);
        if (program.isPresent()) {
            AcademicProgram p = program.get();
            p.setActivo(false);
            academicProgramRepository.save(p);
        }
    }
    
    // Reactivar programa
    public void activate(Long id) {
        Optional<AcademicProgram> program = findById(id);
        if (program.isPresent()) {
            AcademicProgram p = program.get();
            p.setActivo(true);
            academicProgramRepository.save(p);
        }
    }
    
    // Estadísticas
    public Long countActivePrograms() {
        return academicProgramRepository.countActivePrograms();
    }
    
    public List<Object[]> getProgramStatsByType() {
        return academicProgramRepository.countProgramsByType();
    }
    
    // Métodos helper para obtener tipos y modalidades
    public List<String> getProgramTypes() {
        return List.of("Licenciatura", "Ingeniería", "Maestría");
    }
    
    public List<String> getModalidades() {
        return List.of("Escolarizada", "Semi", "Abierta", "Por Examen CENEVAL");
    }
    
    public List<String> getAreasCurriculares() {
        return List.of("Profesional", "Transversal", "General");
    }
    
    // Obtener cuatrimestres disponibles
    public List<String> getAvailableCuatrimestres() {
        return List.of("1°", "2°", "3°", "4°", "5°", "6°", "7°", "8°", "9°");
    }
    
    // Inicializar programas de Garden University
    public void initializeGardenUniversityPrograms() {
        // Verificar si ya existen programas
        if (academicProgramRepository.countActivePrograms() == 0) {
            
            // Licenciaturas
            createProgram("Licenciatura en Enfermería", "Licenciatura", 9, 
                "Escolarizada y Semi", 
                "Formar profesionales de enfermería competentes en el cuidado integral de la salud",
                "Hospitales, clínicas, centros de salud, empresas, consultorios");
                
            createProgram("Licenciatura en Psicología", "Licenciatura", 9,
                "Escolarizada y Semi",
                "Formar psicólogos con bases científicas y humanísticas sólidas",
                "Consultorios, hospitales, empresas, centros educativos, investigación");
                
            createProgram("Licenciatura en Derecho", "Licenciatura", 9,
                "Escolarizada y Semi",
                "Formar abogados con sólida preparación jurídica y ética profesional",
                "Despachos jurídicos, tribunales, empresas, sector público");
                
            createProgram("Licenciatura en Gestión Empresarial", "Licenciatura", 9,
                "Escolarizada y Semi",
                "Formar gestores empresariales innovadores y competitivos",
                "Empresas privadas, sector público, emprendimiento, consultoría");
            
            // Ingenierías
            createProgram("Ingeniería Mecatrónica", "Ingeniería", 9,
                "Escolarizada",
                "Formar ingenieros especializados en sistemas mecatrónicos",
                "Industria automotriz, robótica, automatización, manufactura");
                
            createProgram("Ingeniería Industrial", "Ingeniería", 9,
                "Escolarizada",
                "Formar ingenieros en optimización de procesos y sistemas productivos",
                "Industria manufacturera, logística, calidad, consultoría");
            
            // Maestría
            createProgram("Maestría en Gestión y Administración Pública", "Maestría", 6,
                "Escolarizada y Semi",
                "Formar especialistas en administración pública moderna",
                "Sector público, organismos internacionales, consultoría gubernamental");
        }
    }
    
    private void createProgram(String nombre, String tipo, Integer cuatrimestres, 
                              String modalidad, String perfilEgreso, String campoLaboral) {
        AcademicProgram program = new AcademicProgram(nombre, tipo, cuatrimestres);
        program.setModalidad(modalidad);
        program.setPerfilEgreso(perfilEgreso);
        program.setCampoLaboral(campoLaboral);
        program.setDescripcion("Programa académico de " + nombre + " de Garden University");
        
        // Calcular créditos aproximados
        int creditosBase = tipo.equals("Maestría") ? 45 : 65;
        program.setCreditosTotales(creditosBase * cuatrimestres / 9);
        
        academicProgramRepository.save(program);
    }
}
