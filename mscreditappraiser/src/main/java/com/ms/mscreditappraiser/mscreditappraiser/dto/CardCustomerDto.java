package com.ms.mscreditappraiser.mscreditappraiser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCustomerDto {

    private String name;
    private String flag;
    private BigDecimal basicLimit;
}
