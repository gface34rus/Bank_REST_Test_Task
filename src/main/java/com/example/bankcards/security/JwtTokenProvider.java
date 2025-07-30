package com.example.bankcards.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Провайдер для работы с JWT токенами.
 * Предоставляет методы для генерации, валидации и извлечения данных из JWT токенов.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Component
public class JwtTokenProvider {
    /** Секретный ключ для подписи JWT токенов */
    @Value("${app.jwtSecret:SecretKey1234567890}")
    private String jwtSecret;

    /** Время жизни JWT токена в миллисекундах */
    @Value("${app.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    /**
     * Генерирует JWT токен для аутентифицированного пользователя.
     * 
     * @param authentication объект аутентификации Spring Security
     * @return JWT токен в виде строки
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Извлекает имя пользователя из JWT токена.
     * 
     * @param token JWT токен
     * @return имя пользователя из токена
     * @throws JwtException если токен некорректный
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Проверяет валидность JWT токена.
     * 
     * @param authToken JWT токен для проверки
     * @return true если токен валиден, false в противном случае
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
} 