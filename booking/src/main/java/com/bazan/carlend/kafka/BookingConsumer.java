package com.bazan.carlend.kafka;

import com.bazan.carlend.client.Client;
import com.bazan.carlend.client.IClientRepository;
import com.bazan.carlend.kafka.customer.CustomerCreated;
import com.bazan.carlend.kafka.vehicle.VehicleCreated;
import com.bazan.carlend.vehicle.IVehicleRepository;
import com.bazan.carlend.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingConsumer {

    public final static String CUSTOMER_TOPIC = "customer-topic";
    public final static String VEHICLE_TOPIC = "vehicle-topic";

    private final IClientRepository clientRepository;
    private final IVehicleRepository vehicleRepository;

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

    @KafkaListener(topics = VEHICLE_TOPIC)
    public void consumeVehicleCreated(VehicleCreated vehicleCreated) {
        log.info("Start vehicle created consumer");

        var vehicle = Vehicle.builder()
                .id(vehicleCreated.id())
                .model(vehicleCreated.model())
                .status(vehicleCreated.status())
                .category(vehicleCreated.category())
                .pricePerDay(vehicleCreated.pricePerDay())
                .build();

        vehicleRepository.save(vehicle);

        log.info("End vehicle created consumer");
    }
}
