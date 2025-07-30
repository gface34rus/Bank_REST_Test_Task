package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import com.example.bankcards.util.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

/**
 * REST контроллер для управления банковскими картами.
 * Предоставляет API для создания, чтения, обновления карт,
 * управления их статусом и получения баланса.
 * Доступ для пользователей с ролями USER и ADMIN.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    /**
     * Конструктор с внедрением зависимости CardService.
     * 
     * @param cardService сервис для работы с картами
     */
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Получает карту по её идентификатору.
     * 
     * @param id идентификатор карты
     * @return Optional с данными карты или пустой Optional, если карта не найдена
     */
    @GetMapping("/{id}")
    public Optional<CardDto> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    /**
     * Получает список карт текущего пользователя с пагинацией.
     * 
     * @param page номер страницы (по умолчанию 0)
     * @param size размер страницы (по умолчанию 10)
     * @param principal текущий аутентифицированный пользователь
     * @return страница с картами пользователя
     */
    @GetMapping
    public Page<CardDto> listCards(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Principal principal) {
        // Здесь предполагается, что principal.getName() = username
        User user = new User();
        user.setUsername(principal.getName());
        Pageable pageable = PageRequest.of(page, size);
        return cardService.listCards(user, pageable);
    }

    /**
     * Создает новую карту для текущего пользователя.
     * 
     * @param request данные для создания карты
     * @param principal текущий аутентифицированный пользователь
     * @return созданная карта
     * @throws IllegalArgumentException если данные карты некорректны
     */
    @PostMapping
    public CardDto createCard(@RequestBody CreateCardRequest request, Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return cardService.createCard(request, user);
    }

    /**
     * Обновляет существующую карту.
     * 
     * @param id идентификатор карты для обновления
     * @param card новые данные карты
     * @return обновленная карта
     * @throws IllegalArgumentException если данные карты некорректны
     */
    @PutMapping("/{id}")
    public CardDto updateCard(@PathVariable Long id, @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }

    /**
     * Удаляет карту по её идентификатору.
     * 
     * @param id идентификатор карты для удаления
     */
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    /**
     * Блокирует карту.
     * 
     * @param id идентификатор карты для блокировки
     * @return заблокированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    @PostMapping("/{id}/block")
    public CardDto blockCard(@PathVariable Long id) {
        return cardService.blockCard(id);
    }

    /**
     * Активирует заблокированную карту.
     * 
     * @param id идентификатор карты для активации
     * @return активированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    @PostMapping("/{id}/activate")
    public CardDto activateCard(@PathVariable Long id) {
        return cardService.activateCard(id);
    }

    /**
     * Получает баланс карты.
     * 
     * @param id идентификатор карты
     * @param principal текущий аутентифицированный пользователь
     * @return баланс карты
     * @throws IllegalArgumentException если пользователь не владеет картой
     */
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id, Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return cardService.getBalance(id, user);
    }
} 