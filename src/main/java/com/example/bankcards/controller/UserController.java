package com.example.bankcards.controller;

import com.example.bankcards.entity.User;
import com.example.bankcards.service.UserService;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST контроллер для управления пользователями.
 * Предоставляет API для создания, чтения, обновления и удаления пользователей.
 * Доступ только для администраторов.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    /**
     * Конструктор с внедрением зависимости UserService.
     * 
     * @param userService сервис для работы с пользователями
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получает пользователя по его идентификатору.
     * 
     * @param id идентификатор пользователя
     * @return Optional с данными пользователя или пустой Optional, если пользователь не найден
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id).orElse(null);
    }

    /**
     * Получает список пользователей с пагинацией.
     * 
     * @param page номер страницы (по умолчанию 0)
     * @param size размер страницы (по умолчанию 10)
     * @return страница с пользователями
     */
    @GetMapping
    public Page<UserDto> listUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.listUsers(pageable);
    }

    /**
     * Создает нового пользователя.
     * 
     * @param user данные пользователя для создания
     * @return созданный пользователь
     * @throws IllegalArgumentException если данные пользователя некорректны
     */
    @PostMapping
    public UserDto createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Обновляет существующего пользователя.
     * 
     * @param id идентификатор пользователя для обновления
     * @param user новые данные пользователя
     * @return обновленный пользователь
     * @throws IllegalArgumentException если данные пользователя некорректны
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /**
     * Удаляет пользователя по его идентификатору.
     * 
     * @param id идентификатор пользователя для удаления
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
} 