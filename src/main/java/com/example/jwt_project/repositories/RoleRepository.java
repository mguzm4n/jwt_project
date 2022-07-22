package com.example.jwt_project.repositories;

import com.example.jwt_project.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByName(String name);
}
