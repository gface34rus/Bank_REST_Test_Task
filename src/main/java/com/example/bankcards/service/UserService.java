package com.example.bankcards.service;

import com.example.bankcards.entity.User;
import com.example.bankcards.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * Сервис для работы с пользователями.
 * Предоставляет методы для создания, чтения, обновления и удаления пользователей.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public interface UserService {
    /**
     * Находит пользователя по идентификатору.
     * 
     * @param id идентификатор пользователя
     * @return Optional с данными пользователя или пустой Optional, если пользователь не найден
     */
    Optional<UserDto> findById(Long id);
    
    /**
     * Находит пользователя по имени пользователя.
     * 
     * @param username имя пользователя
     * @return Optional с данными пользователя или пустой Optional, если пользователь не найден
     */
    Optional<UserDto> findByUsername(String username);
    
    /**
     * Создает нового пользователя.
     * 
     * @param user данные пользователя для создания
     * @return созданный пользователь
     * @throws IllegalArgumentException если данные пользователя некорректны
     */
    UserDto createUser(User user);
    
    /**
     * Обновляет существующего пользователя.
     * 
     * @param id идентификатор пользователя для обновления
     * @param user новые данные пользователя
     * @return обновленный пользователь
     * @throws IllegalArgumentException если данные пользователя некорректны
     */
    UserDto updateUser(Long id, User user);
    
    /**
     * Удаляет пользователя по идентификатору.
     * 
     * @param id идентификатор пользователя для удаления
     */
    void deleteUser(Long id);
    
    /**
     * Получает список пользователей с пагинацией.
     * 
     * @param pageable параметры пагинации
     * @return страница с пользователями
     */
    Page<com.example.bankcards.dto.UserDto> listUsers(Pageable pageable);
} 