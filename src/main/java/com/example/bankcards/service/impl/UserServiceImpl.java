package com.example.bankcards.service.impl;

import com.example.bankcards.entity.User;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.repository.RoleRepository;
import com.example.bankcards.service.UserService;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Реализация сервиса для работы с пользователями.
 * Предоставляет методы для создания, чтения, обновления и удаления пользователей
 * с использованием транзакций для операций изменения данных.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Конструктор с внедрением зависимостей.
     * 
     * @param userRepository репозиторий для работы с пользователями
     * @param roleRepository репозиторий для работы с ролями
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserDto createUser(User user) {
        return UserMapper.toDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserDto updateUser(Long id, User user) {
        user.setId(id);
        return UserMapper.toDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<UserDto> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::toDto);
    }
} 