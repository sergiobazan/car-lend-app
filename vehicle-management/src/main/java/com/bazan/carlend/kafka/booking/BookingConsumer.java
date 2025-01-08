package com.bazan.carlend.kafka.booking;

import com.bazan.carlend.vehicle.IVehicleRepository;
import com.bazan.carlend.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class BookingConsumer {
    public static final String BOOKING_TOPIC = "booking_topic";
    public static final String BOOKING_GROUP = "bookingGroup";

    private final IVehicleRepository vehicleRepository;

    @Transactional
    @KafkaListener(topics = BOOKING_TOPIC, groupId = BOOKING_GROUP)
    public void consume(BookingConfirmation bookingConfirmation) {
        log.info("Starting Booking confirmation: {}", bookingConfirmation);

        Vehicle vehicle = vehicleRepository.findById(bookingConfirmation.vehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus(bookingConfirmation.status());

        vehicleRepository.save(vehicle);

        log.info("Finish Booking confirmation: {}", bookingConfirmation);

    }
}
