package com.example.bankcards.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) для передачи данных банковской карты.
 * Используется для безопасной передачи информации о карте
 * с замаскированным номером карты.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class CardDto {
    /** Уникальный идентификатор карты */
    private Long id;
    
    /** Замаскированный номер карты (например, **** **** **** 1234) */
    private String maskedCardNumber;
    
    /** Имя пользователя владельца карты */
    private String ownerUsername;
    
    /** Дата истечения срока действия карты */
    private LocalDate expiryDate;
    
    /** Статус карты (ACTIVE, BLOCKED, EXPIRED) */
    private String status;
    
    /** Текущий баланс карты */
    private BigDecimal balance;

    /**
     * Конструктор по умолчанию.
     */
    public CardDto() {}

    /**
     * Конструктор со всеми полями.
     * 
     * @param id уникальный идентификатор карты
     * @param maskedCardNumber замаскированный номер карты
     * @param ownerUsername имя пользователя владельца карты
     * @param expiryDate дата истечения срока действия карты
     * @param status статус карты
     * @param balance текущий баланс карты
     */
    public CardDto(Long id, String maskedCardNumber, String ownerUsername, LocalDate expiryDate, String status, BigDecimal balance) {
        this.id = id;
        this.maskedCardNumber = maskedCardNumber;
        this.ownerUsername = ownerUsername;
        this.expiryDate = expiryDate;
        this.status = status;
        this.balance = balance;
    }

    /**
     * Получает уникальный идентификатор карты.
     * 
     * @return идентификатор карты
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор карты.
     * 
     * @param id идентификатор карты
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает замаскированный номер карты.
     * 
     * @return замаскированный номер карты
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Устанавливает замаскированный номер карты.
     * 
     * @param maskedCardNumber замаскированный номер карты
     */
    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    /**
     * Получает имя пользователя владельца карты.
     * 
     * @return имя пользователя владельца
     */
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /**
     * Устанавливает имя пользователя владельца карты.
     * 
     * @param ownerUsername имя пользователя владельца
     */
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    /**
     * Получает дату истечения срока действия карты.
     * 
     * @return дата истечения срока действия
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Устанавливает дату истечения срока действия карты.
     * 
     * @param expiryDate дата истечения срока действия
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Получает статус карты.
     * 
     * @return статус карты (ACTIVE, BLOCKED, EXPIRED)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Устанавливает статус карты.
     * 
     * @param status статус карты
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Получает текущий баланс карты.
     * 
     * @return текущий баланс карты
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Устанавливает текущий баланс карты.
     * 
     * @param balance текущий баланс карты
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
} 