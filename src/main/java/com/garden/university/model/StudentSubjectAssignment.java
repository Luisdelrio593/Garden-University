package com.garden.university.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_subject_assignments")
public class StudentSubjectAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @ManyToOne
    @JoinColumn(name = "teacher_assignment_id", nullable = false)
    private TeacherSubjectAssignment teacherAssignment;
    
    @Column(name = "grupo")
    private String grupo; // A, B, C, etc.
    
    @Column(name = "cuatrimestre")
    private String cuatrimestre;
    
    @Column(name = "programa_estudios")
    private String programaEstudios;
    
    @Column(name = "modalidad")
    private String modalidad;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    // Constructores
    public StudentSubjectAssignment() {}
    
    public StudentSubjectAssignment(Student student, Subject subject, TeacherSubjectAssignment teacherAssignment, 
                                  String grupo, String cuatrimestre, String programaEstudios, String modalidad) {
        this.student = student;
        this.subject = subject;
        this.teacherAssignment = teacherAssignment;
        this.grupo = grupo;
        this.cuatrimestre = cuatrimestre;
        this.programaEstudios = programaEstudios;
        this.modalidad = modalidad;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    
    public TeacherSubjectAssignment getTeacherAssignment() { return teacherAssignment; }
    public void setTeacherAssignment(TeacherSubjectAssignment teacherAssignment) { this.teacherAssignment = teacherAssignment; }
    
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    
    public String getCuatrimestre() { return cuatrimestre; }
    public void setCuatrimestre(String cuatrimestre) { this.cuatrimestre = cuatrimestre; }
    
    public String getProgramaEstudios() { return programaEstudios; }
    public void setProgramaEstudios(String programaEstudios) { this.programaEstudios = programaEstudios; }
    
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    
    @Override
    public String toString() {
        return "StudentSubjectAssignment{" +
                "id=" + id +
                ", student=" + (student != null ? student.getNombres() + " " + student.getApellidos() : "null") +
                ", subject=" + (subject != null ? subject.getNombre() : "null") +
                ", grupo='" + grupo + '\'' +
                ", cuatrimestre='" + cuatrimestre + '\'' +
                ", programaEstudios='" + programaEstudios + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", activa=" + activa +
                '}';
    }
}
