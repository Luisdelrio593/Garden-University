package com.garden.university.config;

import com.garden.university.model.User;
import com.garden.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario administrador por defecto si no existe
        if (!userService.findByEmail("admin@garden.uni.edu").isPresent()) {
            User admin = new User(
                "admin@garden.uni.edu",
                "admin123",
                "ADMIN",
                "Administrador",
                "Sistema"
            );
            userService.createUser(admin);
            System.out.println("Usuario administrador creado: admin@garden.uni.edu / admin123");
        }

        // Crear usuario de prueba
        if (!userService.findByEmail("director@garden.uni.edu").isPresent()) {
            User director = new User(
                "director@garden.uni.edu",
                "director123",
                "ADMIN",
                "Director",
                "Académico"
            );
            userService.createUser(director);
            System.out.println("Usuario director creado: director@garden.uni.edu / director123");
        }
    }
}
