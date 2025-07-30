package com.example.bankcards.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO для создания новой банковской карты.
 * Содержит данные, необходимые для создания карты с валидацией полей.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class CreateCardRequest {
    /** Номер карты (16 цифр) */
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    /** Дата истечения срока действия карты */
    @NotNull
    private LocalDate expiryDate;

    /** Начальный баланс карты (не менее 0.00) */
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal initialBalance;

    /**
     * Конструктор по умолчанию.
     */
    public CreateCardRequest() {}

    /**
     * Конструктор со всеми полями.
     * 
     * @param cardNumber номер карты (16 цифр)
     * @param expiryDate дата истечения срока действия карты
     * @param initialBalance начальный баланс карты
     */
    public CreateCardRequest(String cardNumber, LocalDate expiryDate, BigDecimal initialBalance) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.initialBalance = initialBalance;
    }

    /**
     * Получает номер карты.
     * 
     * @return номер карты (16 цифр)
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Устанавливает номер карты.
     * 
     * @param cardNumber номер карты (16 цифр)
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
     * Получает начальный баланс карты.
     * 
     * @return начальный баланс карты
     */
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * Устанавливает начальный баланс карты.
     * 
     * @param initialBalance начальный баланс карты
     */
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
} 