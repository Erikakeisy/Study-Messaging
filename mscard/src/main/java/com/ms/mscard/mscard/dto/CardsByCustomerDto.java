package com.ms.mscard.mscard.dto;

import com.ms.mscard.mscard.entities.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByCustomerDto {

    private String name;
    private String flag;
    private BigDecimal basicLimit;

    public static CardsByCustomerDto fromModel(ClientCard model){
        return new CardsByCustomerDto(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getBasicLimit()
        );
    }
}
