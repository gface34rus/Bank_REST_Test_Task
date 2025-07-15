package com.example.bankcards.controller;

import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final CardService cardService;

    @Autowired
    public TransferController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void transfer(@RequestParam Long fromCardId,
                         @RequestParam Long toCardId,
                         @RequestParam BigDecimal amount,
                         Principal principal) {
        User user = new User();
        user.setUsername(principal.getName());
        cardService.transferBetweenCards(fromCardId, toCardId, amount, user);
    }
} 