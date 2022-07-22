package com.example.jwt_project.controllers;

import com.example.jwt_project.models.UserModel;
import com.example.jwt_project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        URI uri = URI.create(
                ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/create")
                .toUriString()
        );
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
