package com.ms.mscard.mscard.dto;

import com.ms.mscard.mscard.entities.Card;
import com.ms.mscard.mscard.entities.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {

    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel(){
        return new Card(name,flag,income,basicLimit);
    }
}
