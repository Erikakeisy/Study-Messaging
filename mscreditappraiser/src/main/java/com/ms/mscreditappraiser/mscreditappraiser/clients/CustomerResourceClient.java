package com.ms.mscreditappraiser.mscreditappraiser.clients;

import com.ms.mscreditappraiser.mscreditappraiser.dto.CardDto;
import com.ms.mscreditappraiser.mscreditappraiser.dto.CustomerDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "msclient", path = "/clients")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerDataDto> dataCustumer(@RequestParam("cpf") String cpf);



}
