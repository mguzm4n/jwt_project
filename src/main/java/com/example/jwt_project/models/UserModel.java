package com.example.jwt_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name, lastname;
    private String password;
    private String username, email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleModel> roles = new ArrayList<>();
}
