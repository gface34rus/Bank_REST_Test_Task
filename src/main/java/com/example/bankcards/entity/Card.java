package com.example.bankcards.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity класс для представления банковской карты в системе.
 * Содержит информацию о карте, её владельце, статусе и балансе.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Entity
@Table(name = "cards")
public class Card {
    /** Уникальный идентификатор карты */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Зашифрованный номер карты */
    @Column(nullable = false, unique = true)
    private String cardNumber;

    /** Владелец карты (исключен из JSON сериализации) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    /** Дата истечения срока действия карты */
    @Column(nullable = false)
    private LocalDate expiryDate;

    /** Статус карты (ACTIVE, BLOCKED, EXPIRED) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /** Текущий баланс карты */
    @Column(nullable = false)
    private BigDecimal balance;

    /**
     * Перечисление возможных статусов карты.
     */
    public enum Status {
        /** Активная карта */
        ACTIVE,
        /** Заблокированная карта */
        BLOCKED,
        /** Карта с истекшим сроком действия */
        EXPIRED
    }

    /**
     * Конструктор по умолчанию.
     */
    public Card() {}

    /**
     * Получает уникальный идентификатор карты.
     * 
     * @return идентификатор карты
     */
    public Long getId() { return id; }
    
    /**
     * Устанавливает уникальный идентификатор карты.
     * 
     * @param id идентификатор карты
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Получает зашифрованный номер карты.
     * 
     * @return зашифрованный номер карты
     */
    public String getCardNumber() { return cardNumber; }
    
    /**
     * Устанавливает зашифрованный номер карты.
     * 
     * @param cardNumber зашифрованный номер карты
     */
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    
    /**
     * Получает владельца карты.
     * 
     * @return владелец карты
     */
    public User getOwner() { return owner; }
    
    /**
     * Устанавливает владельца карты.
     * 
     * @param owner владелец карты
     */
    public void setOwner(User owner) { this.owner = owner; }
    
    /**
     * Получает дату истечения срока действия карты.
     * 
     * @return дата истечения срока действия
     */
    public LocalDate getExpiryDate() { return expiryDate; }
    
    /**
     * Устанавливает дату истечения срока действия карты.
     * 
     * @param expiryDate дата истечения срока действия
     */
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    /**
     * Получает статус карты.
     * 
     * @return статус карты
     */
    public Status getStatus() { return status; }
    
    /**
     * Устанавливает статус карты.
     * 
     * @param status статус карты
     */
    public void setStatus(Status status) { this.status = status; }
    
    /**
     * Получает текущий баланс карты.
     * 
     * @return текущий баланс карты
     */
    public BigDecimal getBalance() { return balance; }
    
    /**
     * Устанавливает текущий баланс карты.
     * 
     * @param balance текущий баланс карты
     */
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    // Геттеры и сеттеры
    // equals, hashCode, toString
    // ... existing code ...
} 