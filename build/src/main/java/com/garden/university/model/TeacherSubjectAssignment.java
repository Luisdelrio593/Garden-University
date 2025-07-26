package com.garden.university.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subject_assignments")
public class TeacherSubjectAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @Column(name = "grupo")
    private String grupo; // A, B, C, etc.
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    // Constructores
    public TeacherSubjectAssignment() {}
    
    public TeacherSubjectAssignment(Teacher teacher, Subject subject, String grupo) {
        this.teacher = teacher;
        this.subject = subject;
        this.grupo = grupo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}
