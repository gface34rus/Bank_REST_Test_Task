package com.example.bankcards.repository;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
} 