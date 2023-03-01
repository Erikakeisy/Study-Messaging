package com.ms.mscreditappraiser.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queue.issue-card}")
    private String issueCardQueue;

    @Bean
    public Queue queueIssueCard(){
        return new Queue(issueCardQueue,true);
    }
}
