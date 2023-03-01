package com.ms.mscard.mscard.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataIssueCardDto {

    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;
}
