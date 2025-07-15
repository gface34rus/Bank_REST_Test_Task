package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Optional;

public interface CardService {
    CardDto createCard(CreateCardRequest request, User owner);
    Optional<CardDto> getCardById(Long id);
    Card updateCard(Long id, Card card);
    void deleteCard(Long id);
    Card blockCard(Long id);
    Card activateCard(Long id);
    Page<CardDto> listCards(User owner, Pageable pageable);
    void transferBetweenCards(Long fromCardId, Long toCardId, BigDecimal amount, User user);
    BigDecimal getBalance(Long cardId, User user);
} 