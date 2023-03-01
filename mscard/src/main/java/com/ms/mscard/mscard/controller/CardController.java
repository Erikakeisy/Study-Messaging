package com.ms.mscard.mscard.controller;

import com.ms.mscard.mscard.dto.CardDto;
import com.ms.mscard.mscard.dto.CardsByCustomerDto;
import com.ms.mscard.mscard.entities.Card;
import com.ms.mscard.mscard.entities.ClientCard;
import com.ms.mscard.mscard.service.CardService;
import com.ms.mscard.mscard.service.ClientCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastreCard(@RequestBody CardDto dto){
        var card = dto.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardIncomeAteh(@RequestParam("income") Long income){
        List<Card> list = cardService.getCardEqualLowerIncome(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByCustomerDto>> getCartoesByClient(
            @RequestParam("cpf") String cpf){
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<CardsByCustomerDto> resultList = list.stream()
                .map(CardsByCustomerDto::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
