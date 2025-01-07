package com.bazan.carlend.vehicle;

import java.math.BigDecimal;

public record VehicleRequest(
        String model,
        String characteristics,
        BigDecimal pricePerDay,
        long mileage,
        int categoryId
) {
}
