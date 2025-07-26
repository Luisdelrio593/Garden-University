package com.garden.university.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column
    private String descripcion;
    
    @Column
    private String cuatrimestre; // 1°, 2°, 3°, etc.
    
    @Column
    private Integer creditos;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    // Nuevos campos solicitados
    @Column(length = 20)
    private String claveAsignatura; // Clave única de la materia
    
    @Column
    private String areaCurricular; // Profesional, Transversal, General
    
    @Column
    private Boolean practicasProfesionales = false;
    
    @Column
    private Boolean servicioSocial = false;
    
    // Relación con programa académico
    @ManyToOne
    @JoinColumn(name = "academic_program_id")
    private AcademicProgram academicProgram;
    
    // Relación con teacher para el docente asignado
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    // Constructores
    public Subject() {}
    
    public Subject(String nombre, String cuatrimestre) {
        this.nombre = nombre;
        this.cuatrimestre = cuatrimestre;
    }
    
    public Subject(String nombre, String descripcion, String cuatrimestre, Integer creditos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cuatrimestre = cuatrimestre;
        this.creditos = creditos;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCuatrimestre() { return cuatrimestre; }
    public void setCuatrimestre(String cuatrimestre) { this.cuatrimestre = cuatrimestre; }
    
    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }
    
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    
    // Getters y Setters para los nuevos campos
    public String getClaveAsignatura() { return claveAsignatura; }
    public void setClaveAsignatura(String claveAsignatura) { this.claveAsignatura = claveAsignatura; }
    
    public String getAreaCurricular() { return areaCurricular; }
    public void setAreaCurricular(String areaCurricular) { this.areaCurricular = areaCurricular; }
    
    public Boolean getPracticasProfesionales() { return practicasProfesionales; }
    public void setPracticasProfesionales(Boolean practicasProfesionales) { this.practicasProfesionales = practicasProfesionales; }
    
    public Boolean getServicioSocial() { return servicioSocial; }
    public void setServicioSocial(Boolean servicioSocial) { this.servicioSocial = servicioSocial; }
    
    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }
    
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}
