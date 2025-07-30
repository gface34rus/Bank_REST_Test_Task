package com.example.bankcards.dto;

/**
 * DTO для запроса аутентификации пользователя.
 * Содержит учетные данные пользователя для получения JWT токена.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class JwtAuthenticationRequest {
    /** Имя пользователя */
    private String username;
    
    /** Пароль пользователя */
    private String password;

    /**
     * Получает имя пользователя.
     * 
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя.
     * 
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получает пароль пользователя.
     * 
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     * 
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }
} 