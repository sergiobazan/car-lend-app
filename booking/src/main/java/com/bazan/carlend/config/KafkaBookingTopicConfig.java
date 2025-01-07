package com.bazan.carlend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBookingTopicConfig {
    public static final String BOOKING_TOPIC = "booking_topic";

    @Bean
    public NewTopic bookingTopic() {
        return TopicBuilder
                .name(BOOKING_TOPIC)
                .build();
    }
}
