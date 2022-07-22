package com.example.jwt_project;

import com.example.jwt_project.models.RoleModel;
import com.example.jwt_project.models.UserModel;
import com.example.jwt_project.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtProjectApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new RoleModel(null, "ROLE_USER"));
            userService.saveRole(new RoleModel(null, "ROLE_MODERATOR"));
            userService.saveRole(new RoleModel(null, "ROLE_ADMIN"));

            userService.saveUser(new UserModel(null, "Marcelo", "Guzman", "hi123",  "mguzman",
                    "marcelo@gmail.com",
                    new ArrayList<RoleModel>())
            );

            userService.addRoleToUser("mguzman", "ROLE_USER");
            userService.addRoleToUser("mguzman", "ROLE_MODERATOR");

            userService.saveUser(new UserModel(null, "Israel", "Gutierrez", "123hi",  "soysauce",
                    "israel@gmail.com",
                    new ArrayList<RoleModel>())
            );
        };
    }
}
