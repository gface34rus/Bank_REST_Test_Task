package com.example.bankcards.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardDto {
    private Long id;
    private String maskedCardNumber;
    private String ownerUsername;
    private LocalDate expiryDate;
    private String status;
    private BigDecimal balance;

    public CardDto() {}

    public CardDto(Long id, String maskedCardNumber, String ownerUsername, LocalDate expiryDate, String status, BigDecimal balance) {
        this.id = id;
        this.maskedCardNumber = maskedCardNumber;
        this.ownerUsername = ownerUsername;
        this.expiryDate = expiryDate;
        this.status = status;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
} 