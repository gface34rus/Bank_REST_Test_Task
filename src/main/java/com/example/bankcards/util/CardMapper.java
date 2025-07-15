package com.example.bankcards.util;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;

public class CardMapper {
    public static CardDto toDto(Card card) {
        String decrypted = CardEncryptionUtil.decrypt(card.getCardNumber());
        String masked = CardMaskUtil.mask(decrypted);
        return new CardDto(
                card.getId(),
                masked,
                card.getExpiryDate(),
                card.getStatus().name(),
                card.getBalance()
        );
    }
} 