package com.example.bankcards.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCardRequest {
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal initialBalance;

    public CreateCardRequest() {}

    public CreateCardRequest(String cardNumber, LocalDate expiryDate, BigDecimal initialBalance) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.initialBalance = initialBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
} 