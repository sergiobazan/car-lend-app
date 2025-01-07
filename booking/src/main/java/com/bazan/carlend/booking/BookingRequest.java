package com.bazan.carlend.booking;

import java.time.LocalDateTime;

public record BookingRequest(
        LocalDateTime startDate,
        LocalDateTime endDate,
        String instructions,
        int clientId,
        int vehicleId
) {
}
