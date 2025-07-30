package com.example.bankcards.dto;

/**
 * DTO для ответа аутентификации пользователя.
 * Содержит JWT токен доступа для авторизованного пользователя.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class JwtAuthenticationResponse {
    /** JWT токен доступа */
    private String accessToken;

    /**
     * Конструктор с JWT токеном.
     * 
     * @param accessToken JWT токен доступа
     */
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Получает JWT токен доступа.
     * 
     * @return JWT токен доступа
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Устанавливает JWT токен доступа.
     * 
     * @param accessToken JWT токен доступа
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
} 