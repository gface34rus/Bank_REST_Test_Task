package com.example.bankcards.exception;

/**
 * Исключение, выбрасываемое при попытке перевода средств,
 * когда на карте недостаточно средств для выполнения операции.
 * 
 * @author Bank REST Team
 * @version 1.0
 */
public class InsufficientFundsException extends RuntimeException {
    /**
     * Конструктор с сообщением об ошибке.
     * 
     * @param message сообщение об ошибке
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
} 