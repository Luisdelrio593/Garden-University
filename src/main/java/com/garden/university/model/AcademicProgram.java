package com.garden.university.model;

import jakarta.persistence.*;

@Entity
@Table(name = "academic_programs")
public class AcademicProgram {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String tipo; // Licenciatura, Ingeniería, Maestría
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(nullable = false)
    private Integer numeroCuatrimestres;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Información adicional
    @Column(length = 100)
    private String modalidad; // Escolarizada, Semiescolarizada, etc.
    
    @Column
    private Integer creditosTotales;
    
    @Column(length = 500)
    private String perfilEgreso;
    
    @Column(length = 500)
    private String campoLaboral;
    
    // Constructores
    public AcademicProgram() {}
    
    public AcademicProgram(String nombre, String tipo, Integer numeroCuatrimestres) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroCuatrimestres = numeroCuatrimestres;
        this.activo = true;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getNumeroCuatrimestres() {
        return numeroCuatrimestres;
    }
    
    public void setNumeroCuatrimestres(Integer numeroCuatrimestres) {
        this.numeroCuatrimestres = numeroCuatrimestres;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public String getModalidad() {
        return modalidad;
    }
    
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    public Integer getCreditosTotales() {
        return creditosTotales;
    }
    
    public void setCreditosTotales(Integer creditosTotales) {
        this.creditosTotales = creditosTotales;
    }
    
    public String getPerfilEgreso() {
        return perfilEgreso;
    }
    
    public void setPerfilEgreso(String perfilEgreso) {
        this.perfilEgreso = perfilEgreso;
    }
    
    public String getCampoLaboral() {
        return campoLaboral;
    }
    
    public void setCampoLaboral(String campoLaboral) {
        this.campoLaboral = campoLaboral;
    }
}
