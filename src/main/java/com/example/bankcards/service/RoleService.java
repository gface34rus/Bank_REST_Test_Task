package com.example.bankcards.service;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.Role.RoleName;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
    Role createRole(Role role);
    List<Role> listRoles();
} 