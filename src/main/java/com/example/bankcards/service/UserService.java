package com.example.bankcards.service;

import com.example.bankcards.entity.User;
import com.example.bankcards.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByUsername(String username);
    UserDto createUser(User user);
    UserDto updateUser(Long id, User user);
    void deleteUser(Long id);
    Page<com.example.bankcards.dto.UserDto> listUsers(Pageable pageable);
} 