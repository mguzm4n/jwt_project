package com.example.jwt_project.services;

import com.example.jwt_project.models.RoleModel;
import com.example.jwt_project.models.UserModel;
import com.example.jwt_project.repositories.RoleRepository;
import com.example.jwt_project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor // Create a constructor and set repositories automatically.
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserModel saveUser(UserModel user) {
        log.info("Saving new user {} to the database", user.getName());
        return userRepository.save(user);
    }

    @Override
    public UserModel getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserModel user = userRepository.findByUsername(username);
        RoleModel role = roleRepository.findByName(roleName);
        log.info("Adding new role {} to user {}", role.getName(), user.getName());
        user.getRoles().add(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roleModel -> {
            authorities.add(new SimpleGrantedAuthority(roleModel.getName()));
        });

        return new
                org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities
        );
    }
}
