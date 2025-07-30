package com.example.bankcards.controller;

import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST контроллер для управления переводами между картами.
 * Предоставляет API для перевода средств между картами одного пользователя.
 * Доступ для пользователей с ролями USER и ADMIN.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Tag(name = "Переводы", description = "Переводы между картами (USER, ADMIN)")
@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final CardService cardService;

    /**
     * Конструктор с внедрением зависимости CardService.
     * 
     * @param cardService сервис для работы с картами
     */
    @Autowired
    public TransferController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Выполняет перевод средств между картами одного пользователя.
     * 
     * @param fromCardId идентификатор карты-отправителя
     * @param toCardId идентификатор карты-получателя
     * @param amount сумма перевода
     * @param principal текущий аутентифицированный пользователь
     * @throws IllegalArgumentException если пользователь не владеет обеими картами
     * @throws InsufficientFundsException если недостаточно средств на карте-отправителе
     */
    @Operation(summary = "Перевести средства между картами", description = "Выполняет перевод средств между картами одного пользователя.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Перевод выполнен успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка перевода: недостаточно средств или пользователь не владеет картами")
    })
    @PostMapping
    public void transfer(
            @Parameter(description = "ID карты-отправителя", required = true) @RequestParam Long fromCardId,
            @Parameter(description = "ID карты-получателя", required = true) @RequestParam Long toCardId,
            @Parameter(description = "Сумма перевода", required = true) @RequestParam BigDecimal amount,
            @Parameter(hidden = true) Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        cardService.transferBetweenCards(fromCardId, toCardId, amount, user);
    }
} 