package com.ms.mscard.mscard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.mscard.mscard.dto.DataIssueCardDto;
import com.ms.mscard.mscard.entities.Card;
import com.ms.mscard.mscard.entities.ClientCard;
import com.ms.mscard.mscard.repository.CardRepository;
import com.ms.mscard.mscard.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IssueCardSubscriber {

    private final CardRepository cardRepository;

    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queue.issue-card}")
    public void receiveIssuingRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            DataIssueCardDto dataIssueCardDto = mapper.readValue(payload, DataIssueCardDto.class);
            Card card = cardRepository.findById(dataIssueCardDto.getIdCard()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(dataIssueCardDto.getCpf());
            clientCard.setBasicLimit(dataIssueCardDto.getApprovedLimit());

            clientCardRepository.save(clientCard);

        } catch (Exception e ) {
            log.error("Error in obtaining letter issuing request: {}",e.getMessage());
        }
    }
}
