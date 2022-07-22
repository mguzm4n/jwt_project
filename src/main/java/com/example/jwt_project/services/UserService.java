package com.example.jwt_project.services;

import com.example.jwt_project.models.RoleModel;
import com.example.jwt_project.models.UserModel;
import com.example.jwt_project.repositories.UserRepository;

import java.util.List;

public interface UserService {

    UserModel saveUser(UserModel user);
    UserModel getUser(String username);
    List<UserModel> getAllUsers();
    RoleModel saveRole(RoleModel role);
    void addRoleToUser(String username, String role);

}
