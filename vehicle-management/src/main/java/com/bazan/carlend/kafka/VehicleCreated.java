package com.bazan.carlend.kafka;

import com.bazan.carlend.vehicle.VehicleStatus;

import java.math.BigDecimal;

public record VehicleCreated(
         int id,
         String model,
         BigDecimal pricePerDay,
         VehicleStatus status,
         String category
) {
}
