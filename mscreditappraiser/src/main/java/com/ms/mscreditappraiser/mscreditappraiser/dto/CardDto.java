package com.ms.mscreditappraiser.mscreditappraiser.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {

    private Long id;
    private String name;
    private String flag;
    private BigDecimal basicLimit;
}
