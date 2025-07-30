package com.example.bankcards.entity;

import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity класс для представления пользователя в системе.
 * Содержит основную информацию о пользователе, его роли и карты.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User {
    /** Уникальный идентификатор пользователя */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Уникальное имя пользователя */
    @Column(nullable = false, unique = true)
    private String username;

    /** Хешированный пароль пользователя */
    @Column(nullable = false)
    private String password;

    /** Уникальный email адрес пользователя */
    @Column(nullable = false, unique = true)
    private String email;

    /** Роли пользователя в системе */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /** Карты пользователя (исключены из JSON сериализации) */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Card> cards = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     */
    public User() {}

    /**
     * Получает уникальный идентификатор пользователя.
     * 
     * @return идентификатор пользователя
     */
    public Long getId() { return id; }
    
    /**
     * Устанавливает уникальный идентификатор пользователя.
     * 
     * @param id идентификатор пользователя
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Получает уникальное имя пользователя.
     * 
     * @return имя пользователя
     */
    public String getUsername() { return username; }
    
    /**
     * Устанавливает уникальное имя пользователя.
     * 
     * @param username имя пользователя
     */
    public void setUsername(String username) { this.username = username; }
    
    /**
     * Получает хешированный пароль пользователя.
     * 
     * @return хешированный пароль
     */
    public String getPassword() { return password; }
    
    /**
     * Устанавливает хешированный пароль пользователя.
     * 
     * @param password хешированный пароль
     */
    public void setPassword(String password) { this.password = password; }
    
    /**
     * Получает уникальный email адрес пользователя.
     * 
     * @return email адрес
     */
    public String getEmail() { return email; }
    
    /**
     * Устанавливает уникальный email адрес пользователя.
     * 
     * @param email email адрес
     */
    public void setEmail(String email) { this.email = email; }
    
    /**
     * Получает роли пользователя в системе.
     * 
     * @return множество ролей пользователя
     */
    public Set<Role> getRoles() { return roles; }
    
    /**
     * Устанавливает роли пользователя в системе.
     * 
     * @param roles множество ролей пользователя
     */
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    
    /**
     * Получает карты пользователя.
     * 
     * @return список карт пользователя
     */
    public List<Card> getCards() { return cards; }
    
    /**
     * Устанавливает карты пользователя.
     * 
     * @param cards список карт пользователя
     */
    public void setCards(List<Card> cards) { this.cards = cards; }

    // Геттеры и сеттеры
    // equals, hashCode, toString
    // ... existing code ...
} 