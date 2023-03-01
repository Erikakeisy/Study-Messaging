package com.ms.mscreditappraiser.mscreditappraiser.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.mscreditappraiser.mscreditappraiser.dto.DataIssueCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherIssueCard {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void requestCard(DataIssueCardDto data) throws JsonProcessingException{
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String convertIntoJson(DataIssueCardDto data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        return json;
    }

}
