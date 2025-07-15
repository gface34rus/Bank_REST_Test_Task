package com.example.bankcards;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.RoleRepository;
import com.example.bankcards.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Component
public class TestUserInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public TestUserInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Создание тестового пользователя
        if (userRepository.findByUsername("testuser").isEmpty()) {
            Role userRole = roleRepository.findByName(Role.RoleName.USER)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(Role.RoleName.USER);
                        return roleRepository.save(newRole);
                    });
            User user = new User();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEmail("testuser@example.com");
            user.setRoles(Collections.singleton(userRole));
            userRepository.save(user);
            System.out.println("Тестовый пользователь создан: testuser / password");
        }
        // Создание тестового администратора
        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName(Role.RoleName.ADMIN)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(Role.RoleName.ADMIN);
                        return roleRepository.save(newRole);
                    });
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@example.com");
            admin.setRoles(Collections.singleton(adminRole));
            userRepository.save(admin);
            System.out.println("Тестовый администратор создан: admin / admin");
        }
    }
} 