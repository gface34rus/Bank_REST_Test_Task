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
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST контроллер для аутентификации пользователей.
 * Предоставляет API для входа в систему и получения JWT токена.
 * Доступ для всех пользователей (не требует аутентификации).
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Tag(name = "Аутентификация", description = "Вход в систему и получение JWT токена")
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
    @Operation(summary = "Вход в систему", description = "Аутентифицирует пользователя и возвращает JWT токен.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Аутентификация успешна, возвращён JWT токен"),
        @ApiResponse(responseCode = "401", description = "Неверные учетные данные")
    })
    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(
            @Parameter(description = "Данные для входа (имя пользователя и пароль)", required = true) @RequestBody JwtAuthenticationRequest loginRequest) {
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