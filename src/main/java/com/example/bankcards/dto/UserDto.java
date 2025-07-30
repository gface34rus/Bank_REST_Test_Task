package com.example.bankcards.dto;

import java.util.List;

/**
 * DTO (Data Transfer Object) для передачи данных пользователя.
 * Используется для безопасной передачи информации о пользователе
 * без раскрытия чувствительных данных (пароль).
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class UserDto {
    /** Уникальный идентификатор пользователя */
    private Long id;
    
    /** Уникальное имя пользователя */
    private String username;
    
    /** Email адрес пользователя */
    private String email;
    
    /** Список ролей пользователя */
    private List<RoleDto> roles;

    /**
     * Конструктор по умолчанию.
     */
    public UserDto() {}

    /**
     * Конструктор с основными полями.
     * 
     * @param id уникальный идентификатор пользователя
     * @param username уникальное имя пользователя
     * @param email email адрес пользователя
     */
    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Получает уникальный идентификатор пользователя.
     * 
     * @return идентификатор пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор пользователя.
     * 
     * @param id идентификатор пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает уникальное имя пользователя.
     * 
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает уникальное имя пользователя.
     * 
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получает email адрес пользователя.
     * 
     * @return email адрес
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает email адрес пользователя.
     * 
     * @param email email адрес
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получает список ролей пользователя.
     * 
     * @return список ролей
     */
    public List<RoleDto> getRoles() {
        return roles;
    }

    /**
     * Устанавливает список ролей пользователя.
     * 
     * @param roles список ролей
     */
    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
} 