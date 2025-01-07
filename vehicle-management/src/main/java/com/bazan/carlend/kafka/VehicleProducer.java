package com.bazan.carlend.kafka;

import com.bazan.carlend.config.KafkaVehicleTopicConfig;
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
public class VehicleProducer {

    private final KafkaTemplate<String, VehicleCreated> kafkaTemplate;

    public void send(VehicleCreated vehicleCreated) {
        log.info("Sending vehicle created to Kafka");

        Message<VehicleCreated> message = MessageBuilder
                .withPayload(vehicleCreated)
                .setHeader(KafkaHeaders.TOPIC, KafkaVehicleTopicConfig.TOPIC)
                .build();

        kafkaTemplate.send(message);

        log.info("Vehicle sent to Kafka");
    }
}
