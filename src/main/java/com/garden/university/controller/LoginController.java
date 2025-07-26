package com.garden.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Credenciales inválidas. Asegúrate de usar un email con terminación @garden.uni.edu");
        }
        
        if (logout != null) {
            model.addAttribute("successMessage", "Has cerrado sesión exitosamente");
        }
        
        return "login";
    }
}
