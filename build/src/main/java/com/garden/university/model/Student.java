package com.garden.university.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombres;
    
    @Column(nullable = false)
    private String apellidos;
    
    @Column(nullable = true)
    private Integer edad;
    
    @Column(name = "fecha_nacimiento", nullable = true)
    private LocalDate fechaNacimiento;
    
    @Column(nullable = true)
    private String genero;
    
    @Column(name = "estado_civil", nullable = true)
    private String estadoCivil;
    
    @Column(nullable = true, unique = true)
    private String curp;
    
    @Column(nullable = true, unique = true)
    private String rfc;
    
    @Column(nullable = true)
    private String domicilio;
    
    @Column(nullable = true)
    private String celular;
    
    @Column(name = "celular_emergencia", nullable = true)
    private String celularEmergencia;
    
    @Column(name = "correo_alumno", nullable = true, unique = true)
    private String correoAlumno;
    
    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;
    
    @Column(name = "password_acceso", nullable = true)
    private String passwordAcceso;
    
    @Column(nullable = true)
    private String cuatrimestre;
    
    @Column(name = "modalidad_estudios", nullable = true)
    private String modalidadEstudios; // Escolarizada, Semiescolarizada, Abierta, Por Examen CENEVAL
    
    @Column(name = "programa_estudios", nullable = true)
    private String programaEstudios; // Bachillerato, Licenciatura, Maestría
    
    @Column(name = "concepto_pago")
    private String conceptoPago;
    
    @Column(name = "monto_pago")
    private Double montoPago;
    
    @Column(name = "metodo_pago")
    private String metodoPago;
    
    @Column(name = "nombre_padre_tutor", nullable = true)
    private String nombrePadreTutor;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Constructores
    public Student() {}
    
    public Student(String nombres, String apellidos, String correoInstitucional) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
    }
    
    public Student(String nombres, String apellidos, Integer edad, LocalDate fechaNacimiento, 
                  String genero, String estadoCivil, String curp, String rfc, String domicilio,
                  String celular, String celularEmergencia, String correoAlumno, 
                  String correoInstitucional, String passwordAcceso, String cuatrimestre, String nombrePadreTutor) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.curp = curp;
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.celular = celular;
        this.celularEmergencia = celularEmergencia;
        this.correoAlumno = correoAlumno;
        this.correoInstitucional = correoInstitucional;
        this.passwordAcceso = passwordAcceso;
        this.cuatrimestre = cuatrimestre;
        this.nombrePadreTutor = nombrePadreTutor;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }
    
    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }
    
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    
    public String getCelularEmergencia() { return celularEmergencia; }
    public void setCelularEmergencia(String celularEmergencia) { this.celularEmergencia = celularEmergencia; }
    
    public String getCorreoAlumno() { return correoAlumno; }
    public void setCorreoAlumno(String correoAlumno) { this.correoAlumno = correoAlumno; }
    
    public String getCorreoInstitucional() { return correoInstitucional; }
    public void setCorreoInstitucional(String correoInstitucional) { this.correoInstitucional = correoInstitucional; }
    
    public String getPasswordAcceso() { return passwordAcceso; }
    public void setPasswordAcceso(String passwordAcceso) { this.passwordAcceso = passwordAcceso; }
    
    public String getCuatrimestre() { return cuatrimestre; }
    public void setCuatrimestre(String cuatrimestre) { this.cuatrimestre = cuatrimestre; }
    
    public String getModalidadEstudios() { return modalidadEstudios; }
    public void setModalidadEstudios(String modalidadEstudios) { this.modalidadEstudios = modalidadEstudios; }
    
    public String getProgramaEstudios() { return programaEstudios; }
    public void setProgramaEstudios(String programaEstudios) { this.programaEstudios = programaEstudios; }
    
    public String getConceptoPago() { return conceptoPago; }
    public void setConceptoPago(String conceptoPago) { this.conceptoPago = conceptoPago; }
    
    public Double getMontoPago() { return montoPago; }
    public void setMontoPago(Double montoPago) { this.montoPago = montoPago; }
    
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    
    public String getNombrePadreTutor() { return nombrePadreTutor; }
    public void setNombrePadreTutor(String nombrePadreTutor) { this.nombrePadreTutor = nombrePadreTutor; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
