package com.bazan.carlend.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingResponse(
        int id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BookingStatus status,
        BigDecimal totalAmount,
        String instructions,
        ClientResponse client,
        VehicleResponse vehicle
) {
}

record ClientResponse(
        int id,
        String name,
        String email
) {}

record VehicleResponse(
        int id,
        String model,
        String category
) {}