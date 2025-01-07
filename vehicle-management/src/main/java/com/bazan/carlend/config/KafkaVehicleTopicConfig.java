package com.bazan.carlend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaVehicleTopicConfig {
    public final static String TOPIC = "vehicle-topic";

    @Bean
    public NewTopic vehicleTopic() {
        return TopicBuilder
                .name(TOPIC)
                .build();
    }
}
