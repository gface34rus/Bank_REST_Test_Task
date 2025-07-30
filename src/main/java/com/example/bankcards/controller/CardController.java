package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST контроллер для управления банковскими картами.
 * Предоставляет API для создания, чтения, обновления карт,
 * управления их статусом и получения баланса.
 * Доступ для пользователей с ролями USER и ADMIN.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Tag(name = "Карты", description = "Управление банковскими картами (USER, ADMIN)")
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
    @Operation(summary = "Получить карту по ID", description = "Возвращает карту по её идентификатору.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Карта найдена"),
        @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @GetMapping("/{id}")
    public Optional<CardDto> getCardById(@Parameter(description = "ID карты", required = true) @PathVariable Long id) {
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
    @Operation(summary = "Получить список карт пользователя", description = "Возвращает страницу карт текущего пользователя с пагинацией.")
    @ApiResponse(responseCode = "200", description = "Список карт получен")
    @GetMapping
    public Page<CardDto> listCards(
            @Parameter(description = "Номер страницы", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Размер страницы", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(hidden = true) Principal principal) {
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
    @Operation(summary = "Создать карту", description = "Создаёт новую карту для текущего пользователя.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Карта создана"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные карты")
    })
    @PostMapping
    public CardDto createCard(
            @Parameter(description = "Данные для создания карты", required = true) @RequestBody CreateCardRequest request,
            @Parameter(hidden = true) Principal principal) {
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
    @Operation(summary = "Обновить карту", description = "Обновляет существующую карту по ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Карта обновлена"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные карты")
    })
    @PutMapping("/{id}")
    public CardDto updateCard(
            @Parameter(description = "ID карты", required = true) @PathVariable Long id,
            @Parameter(description = "Новые данные карты", required = true) @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }

    /**
     * Удаляет карту по её идентификатору.
     * 
     * @param id идентификатор карты для удаления
     */
    @Operation(summary = "Удалить карту", description = "Удаляет карту по ID.")
    @ApiResponse(responseCode = "204", description = "Карта удалена")
    @DeleteMapping("/{id}")
    public void deleteCard(@Parameter(description = "ID карты", required = true) @PathVariable Long id) {
        cardService.deleteCard(id);
    }

    /**
     * Блокирует карту.
     * 
     * @param id идентификатор карты для блокировки
     * @return заблокированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    @Operation(summary = "Блокировать карту", description = "Блокирует карту по ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Карта заблокирована"),
        @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @PostMapping("/{id}/block")
    public CardDto blockCard(@Parameter(description = "ID карты", required = true) @PathVariable Long id) {
        return cardService.blockCard(id);
    }

    /**
     * Активирует заблокированную карту.
     * 
     * @param id идентификатор карты для активации
     * @return активированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    @Operation(summary = "Активировать карту", description = "Активирует заблокированную карту по ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Карта активирована"),
        @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @PostMapping("/{id}/activate")
    public CardDto activateCard(@Parameter(description = "ID карты", required = true) @PathVariable Long id) {
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
    @Operation(summary = "Получить баланс карты", description = "Возвращает баланс карты по ID для текущего пользователя.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Баланс получен"),
        @ApiResponse(responseCode = "404", description = "Карта не найдена или не принадлежит пользователю")
    })
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(
            @Parameter(description = "ID карты", required = true) @PathVariable Long id,
            @Parameter(hidden = true) Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return cardService.getBalance(id, user);
    }
} 