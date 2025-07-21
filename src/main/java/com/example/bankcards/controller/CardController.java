package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import com.example.bankcards.util.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public Optional<CardDto> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping
    public Page<CardDto> listCards(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Principal principal) {
        // Здесь предполагается, что principal.getName() = username
        User user = new User();
        user.setUsername(principal.getName());
        Pageable pageable = PageRequest.of(page, size);
        return cardService.listCards(user, pageable);
    }

    @PostMapping
    public CardDto createCard(@RequestBody CreateCardRequest request, Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return cardService.createCard(request, user);
    }

    @PutMapping("/{id}")
    public CardDto updateCard(@PathVariable Long id, @RequestBody Card card) {
        return CardMapper.toDto(cardService.updateCard(id, card));
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }


    @PostMapping("/{id}/block")
    public CardDto blockCard(@PathVariable Long id) {
        return CardMapper.toDto(cardService.blockCard(id));
    }


    @PostMapping("/{id}/activate")
    public CardDto activateCard(@PathVariable Long id) {
        return CardMapper.toDto(cardService.activateCard(id));
    }


    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id, Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        return cardService.getBalance(id, user);
    }
} 