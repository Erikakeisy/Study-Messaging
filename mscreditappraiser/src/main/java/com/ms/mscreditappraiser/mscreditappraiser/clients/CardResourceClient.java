package com.ms.mscreditappraiser.mscreditappraiser.clients;

import com.ms.mscreditappraiser.mscreditappraiser.dto.CardCustomerDto;
import com.ms.mscreditappraiser.mscreditappraiser.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscard", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardCustomerDto>> getCartoesByClient(
            @RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<CardDto>> getCardIncomeAteh(@RequestParam("income") Long income);
}
