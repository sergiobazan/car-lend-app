package com.bazan.carlend.kafka;

import com.bazan.carlend.vehicle.VehicleStatus;

public record BookingConfirmation(
        int bookingId,
        int vehicleId,
        VehicleStatus status
) {
}
