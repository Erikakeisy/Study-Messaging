package com.ms.mscreditappraiser.mscreditappraiser.service;

import com.ms.mscreditappraiser.mscreditappraiser.clients.CardResourceClient;
import com.ms.mscreditappraiser.mscreditappraiser.clients.CustomerResourceClient;
import com.ms.mscreditappraiser.mscreditappraiser.config.PublisherIssueCard;
import com.ms.mscreditappraiser.mscreditappraiser.dto.*;
import com.ms.mscreditappraiser.mscreditappraiser.exception.ComunicationErrorEx;
import com.ms.mscreditappraiser.mscreditappraiser.exception.CustomerNotFoundEx;
import com.ms.mscreditappraiser.mscreditappraiser.exception.IssueCardErrorEx;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerResourceClient;

    private final CardResourceClient cardResourceClient;

    private final PublisherIssueCard publisherIssueCard;


    public CustomerSituationDto getSituationCustomer(String cpf) throws CustomerNotFoundEx, ComunicationErrorEx {
        // obter dados do cliente do msclient
        // obter cartões do cliente do mscard
        try {
            ResponseEntity<CustomerDataDto> dataCustomerResponse = customerResourceClient.dataCustumer(cpf);
            ResponseEntity<List<CardCustomerDto>> dataCardResponse = cardResourceClient.getCartoesByClient(cpf);

            return CustomerSituationDto
                    .builder()
                    .client(dataCustomerResponse.getBody())
                    .cards(dataCardResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerNotFoundEx();
            }
            throw new ComunicationErrorEx(ex.getMessage(), status);
        }
    }

    public ResponseEvaluationCustomerDto carryOutEvaluation(String cpf, Long income) throws CustomerNotFoundEx, ComunicationErrorEx {
        try {
            ResponseEntity<CustomerDataDto> dataCustomerResponse = customerResourceClient.dataCustumer(cpf);
            ResponseEntity<List<CardDto>> cardIncomeAteh = cardResourceClient.getCardIncomeAteh(income);

            List<CardDto> body = cardIncomeAteh.getBody();

            var listCardsApproved= body.stream().map(cardDto -> {

                CustomerDataDto dataClient = dataCustomerResponse.getBody();

                BigDecimal limitBasic = cardDto.getBasicLimit();
                BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal ageBD = BigDecimal.valueOf(dataClient.getAge());
                final BigDecimal fator = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = fator.multiply(limitBasic);

                CardApprovedDto approved = new CardApprovedDto();
                approved.setName(cardDto.getName());
                approved.setFlag(cardDto.getFlag());
                approved.setBasicLimit(limitApproved);
                return approved;
            }).collect(Collectors.toList());

            return new ResponseEvaluationCustomerDto(listCardsApproved);

        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerNotFoundEx();
            }
            throw new ComunicationErrorEx(ex.getMessage(), status);
        }
    }

    public ProtocolCardIssueDto issueApprovedCard(DataIssueCardDto data){
        try{
            publisherIssueCard.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolCardIssueDto(protocol);

        }catch (Exception e){
            throw new IssueCardErrorEx(e.getMessage());
        }
    }
}
