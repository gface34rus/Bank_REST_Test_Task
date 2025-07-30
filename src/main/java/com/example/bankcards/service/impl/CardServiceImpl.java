package com.example.bankcards.service.impl;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.CardService;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CreateCardRequest;
import com.example.bankcards.util.CardEncryptionUtil;
import com.example.bankcards.util.CardMapper;
import com.example.bankcards.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CardDto createCard(CreateCardRequest request, User owner) {
        String encryptedNumber = CardEncryptionUtil.encrypt(request.getCardNumber());
        Card card = new Card();
        card.setCardNumber(encryptedNumber);
        card.setOwner(owner);
        card.setExpiryDate(request.getExpiryDate());
        card.setStatus(Card.Status.ACTIVE);
        card.setBalance(request.getInitialBalance());
        Card saved = cardRepository.save(card);
        return CardMapper.toDto(saved);
    }

    @Override
    public Optional<CardDto> getCardById(Long id) {
        return cardRepository.findById(id).map(CardMapper::toDto);
    }

    @Override
    @Transactional
    public CardDto updateCard(Long id, Card card) {
        card.setId(id);
        return CardMapper.toDto(cardRepository.save(card));
    }

    @Override
    @Transactional
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CardDto blockCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setStatus(Card.Status.BLOCKED);
        return CardMapper.toDto(cardRepository.save(card));
    }

    @Override
    @Transactional
    public CardDto activateCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setStatus(Card.Status.ACTIVE);
        return CardMapper.toDto(cardRepository.save(card));
    }

    @Override
    public Page<CardDto> listCards(User owner, Pageable pageable) {
        Page<Card> cards = cardRepository.findAllByOwner(owner, pageable);
        return new PageImpl<>(cards.getContent().stream().map(CardMapper::toDto).toList(), pageable, cards.getTotalElements());
    }

    @Override
    @Transactional
    public void transferBetweenCards(Long fromCardId, Long toCardId, BigDecimal amount, User user) {
        Card fromCard = cardRepository.findById(fromCardId).orElseThrow();
        Card toCard = cardRepository.findById(toCardId).orElseThrow();
        if (!fromCard.getOwner().equals(user) || !toCard.getOwner().equals(user)) {
            throw new IllegalArgumentException("User does not own both cards");
        }
        if (fromCard.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        fromCard.setBalance(fromCard.getBalance().subtract(amount));
        toCard.setBalance(toCard.getBalance().add(amount));
        cardRepository.save(fromCard);
        cardRepository.save(toCard);
    }

    @Override
    public BigDecimal getBalance(Long cardId, User user) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        if (!card.getOwner().equals(user)) {
            throw new IllegalArgumentException("User does not own this card");
        }
        return card.getBalance();
    }
} 