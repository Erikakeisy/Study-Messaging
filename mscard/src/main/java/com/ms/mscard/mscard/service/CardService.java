package com.ms.mscard.mscard.service;

import com.ms.mscard.mscard.entities.Card;
import com.ms.mscard.mscard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save (Card card){
        return cardRepository.save(card);
    }

    public List<Card> getCardEqualLowerIncome(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
