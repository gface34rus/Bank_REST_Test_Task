package com.example.bankcards.service.impl;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.Role.RoleName;
import com.example.bankcards.repository.RoleRepository;
import com.example.bankcards.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
} 