package com.garden.university.controller;

import com.garden.university.model.*;
import com.garden.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/asignaciones")
public class AssignmentController {
    
    @Autowired
    private StudentSubjectAssignmentService studentSubjectAssignmentService;
    
    @Autowired
    private TeacherSubjectAssignmentService teacherSubjectAssignmentService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private SubjectService subjectService;
    
    @GetMapping
    public String showAssignments(Model model) {
        model.addAttribute("studentAssignments", studentSubjectAssignmentService.findAll());
        model.addAttribute("teacherAssignments", teacherSubjectAssignmentService.findAll());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        
        return "asignaciones";
    }
    
    @PostMapping("/student")
    public String createStudentAssignment(@RequestParam Long studentId,
                                        @RequestParam Long subjectId,
                                        @RequestParam Long teacherAssignmentId,
                                        @RequestParam String grupo,
                                        @RequestParam String cuatrimestre,
                                        @RequestParam String programaEstudios,
                                        @RequestParam String modalidad,
                                        RedirectAttributes redirectAttributes) {
        try {
            StudentSubjectAssignment assignment = studentSubjectAssignmentService.createAssignment(
                studentId, subjectId, teacherAssignmentId, grupo, cuatrimestre, programaEstudios, modalidad);
            
            if (assignment != null) {
                redirectAttributes.addFlashAttribute("success", "Asignación de estudiante creada exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo crear la asignación. Puede que ya exista.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la asignación: " + e.getMessage());
        }
        
        return "redirect:/asignaciones";
    }
    
    @PostMapping("/teacher")
    public String createTeacherAssignment(@RequestParam Long teacherId,
                                        @RequestParam Long subjectId,
                                        @RequestParam String grupo,
                                        RedirectAttributes redirectAttributes) {
        try {
            Optional<Teacher> teacher = teacherService.findById(teacherId);
            Optional<Subject> subject = subjectService.findById(subjectId);
            
            if (teacher.isPresent() && subject.isPresent()) {
                TeacherSubjectAssignment assignment = new TeacherSubjectAssignment(teacher.get(), subject.get(), grupo);
                teacherSubjectAssignmentService.save(assignment);
                redirectAttributes.addFlashAttribute("success", "Asignación de docente creada exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "Docente o materia no encontrados");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la asignación: " + e.getMessage());
        }
        
        return "redirect:/asignaciones";
    }
    
    @GetMapping("/teacher/{teacherId}/grupo/{grupo}")
    @ResponseBody
    public List<StudentSubjectAssignment> getStudentsByTeacherAndGroup(@PathVariable Long teacherId, @PathVariable String grupo) {
        return studentSubjectAssignmentService.findByTeacherAndGrupo(teacherId, grupo);
    }
    
    @GetMapping("/programa/{programa}/cuatrimestre/{cuatrimestre}/modalidad/{modalidad}")
    @ResponseBody
    public List<StudentSubjectAssignment> getStudentsByProgram(@PathVariable String programa, 
                                                             @PathVariable String cuatrimestre,
                                                             @PathVariable String modalidad) {
        return studentSubjectAssignmentService.findByPrograma(programa, cuatrimestre, modalidad);
    }
    
    @DeleteMapping("/{id}")
    public String deleteAssignment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentSubjectAssignmentService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Asignación eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la asignación: " + e.getMessage());
        }
        
        return "redirect:/asignaciones";
    }
}
