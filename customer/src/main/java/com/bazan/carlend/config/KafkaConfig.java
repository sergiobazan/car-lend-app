package com.bazan.carlend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    public final static String TOPIC = "customer-topic";

    @Bean
    public NewTopic customerTopic() {
        return TopicBuilder
                .name(TOPIC)
                .build();
    }
}
