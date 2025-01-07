package com.bazan.carlend.kafka;

import com.bazan.carlend.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerProducer {

    private final KafkaTemplate<String, CustomerCreated> kafkaTemplate;

    public void sendCustomer(CustomerCreated customerCreated) {
        log.info("Send customer {} to kafka", customerCreated);

        Message<CustomerCreated> message = MessageBuilder
                .withPayload(customerCreated)
                .setHeader(KafkaHeaders.TOPIC, KafkaConfig.TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
