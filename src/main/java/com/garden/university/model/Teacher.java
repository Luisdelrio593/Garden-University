package com.garden.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombres;
    
    @Column(nullable = false)
    private String apellidos;
    
    @Column(unique = true)
    private String curp;
    
    @Column
    private Integer edad;
    
    @Column
    private String genero;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Column
    private String domicilio;
    
    @Column(unique = true)
    private String rfc;
    
    @Column(name = "grado_estudio")
    private String gradoEstudio; // Licenciatura, Maestría, Doctorado
    
    @Column(name = "cedula_profesional", unique = true)
    private String cedulaProfesional;
    
    @Column(name = "correo_personal", unique = true)
    private String correoPersonal;
    
    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;
    
    @Column(name = "password_acceso")
    private String passwordAcceso;
    
    @Column
    private String celular;
    
    @Column(name = "celular_emergencia")
    private String celularEmergencia;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Constructores
    public Teacher() {}
    
    public Teacher(String nombres, String apellidos, String correoInstitucional) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
    }
    
    public Teacher(String nombres, String apellidos, String curp, Integer edad, String genero, 
                  LocalDate fechaNacimiento, String domicilio, String rfc, String gradoEstudio,
                  String cedulaProfesional, String correoPersonal, String correoInstitucional,
                  String passwordAcceso, String celular, String celularEmergencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.curp = curp;
        this.edad = edad;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.gradoEstudio = gradoEstudio;
        this.cedulaProfesional = cedulaProfesional;
        this.correoPersonal = correoPersonal;
        this.correoInstitucional = correoInstitucional;
        this.passwordAcceso = passwordAcceso;
        this.celular = celular;
        this.celularEmergencia = celularEmergencia;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }
    
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    
    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }
    
    public String getGradoEstudio() { return gradoEstudio; }
    public void setGradoEstudio(String gradoEstudio) { this.gradoEstudio = gradoEstudio; }
    
    public String getCedulaProfesional() { return cedulaProfesional; }
    public void setCedulaProfesional(String cedulaProfesional) { this.cedulaProfesional = cedulaProfesional; }
    
    public String getCorreoPersonal() { return correoPersonal; }
    public void setCorreoPersonal(String correoPersonal) { this.correoPersonal = correoPersonal; }
    
    public String getCorreoInstitucional() { return correoInstitucional; }
    public void setCorreoInstitucional(String correoInstitucional) { this.correoInstitucional = correoInstitucional; }
    
    public String getPasswordAcceso() { return passwordAcceso; }
    public void setPasswordAcceso(String passwordAcceso) { this.passwordAcceso = passwordAcceso; }
    
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    
    public String getCelularEmergencia() { return celularEmergencia; }
    public void setCelularEmergencia(String celularEmergencia) { this.celularEmergencia = celularEmergencia; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    // Método helper para obtener nombre completo
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
