package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Сервис для работы с банковскими картами.
 * Предоставляет методы для создания, чтения, обновления карт,
 * управления их статусом и перевода средств между картами.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public interface CardService {
    /**
     * Создает новую банковскую карту.
     * 
     * @param request данные для создания карты
     * @param owner владелец карты
     * @return созданная карта
     * @throws IllegalArgumentException если данные карты некорректны
     */
    CardDto createCard(CreateCardRequest request, User owner);
    
    /**
     * Находит карту по идентификатору.
     * 
     * @param id идентификатор карты
     * @return Optional с данными карты или пустой Optional, если карта не найдена
     */
    Optional<CardDto> getCardById(Long id);
    
    /**
     * Обновляет существующую карту.
     * 
     * @param id идентификатор карты для обновления
     * @param card новые данные карты
     * @return обновленная карта
     * @throws IllegalArgumentException если данные карты некорректны
     */
    CardDto updateCard(Long id, Card card);
    
    /**
     * Удаляет карту по идентификатору.
     * 
     * @param id идентификатор карты для удаления
     */
    void deleteCard(Long id);
    
    /**
     * Блокирует карту.
     * 
     * @param id идентификатор карты для блокировки
     * @return заблокированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    CardDto blockCard(Long id);
    
    /**
     * Активирует заблокированную карту.
     * 
     * @param id идентификатор карты для активации
     * @return активированная карта
     * @throws IllegalArgumentException если карта не найдена
     */
    CardDto activateCard(Long id);
    
    /**
     * Получает список карт пользователя с пагинацией.
     * 
     * @param owner владелец карт
     * @param pageable параметры пагинации
     * @return страница с картами пользователя
     */
    Page<CardDto> listCards(User owner, Pageable pageable);
    
    /**
     * Переводит средства между картами одного пользователя.
     * 
     * @param fromCardId идентификатор карты-отправителя
     * @param toCardId идентификатор карты-получателя
     * @param amount сумма перевода
     * @param user пользователь, владеющий обеими картами
     * @throws IllegalArgumentException если пользователь не владеет обеими картами
     * @throws InsufficientFundsException если недостаточно средств на карте-отправителе
     */
    void transferBetweenCards(Long fromCardId, Long toCardId, BigDecimal amount, User user);
    
    /**
     * Получает баланс карты.
     * 
     * @param cardId идентификатор карты
     * @param user пользователь, запрашивающий баланс
     * @return баланс карты
     * @throws IllegalArgumentException если пользователь не владеет картой
     */
    BigDecimal getBalance(Long cardId, User user);
} 