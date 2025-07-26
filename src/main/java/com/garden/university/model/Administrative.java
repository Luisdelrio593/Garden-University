package com.garden.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "administratives")
public class Administrative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombres;
    
    @Column(nullable = false)
    private String apellidos;
    
    @Column(unique = true)
    private String curp;
    
    @Column(unique = true)
    private String rfc;
    
    @Column
    private String direccion;
    
    @Column(unique = true)
    private String correoPersonal;
    
    @Column(nullable = false, unique = true)
    private String correoInstitucional;
    
    @Column(nullable = false)
    private String passwordAcceso;
    
    @Column(nullable = false)
    private String area; // SUBDIRECCION, COORDINACION_ACADEMICA, COORDINACION_SERVICIOS_ESCOLARES
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @Column
    private LocalDate fechaIngreso;
    
    @Column
    private String celular;
    
    @Column
    private String extension;
    
    // Constructores
    public Administrative() {}
    
    public Administrative(String nombres, String apellidos, String correoInstitucional, String area) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
        this.area = area;
        this.activo = true;
        this.fechaIngreso = LocalDate.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getCurp() {
        return curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    public String getRfc() {
        return rfc;
    }
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCorreoPersonal() {
        return correoPersonal;
    }
    
    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }
    
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }
    
    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
    
    public String getPasswordAcceso() {
        return passwordAcceso;
    }
    
    public void setPasswordAcceso(String passwordAcceso) {
        this.passwordAcceso = passwordAcceso;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public String getCelular() {
        return celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    // Método helper para nombre completo
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
    
    // Método helper para verificar si es subdirección
    public boolean isSubdireccion() {
        return "SUBDIRECCION".equals(area);
    }
    
    // Método helper para verificar permisos de coordinación
    public boolean isCoordinacion() {
        return "COORDINACION_ACADEMICA".equals(area) || "COORDINACION_SERVICIOS_ESCOLARES".equals(area);
    }
}
