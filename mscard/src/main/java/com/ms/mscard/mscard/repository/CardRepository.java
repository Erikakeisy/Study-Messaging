package com.ms.mscard.mscard.repository;

import com.ms.mscard.mscard.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByIncomeLessThanEqual(BigDecimal income); // menor ou igual
}
