package com.example.bankcards.util;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;

public class CardMapper {
    public static CardDto toDto(Card card) {
        String decrypted = CardEncryptionUtil.decrypt(card.getCardNumber());
        String masked = CardMaskUtil.mask(decrypted);
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setMaskedCardNumber(masked);
        if (card.getOwner() != null) {
            dto.setOwnerUsername(card.getOwner().getUsername());
        }
        dto.setExpiryDate(card.getExpiryDate());
        dto.setStatus(card.getStatus().name());
        dto.setBalance(card.getBalance());
        return dto;
    }
} 