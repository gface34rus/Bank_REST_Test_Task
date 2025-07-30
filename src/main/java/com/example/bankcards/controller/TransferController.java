package com.example.bankcards.controller;

import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

/**
 * REST контроллер для управления переводами между картами.
 * Предоставляет API для перевода средств между картами одного пользователя.
 * Доступ для пользователей с ролями USER и ADMIN.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
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
    @PostMapping
    public void transfer(@RequestParam Long fromCardId,
                         @RequestParam Long toCardId,
                         @RequestParam BigDecimal amount,
                         Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        cardService.transferBetweenCards(fromCardId, toCardId, amount, user);
    }
} 