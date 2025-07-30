package com.example.bankcards.controller;

import com.example.bankcards.dto.JwtAuthenticationRequest;
import com.example.bankcards.dto.JwtAuthenticationResponse;
import com.example.bankcards.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * REST контроллер для аутентификации пользователей.
 * Предоставляет API для входа в систему и получения JWT токена.
 * Доступ для всех пользователей (не требует аутентификации).
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    /**
     * Конструктор с внедрением зависимостей.
     * 
     * @param authenticationManager менеджер аутентификации Spring Security
     * @param tokenProvider провайдер JWT токенов
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Аутентифицирует пользователя и возвращает JWT токен.
     * 
     * @param loginRequest данные для входа (имя пользователя и пароль)
     * @return JWT токен для доступа к защищенным ресурсам
     * @throws AuthenticationException если учетные данные некорректны
     */
    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(@RequestBody JwtAuthenticationRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }
} 