package com.ms.mscreditappraiser.mscreditappraiser.controller;


import com.ms.mscreditappraiser.mscreditappraiser.dto.*;
import com.ms.mscreditappraiser.mscreditappraiser.exception.ComunicationErrorEx;
import com.ms.mscreditappraiser.mscreditappraiser.exception.CustomerNotFoundEx;
import com.ms.mscreditappraiser.mscreditappraiser.exception.IssueCardErrorEx;
import com.ms.mscreditappraiser.mscreditappraiser.service.CreditAppraiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "/situation", params = "cpf")
    public ResponseEntity getCustomerSituation (@RequestParam("cpf") String cpf){
        try {
            CustomerSituationDto customerSituation = creditAppraiserService.getSituationCustomer(cpf);
            return ResponseEntity.ok(customerSituation);
        }catch (CustomerNotFoundEx ex){
            return ResponseEntity.notFound().build();
        }catch (ComunicationErrorEx ex){
            return ResponseEntity.status(HttpStatus.resolve(ex.getStatus())).body(ex.getMessage());
        }
    }

    @PostMapping
    ResponseEntity carryOutEvaluation(@RequestBody DataEvaluationDto dataEvaluation){
        try {
            final ResponseEvaluationCustomerDto responseEvaluationCustomerDto = creditAppraiserService
                    .carryOutEvaluation(dataEvaluation.getCpf(), dataEvaluation.getIncome());
            return ResponseEntity.ok(responseEvaluationCustomerDto);
        }catch (CustomerNotFoundEx ex){
            return ResponseEntity.notFound().build();
        }catch (ComunicationErrorEx ex){
            return ResponseEntity.status(HttpStatus.resolve(ex.getStatus())).body(ex.getMessage());
        }
    }

    @PostMapping("request")
    public ResponseEntity issueCard(@RequestBody DataIssueCardDto data){
        try{
            ProtocolCardIssueDto protocol = creditAppraiserService.issueApprovedCard(data);
            return ResponseEntity.ok(protocol);
        }catch (IssueCardErrorEx e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
