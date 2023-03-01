package com.ms.mscreditappraiser.mscreditappraiser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituationDto {

    private CustomerDataDto client;
    private List<CardCustomerDto> cards;
}
