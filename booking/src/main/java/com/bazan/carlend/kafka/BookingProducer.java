package com.bazan.carlend.kafka;

import com.bazan.carlend.config.KafkaBookingTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingProducer {

    private final KafkaTemplate<String, BookingConfirmation> kafkaTemplate;

    public void sendBooking(BookingConfirmation bookingConfirmation) {
        log.info("Booking confirmation: {}", bookingConfirmation);
        Message<BookingConfirmation> message = MessageBuilder
                .withPayload(bookingConfirmation)
                .setHeader(KafkaHeaders.TOPIC, KafkaBookingTopicConfig.BOOKING_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
