package com.example.bankcards.dto;

/**
 * DTO (Data Transfer Object) для передачи данных роли пользователя.
 * Используется для передачи информации о ролях пользователей.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class RoleDto {
    /** Уникальный идентификатор роли */
    private Long id;
    
    /** Название роли (ADMIN, USER) */
    private String name;

    /**
     * Конструктор по умолчанию.
     */
    public RoleDto() {}

    /**
     * Конструктор с основными полями.
     * 
     * @param id уникальный идентификатор роли
     * @param name название роли
     */
    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Получает уникальный идентификатор роли.
     * 
     * @return идентификатор роли
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор роли.
     * 
     * @param id идентификатор роли
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получает название роли.
     * 
     * @return название роли (ADMIN, USER)
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название роли.
     * 
     * @param name название роли
     */
    public void setName(String name) {
        this.name = name;
    }
} 