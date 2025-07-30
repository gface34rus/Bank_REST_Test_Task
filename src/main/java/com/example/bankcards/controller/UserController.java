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
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST контроллер для управления пользователями.
 * Предоставляет API для создания, чтения, обновления и удаления пользователей.
 * Доступ только для администраторов.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Tag(name = "Пользователи", description = "Управление пользователями (только для админов)")
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

    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по его идентификатору.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Пользователь найден"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public UserDto getUserById(@Parameter(description = "ID пользователя", required = true) @PathVariable Long id) {
        return userService.findById(id).orElse(null);
    }

    @Operation(summary = "Получить список пользователей", description = "Возвращает страницу пользователей с пагинацией.")
    @ApiResponse(responseCode = "200", description = "Список пользователей получен")
    @GetMapping
    public Page<UserDto> listUsers(
            @Parameter(description = "Номер страницы", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Размер страницы", example = "10") @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.listUsers(pageable);
    }

    @Operation(summary = "Создать пользователя", description = "Создает нового пользователя.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Пользователь создан"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя")
    })
    @PostMapping
    public UserDto createUser(@Parameter(description = "Данные пользователя для создания", required = true) @RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Обновить пользователя", description = "Обновляет существующего пользователя по ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Пользователь обновлен"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя")
    })
    @PutMapping("/{id}")
    public UserDto updateUser(
            @Parameter(description = "ID пользователя", required = true) @PathVariable Long id,
            @Parameter(description = "Новые данные пользователя", required = true) @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по ID.")
    @ApiResponse(responseCode = "204", description = "Пользователь удален")
    @DeleteMapping("/{id}")
    public void deleteUser(@Parameter(description = "ID пользователя", required = true) @PathVariable Long id) {
        userService.deleteUser(id);
    }
} 