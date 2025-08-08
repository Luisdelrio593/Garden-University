package com.garden.university.controller;

import com.garden.university.model.Student;
import com.garden.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador simplificado para Garden University
 * Solo administrador puede inscribir alumnos
 */
@Controller
public class SimpleController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<Student> students = studentService.findAllActiveStudents();
        model.addAttribute("students", students);
        model.addAttribute("newStudent", new Student());
        model.addAttribute("totalStudents", students.size());
        
        // Programas y modalidades disponibles
        model.addAttribute("programas", List.of(
            "Bachillerato",
            "Licenciatura en Enfermería",
            "Licenciatura en Psicología", 
            "Licenciatura en Derecho",
            "Licenciatura en Gestión Empresarial",
            "Ingeniería Mecatrónica",
            "Ingeniería Industrial",
            "Maestría en Gestión y Administración Pública"
        ));
        
        model.addAttribute("modalidades", List.of(
            "Escolarizada",
            "Semiescolarizada"
        ));
        
        return "admin-simple";
    }

    @PostMapping("/admin/students/save")
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            // Validaciones básicas
            if (student.getNombres() == null || student.getNombres().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El nombre es obligatorio");
                return "redirect:/admin";
            }
            
            if (student.getApellidos() == null || student.getApellidos().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Los apellidos son obligatorios");
                return "redirect:/admin";
            }
            
            if (student.getCorreoInstitucional() == null || student.getCorreoInstitucional().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El correo institucional es obligatorio");
                return "redirect:/admin";
            }
            
            if (student.getProgramaEstudios() == null || student.getProgramaEstudios().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El programa de estudios es obligatorio");
                return "redirect:/admin";
            }
            
            if (student.getModalidadEstudios() == null || student.getModalidadEstudios().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "La modalidad es obligatoria");
                return "redirect:/admin";
            }
            
            // Validar unicidad del correo institucional
            if (studentService.existsByCorreoInstitucional(student.getCorreoInstitucional().trim())) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un alumno con ese correo institucional");
                return "redirect:/admin";
            }
            
            // Establecer valores por defecto
            student.setActivo(true);
            
            // Guardar estudiante
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", 
                "Alumno " + student.getNombreCompleto() + " registrado exitosamente en " + 
                student.getProgramaEstudios() + " - " + student.getModalidadEstudios());
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar alumno: " + e.getMessage());
        }
        
        return "redirect:/admin";
    }

    @PostMapping("/admin/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Alumno desactivado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al desactivar alumno: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/students/count")
    @ResponseBody
    public String getStudentCount() {
        return "Total de alumnos activos: " + studentService.countActiveStudents();
    }
}
