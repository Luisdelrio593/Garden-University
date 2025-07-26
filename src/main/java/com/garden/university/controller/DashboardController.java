package com.garden.university.controller;

import com.garden.university.dto.ChangePasswordRequest;
import com.garden.university.dto.ChangePasswordResponse;
import com.garden.university.model.*;
import com.garden.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private TeacherSubjectAssignmentService assignmentService;
    
    @Autowired
    private AcademicProgramService academicProgramService;
    
    @Autowired
    private AdministrativeService administrativeService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        return "dashboard";
    }

    @GetMapping("/alumnos")
    public String alumnos(Model model, @RequestParam(required = false) String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("section", "Gestión de Alumnos");
        
        List<Student> students;
        if (search != null && !search.trim().isEmpty()) {
            students = studentService.searchStudents(search);
            model.addAttribute("search", search);
        } else {
            students = studentService.findAllActiveStudents();
        }
        
        model.addAttribute("students", students);
        model.addAttribute("totalStudents", studentService.countActiveStudents());
        model.addAttribute("modalidadesEstudio", studentService.getModalidadesEstudio());
        model.addAttribute("programasEstudio", studentService.getProgramasEstudio());
        return "alumnos";
    }
    
    @PostMapping("/alumnos/save")
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            // Validación básica: solo nombres, apellidos y correo institucional son obligatorios
            if (student.getNombres() == null || student.getNombres().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El nombre es obligatorio");
                return "redirect:/alumnos";
            }
            
            if (student.getApellidos() == null || student.getApellidos().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Los apellidos son obligatorios");
                return "redirect:/alumnos";
            }
            
            if (student.getCorreoInstitucional() == null || student.getCorreoInstitucional().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El correo institucional es obligatorio");
                return "redirect:/alumnos";
            }
            
            // Validaciones de unicidad solo si los campos no están vacíos
            if (student.getCurp() != null && !student.getCurp().trim().isEmpty() && 
                studentService.existsByCurp(student.getCurp().trim())) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un alumno con esa CURP");
                return "redirect:/alumnos";
            }
            
            if (student.getRfc() != null && !student.getRfc().trim().isEmpty() && 
                studentService.existsByRfc(student.getRfc().trim())) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un alumno con ese RFC");
                return "redirect:/alumnos";
            }
            
            if (student.getCorreoAlumno() != null && !student.getCorreoAlumno().trim().isEmpty() && 
                studentService.existsByCorreoAlumno(student.getCorreoAlumno().trim())) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un alumno con ese correo personal");
                return "redirect:/alumnos";
            }
            
            if (studentService.existsByCorreoInstitucional(student.getCorreoInstitucional().trim())) {
                redirectAttributes.addFlashAttribute("error", "Ya existe un alumno con ese correo institucional");
                return "redirect:/alumnos";
            }
            
            // Limpiar campos vacíos antes de guardar
            student = cleanEmptyFields(student);
            
            // Guardar el estudiante
            Student savedStudent = studentService.saveStudent(student);
            
            // Crear usuario automáticamente si se proporcionó contraseña
            if (savedStudent.getPasswordAcceso() != null && !savedStudent.getPasswordAcceso().trim().isEmpty()) {
                try {
                    createUserForStudent(savedStudent);
                } catch (Exception e) {
                    // Si falla la creación del usuario, registramos el error pero no falló el guardado del estudiante
                    redirectAttributes.addFlashAttribute("warning", 
                        "Alumno registrado exitosamente, pero hubo un error al crear las credenciales de acceso: " + e.getMessage());
                    return "redirect:/alumnos";
                }
            }
            
            redirectAttributes.addFlashAttribute("success", "Alumno registrado exitosamente" + 
                (student.getPasswordAcceso() != null && !student.getPasswordAcceso().trim().isEmpty() ? 
                " y credenciales de acceso creadas" : ""));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el alumno: " + e.getMessage());
        }
        return "redirect:/alumnos";
    }
    
    @PostMapping("/alumnos/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            Optional<Student> existingStudent = studentService.findById(id);
            if (!existingStudent.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
                return "redirect:/alumnos";
            }
            
            // Validación básica
            if (student.getNombres() == null || student.getNombres().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El nombre es obligatorio");
                return "redirect:/alumnos";
            }
            
            if (student.getApellidos() == null || student.getApellidos().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Los apellidos son obligatorios");
                return "redirect:/alumnos";
            }
            
            if (student.getCorreoInstitucional() == null || student.getCorreoInstitucional().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El correo institucional es obligatorio");
                return "redirect:/alumnos";
            }
            
            // Validaciones de unicidad excluyendo el registro actual
            if (student.getCurp() != null && !student.getCurp().trim().isEmpty() && 
                studentService.existsByCurpExcludingId(student.getCurp().trim(), id)) {
                redirectAttributes.addFlashAttribute("error", "Ya existe otro alumno con esa CURP");
                return "redirect:/alumnos";
            }
            
            if (student.getRfc() != null && !student.getRfc().trim().isEmpty() && 
                studentService.existsByRfcExcludingId(student.getRfc().trim(), id)) {
                redirectAttributes.addFlashAttribute("error", "Ya existe otro alumno con ese RFC");
                return "redirect:/alumnos";
            }
            
            if (student.getCorreoAlumno() != null && !student.getCorreoAlumno().trim().isEmpty() && 
                studentService.existsByCorreoAlumnoExcludingId(student.getCorreoAlumno().trim(), id)) {
                redirectAttributes.addFlashAttribute("error", "Ya existe otro alumno con ese correo personal");
                return "redirect:/alumnos";
            }
            
            if (studentService.existsByCorreoInstitucionalExcludingId(student.getCorreoInstitucional().trim(), id)) {
                redirectAttributes.addFlashAttribute("error", "Ya existe otro alumno con ese correo institucional");
                return "redirect:/alumnos";
            }
            
            // Limpiar campos vacíos antes de guardar
            student = cleanEmptyFields(student);
            student.setId(id);
            student.setActivo(existingStudent.get().getActivo()); // Mantener el estado activo original
            
            // Actualizar el estudiante
            Student updatedStudent = studentService.updateStudent(student);
            
            // Manejar credenciales de acceso
            if (updatedStudent.getPasswordAcceso() != null && !updatedStudent.getPasswordAcceso().trim().isEmpty()) {
                try {
                    // Verificar si ya existe usuario para este estudiante
                    Optional<User> existingUser = userService.findByEmail(updatedStudent.getCorreoInstitucional());
                    if (existingUser.isPresent()) {
                        // Actualizar contraseña del usuario existente
                        updateUserPassword(updatedStudent);
                    } else {
                        // Crear nuevo usuario
                        createUserForStudent(updatedStudent);
                    }
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("warning", 
                        "Alumno actualizado exitosamente, pero hubo un error al gestionar las credenciales de acceso: " + e.getMessage());
                    return "redirect:/alumnos";
                }
            }
            
            redirectAttributes.addFlashAttribute("success", "Alumno actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el alumno: " + e.getMessage());
        }
        return "redirect:/alumnos";
    }
    
    // Método auxiliar para limpiar campos vacíos
    private Student cleanEmptyFields(Student student) {
        if (student.getCurp() != null && student.getCurp().trim().isEmpty()) {
            student.setCurp(null);
        }
        if (student.getRfc() != null && student.getRfc().trim().isEmpty()) {
            student.setRfc(null);
        }
        if (student.getCorreoAlumno() != null && student.getCorreoAlumno().trim().isEmpty()) {
            student.setCorreoAlumno(null);
        }
        if (student.getPasswordAcceso() != null && student.getPasswordAcceso().trim().isEmpty()) {
            student.setPasswordAcceso(null);
        }
        if (student.getDomicilio() != null && student.getDomicilio().trim().isEmpty()) {
            student.setDomicilio(null);
        }
        if (student.getCelular() != null && student.getCelular().trim().isEmpty()) {
            student.setCelular(null);
        }
        if (student.getCelularEmergencia() != null && student.getCelularEmergencia().trim().isEmpty()) {
            student.setCelularEmergencia(null);
        }
        if (student.getGenero() != null && student.getGenero().trim().isEmpty()) {
            student.setGenero(null);
        }
        if (student.getEstadoCivil() != null && student.getEstadoCivil().trim().isEmpty()) {
            student.setEstadoCivil(null);
        }
        if (student.getCuatrimestre() != null && student.getCuatrimestre().trim().isEmpty()) {
            student.setCuatrimestre(null);
        }
        if (student.getNombrePadreTutor() != null && student.getNombrePadreTutor().trim().isEmpty()) {
            student.setNombrePadreTutor(null);
        }
        if (student.getConceptoPago() != null && student.getConceptoPago().trim().isEmpty()) {
            student.setConceptoPago(null);
        }
        if (student.getMetodoPago() != null && student.getMetodoPago().trim().isEmpty()) {
            student.setMetodoPago(null);
        }
        return student;
    }
    
    @PostMapping("/alumnos/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Alumno desactivado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al desactivar el alumno: " + e.getMessage());
        }
        return "redirect:/alumnos";
    }
    
    @GetMapping("/alumnos/edit/{id}")
    @ResponseBody
    public Student getStudentForEdit(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isEmpty()) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        return student.get();
    }    @GetMapping("/docentes")
    public String docentes(Model model, @RequestParam(required = false) String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("section", "Gestión de Docentes");
        
        List<Teacher> teachers;
        if (search != null && !search.trim().isEmpty()) {
            teachers = teacherService.searchByName(search);
            model.addAttribute("search", search);
        } else {
            teachers = teacherService.findActive();
        }
        
        model.addAttribute("teachers", teachers);
        model.addAttribute("newTeacher", new Teacher());
        return "docentes";
    }
    
    @PostMapping("/docentes/save")
    public String saveTeacher(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            // Validaciones
            if (!teacherService.isValidCurp(teacher.getCurp())) {
                redirectAttributes.addFlashAttribute("error", "CURP inválido. Formato requerido: AAAA000000HXXXXXX0");
                return "redirect:/docentes";
            }
            
            if (!teacherService.isValidRfc(teacher.getRfc())) {
                redirectAttributes.addFlashAttribute("error", "RFC inválido.");
                return "redirect:/docentes";
            }
            
            if (!teacherService.isValidEmail(teacher.getCorreoPersonal()) || 
                !teacherService.isValidEmail(teacher.getCorreoInstitucional())) {
                redirectAttributes.addFlashAttribute("error", "Formato de correo electrónico inválido.");
                return "redirect:/docentes";
            }
            
            teacherService.save(teacher);
            redirectAttributes.addFlashAttribute("success", "Docente guardado exitosamente.");
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        
        return "redirect:/docentes";
    }
    
    @PostMapping("/docentes/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            teacherService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Docente desactivado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/docentes";
    }
    
    @PostMapping("/docentes/activate/{id}")
    public String activateTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            teacherService.activate(id);
            redirectAttributes.addFlashAttribute("success", "Docente reactivado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al reactivar: " + e.getMessage());
        }
        return "redirect:/docentes";
    }
    
    // =============== GESTIÓN DE MATERIAS ===============
    
    @GetMapping("/materias")
    public String materias(Model model, @RequestParam(required = false) String cuatrimestre) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("section", "Gestión de Materias");
        
        List<Subject> subjects;
        if (cuatrimestre != null && !cuatrimestre.trim().isEmpty()) {
            subjects = subjectService.findActiveByCuatrimestre(cuatrimestre);
            model.addAttribute("selectedCuatrimestre", cuatrimestre);
        } else {
            subjects = subjectService.findActive();
        }
        
        model.addAttribute("subjects", subjects);
        model.addAttribute("newSubject", new Subject());
        model.addAttribute("cuatrimestres", subjectService.getAvailableCuatrimestres());
        return "materias";
    }
    
    @PostMapping("/materias/save")
    public String saveSubject(@ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        try {
            subjectService.save(subject);
            redirectAttributes.addFlashAttribute("success", "Materia guardada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/materias";
    }
    
    @PostMapping("/materias/delete/{id}")
    public String deleteSubject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            subjectService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Materia desactivada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/materias";
    }
    
    // =============== ASIGNACIÓN DE MATERIAS A DOCENTES ===============
    
    @GetMapping("/asignaciones")
    public String asignaciones(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("section", "Asignación de Materias");
        
        model.addAttribute("assignments", assignmentService.findActive());
        model.addAttribute("teachers", teacherService.findActive());
        model.addAttribute("subjects", subjectService.findActive());
        model.addAttribute("groups", assignmentService.getAvailableGroups());
        model.addAttribute("newAssignment", new TeacherSubjectAssignment());
        
        return "asignaciones";
    }
    
    @PostMapping("/asignaciones/save")
    public String saveAssignment(@ModelAttribute TeacherSubjectAssignment assignment, 
                               @RequestParam Long teacherId, 
                               @RequestParam Long subjectId,
                               RedirectAttributes redirectAttributes) {
        try {
            Optional<Teacher> teacher = teacherService.findById(teacherId);
            Optional<Subject> subject = subjectService.findById(subjectId);
            
            if (teacher.isPresent() && subject.isPresent()) {
                assignment.setTeacher(teacher.get());
                assignment.setSubject(subject.get());
                assignmentService.save(assignment);
                redirectAttributes.addFlashAttribute("success", "Asignación guardada exitosamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Docente o materia no encontrados.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/asignaciones";
    }
    
    @PostMapping("/asignaciones/delete/{id}")
    public String deleteAssignment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            assignmentService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Asignación eliminada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/asignaciones";
    }

    @PostMapping("/change-password")
    @ResponseBody
    public ChangePasswordResponse changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            
            // Validate input
            if (request.getCurrentPassword() == null || request.getCurrentPassword().trim().isEmpty()) {
                return new ChangePasswordResponse(false, "La contraseña actual es requerida.");
            }
            
            if (request.getNewPassword() == null || request.getNewPassword().trim().length() < 6) {
                return new ChangePasswordResponse(false, "La nueva contraseña debe tener al menos 6 caracteres.");
            }
            
            boolean success = userService.changePassword(email, request.getCurrentPassword(), request.getNewPassword());
            
            if (success) {
                return new ChangePasswordResponse(true, "Contraseña cambiada exitosamente.");
            } else {
                return new ChangePasswordResponse(false, "La contraseña actual es incorrecta.");
            }
            
        } catch (Exception e) {
            return new ChangePasswordResponse(false, "Error interno del servidor.");
        }
    }
    
    // Método auxiliar para crear usuario para estudiante
    private void createUserForStudent(Student student) {
        User user = new User();
        user.setEmail(student.getCorreoInstitucional());
        user.setPassword(student.getPasswordAcceso()); // Se encriptará en UserService.createUser()
        user.setFirstName(student.getNombres());
        user.setLastName(student.getApellidos());
        user.setRole("STUDENT");
        user.setEnabled(true);
        
        userService.createUser(user);
    }
    
    // Método auxiliar para actualizar contraseña de usuario existente
    private void updateUserPassword(Student student) {
        Optional<User> userOpt = userService.findByEmail(student.getCorreoInstitucional());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Actualizar otros campos también por si cambiaron
            user.setFirstName(student.getNombres());
            user.setLastName(student.getApellidos());
            // Usar el método específico para actualizar contraseña
            userService.updateUserPassword(student.getCorreoInstitucional(), student.getPasswordAcceso());
            // Guardar los cambios de nombre
            userService.createUser(user);
        }
    }
    
    // ===== PROGRAMAS ACADÉMICOS =====
    
    @GetMapping("/programas")
    public String programas(Model model, @RequestParam(required = false) String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        model.addAttribute("section", "Gestión de Programas Académicos");
        
        // Inicializar programas si no existen
        academicProgramService.initializeGardenUniversityPrograms();
        
        List<AcademicProgram> programs;
        if (search != null && !search.trim().isEmpty()) {
            // Aquí podrías implementar búsqueda si lo necesitas
            programs = academicProgramService.findAll();
        } else {
            programs = academicProgramService.findAll();
        }
        
        model.addAttribute("programs", programs);
        model.addAttribute("programTypes", academicProgramService.getProgramTypes());
        model.addAttribute("modalidades", academicProgramService.getModalidades());
        model.addAttribute("areasCurriculares", academicProgramService.getAreasCurriculares());
        model.addAttribute("cuatrimestres", academicProgramService.getAvailableCuatrimestres());
        model.addAttribute("teachers", teacherService.findActive());
        
        return "programas";
    }
    
    @PostMapping("/programas/save")
    public String saveProgram(@ModelAttribute AcademicProgram program, RedirectAttributes redirectAttributes) {
        try {
            academicProgramService.save(program);
            redirectAttributes.addFlashAttribute("successMessage", "Programa académico guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el programa: " + e.getMessage());
        }
        return "redirect:/programas";
    }
    
    @PostMapping("/programas/delete/{id}")
    public String deleteProgram(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            academicProgramService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Programa académico eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el programa: " + e.getMessage());
        }
        return "redirect:/programas";
    }
    
    @PostMapping("/programas/activate/{id}")
    public String activateProgram(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            academicProgramService.activate(id);
            redirectAttributes.addFlashAttribute("successMessage", "Programa académico reactivado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al reactivar el programa: " + e.getMessage());
        }
        return "redirect:/programas";
    }
    
    // ===== MATERIAS POR PROGRAMA =====
    
    @PostMapping("/programas/{programId}/materias/save")
    public String saveProgramSubject(@PathVariable Long programId, @ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        try {
            Optional<AcademicProgram> programOpt = academicProgramService.findById(programId);
            if (programOpt.isPresent()) {
                subject.setAcademicProgram(programOpt.get());
                subject.setActiva(true);
                subjectService.save(subject);
                redirectAttributes.addFlashAttribute("successMessage", "Materia agregada al programa exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Programa académico no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al agregar la materia: " + e.getMessage());
        }
        return "redirect:/programas";
    }
    
    // ==================== ADMINISTRATIVOS ====================
    
    @GetMapping("/administrativos")
    public String administrativos(Model model, @RequestParam(required = false) String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("userEmail", email);
        
        List<Administrative> administrativos;
        if (search != null && !search.trim().isEmpty()) {
            administrativos = administrativeService.searchAdministratives(search.trim());
            model.addAttribute("searchTerm", search);
        } else {
            administrativos = administrativeService.getAllActiveAdministratives();
        }
        
        model.addAttribute("administrativos", administrativos);
        model.addAttribute("totalAdministratives", administrativeService.getTotalActiveAdministratives());
        model.addAttribute("administrativeStats", administrativeService.getAdministrativeStatsByArea());
        model.addAttribute("areas", administrativeService.getAreas());
        model.addAttribute("areaDisplayNames", administrativeService.getAreaDisplayNames());
        
        return "administrativos";
    }
    
    @PostMapping("/administrativos/save")
    public String saveAdministrative(@ModelAttribute Administrative administrative, RedirectAttributes redirectAttributes) {
        try {
            // Validar campos únicos
            if (administrativeService.existsByCorreoInstitucional(administrative.getCorreoInstitucional())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Ya existe un administrativo con este correo institucional");
                return "redirect:/administrativos";
            }
            
            if (administrative.getCorreoPersonal() != null && !administrative.getCorreoPersonal().isEmpty() &&
                administrativeService.existsByCorreoPersonal(administrative.getCorreoPersonal())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Ya existe un administrativo con este correo personal");
                return "redirect:/administrativos";
            }
            
            if (administrative.getCurp() != null && !administrative.getCurp().isEmpty() &&
                administrativeService.existsByCurp(administrative.getCurp())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Ya existe un administrativo con esta CURP");
                return "redirect:/administrativos";
            }
            
            if (administrative.getRfc() != null && !administrative.getRfc().isEmpty() &&
                administrativeService.existsByRfc(administrative.getRfc())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Ya existe un administrativo con este RFC");
                return "redirect:/administrativos";
            }
            
            administrativeService.saveAdministrative(administrative);
            redirectAttributes.addFlashAttribute("successMessage", "Administrativo guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el administrativo: " + e.getMessage());
        }
        return "redirect:/administrativos";
    }
    
    @PostMapping("/administrativos/update/{id}")
    public String updateAdministrative(@PathVariable Long id, @ModelAttribute Administrative administrative, RedirectAttributes redirectAttributes) {
        try {
            Optional<Administrative> existingAdministrative = administrativeService.getAdministrativeById(id);
            if (existingAdministrative.isPresent()) {
                Administrative existing = existingAdministrative.get();
                
                // Actualizar campos
                existing.setNombres(administrative.getNombres());
                existing.setApellidos(administrative.getApellidos());
                existing.setCurp(administrative.getCurp());
                existing.setRfc(administrative.getRfc());
                existing.setDireccion(administrative.getDireccion());
                existing.setCorreoPersonal(administrative.getCorreoPersonal());
                existing.setArea(administrative.getArea());
                existing.setCelular(administrative.getCelular());
                existing.setExtension(administrative.getExtension());
                
                // Solo actualizar la contraseña si se proporciona una nueva
                if (administrative.getPasswordAcceso() != null && !administrative.getPasswordAcceso().isEmpty()) {
                    existing.setPasswordAcceso(administrative.getPasswordAcceso());
                }
                
                administrativeService.updateAdministrative(existing);
                redirectAttributes.addFlashAttribute("successMessage", "Administrativo actualizado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Administrativo no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el administrativo: " + e.getMessage());
        }
        return "redirect:/administrativos";
    }
    
    @PostMapping("/administrativos/deactivate/{id}")
    public String deactivateAdministrative(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            administrativeService.deactivateAdministrative(id);
            redirectAttributes.addFlashAttribute("successMessage", "Administrativo desactivado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al desactivar el administrativo: " + e.getMessage());
        }
        return "redirect:/administrativos";
    }
    
    @PostMapping("/administrativos/activate/{id}")
    public String activateAdministrative(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            administrativeService.activateAdministrative(id);
            redirectAttributes.addFlashAttribute("successMessage", "Administrativo activado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al activar el administrativo: " + e.getMessage());
        }
        return "redirect:/administrativos";
    }
    
    @GetMapping("/administrativos/area/{area}")
    @ResponseBody
    public List<Administrative> getAdministrativesByArea(@PathVariable String area) {
        return administrativeService.getAdministrativesByArea(area);
    }
}
