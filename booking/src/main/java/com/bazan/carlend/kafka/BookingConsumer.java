package com.bazan.carlend.kafka;

import com.bazan.carlend.client.Client;
import com.bazan.carlend.client.IClientRepository;
import com.bazan.carlend.kafka.customer.CustomerCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingConsumer {

    public final static String CUSTOMER_TOPIC = "customer-topic";

    private final IClientRepository clientRepository;

    @KafkaListener(topics = CUSTOMER_TOPIC)
    public void consumeClientCreated(CustomerCreated customerCreated) {
        log.info("Start client created consumer");

        var client = Client.builder()
                .id(customerCreated.id())
                .fullName(customerCreated.firstName() + " " + customerCreated.lastName())
                .email(customerCreated.email())
                .build();

        clientRepository.save(client);

        log.info("End client created consumer");
    }
}
