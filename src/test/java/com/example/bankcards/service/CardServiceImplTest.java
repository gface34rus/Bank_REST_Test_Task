package com.example.bankcards.service;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.InsufficientFundsException;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.impl.CardServiceImpl;
import com.example.bankcards.util.CardEncryptionUtil;
import com.example.bankcards.util.CardMaskUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceImplTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CardServiceImpl cardService;

    private User user;
    private Card card;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        card = new Card();
        card.setId(1L);
        card.setCardNumber(CardEncryptionUtil.encrypt("1234567890123456"));
        card.setOwner(user);
        card.setExpiryDate(LocalDate.now().plusYears(2));
        card.setStatus(Card.Status.ACTIVE);
        card.setBalance(BigDecimal.valueOf(1000));
    }

    @Test
    void createCard_success() {
        CreateCardRequest req = new CreateCardRequest();
        req.setCardNumber("1234567890123456");
        req.setExpiryDate(LocalDate.now().plusYears(2));
        req.setInitialBalance(BigDecimal.valueOf(500));
        when(cardRepository.save(any(Card.class))).thenAnswer(inv -> inv.getArgument(0));
        CardDto dto = cardService.createCard(req, user);
        assertEquals("testuser", dto.getOwnerUsername());
        assertEquals("**** **** **** 3456", dto.getMaskedCardNumber());
        assertEquals(BigDecimal.valueOf(500), dto.getBalance());
    }

    @Test
    void getCardById_success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        Optional<CardDto> dtoOpt = cardService.getCardById(1L);
        assertTrue(dtoOpt.isPresent());
        assertEquals("testuser", dtoOpt.get().getOwnerUsername());
    }

    @Test
    void updateCard_success() {
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        card.setBalance(BigDecimal.valueOf(2000));
        CardDto dto = cardService.updateCard(1L, card);
        assertEquals(BigDecimal.valueOf(2000), dto.getBalance());
    }

    @Test
    void blockCard_success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        CardDto dto = cardService.blockCard(1L);
        assertEquals("BLOCKED", dto.getStatus());
    }

    @Test
    void activateCard_success() {
        card.setStatus(Card.Status.BLOCKED);
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        CardDto dto = cardService.activateCard(1L);
        assertEquals("ACTIVE", dto.getStatus());
    }

    @Test
    void listCards_success() {
        Pageable pageable = PageRequest.of(0, 10);
        when(cardRepository.findAllByOwner(user, pageable)).thenReturn(new PageImpl<>(Collections.singletonList(card)));
        Page<CardDto> page = cardService.listCards(user, pageable);
        assertEquals(1, page.getTotalElements());
        assertEquals("testuser", page.getContent().get(0).getOwnerUsername());
    }

    @Test
    void transferBetweenCards_success() {
        Card from = new Card();
        from.setId(1L);
        from.setOwner(user);
        from.setBalance(BigDecimal.valueOf(1000));
        Card to = new Card();
        to.setId(2L);
        to.setOwner(user);
        to.setBalance(BigDecimal.valueOf(100));
        when(cardRepository.findById(1L)).thenReturn(Optional.of(from));
        when(cardRepository.findById(2L)).thenReturn(Optional.of(to));
        cardService.transferBetweenCards(1L, 2L, BigDecimal.valueOf(200), user);
        assertEquals(BigDecimal.valueOf(800), from.getBalance());
        assertEquals(BigDecimal.valueOf(300), to.getBalance());
    }

    @Test
    void transferBetweenCards_insufficientFunds() {
        Card from = new Card();
        from.setId(1L);
        from.setOwner(user);
        from.setBalance(BigDecimal.valueOf(100));
        Card to = new Card();
        to.setId(2L);
        to.setOwner(user);
        to.setBalance(BigDecimal.valueOf(100));
        when(cardRepository.findById(1L)).thenReturn(Optional.of(from));
        when(cardRepository.findById(2L)).thenReturn(Optional.of(to));
        assertThrows(InsufficientFundsException.class, () ->
            cardService.transferBetweenCards(1L, 2L, BigDecimal.valueOf(200), user)
        );
    }

    @Test
    void getBalance_success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        BigDecimal balance = cardService.getBalance(1L, user);
        assertEquals(card.getBalance(), balance);
    }
} 