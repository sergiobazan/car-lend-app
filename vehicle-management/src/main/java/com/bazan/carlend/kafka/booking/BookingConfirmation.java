package com.bazan.carlend.kafka.booking;

import com.bazan.carlend.vehicle.VehicleStatus;

public record BookingConfirmation(
        int bookingId,
        int vehicleId,
        VehicleStatus status
) {
}
