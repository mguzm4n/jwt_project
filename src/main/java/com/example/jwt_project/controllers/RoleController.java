package com.example.jwt_project.controllers;

import com.example.jwt_project.models.RoleModel;
import com.example.jwt_project.models.UserModel;
import com.example.jwt_project.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel role){
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/role/create")
                        .toUriString()
        );
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/addtouser")
    public ResponseEntity<UserModel> createUser(@RequestBody AddRoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
}

@Data
class AddRoleToUserForm {
    private String username;
    private String rolename;
}