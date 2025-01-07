package com.bazan.carlend.vehicle;

import com.bazan.carlend.category.Category;

import java.math.BigDecimal;

public record VehicleResponse(
        int id,
        String model,
        String characteristics,
        BigDecimal pricePerDay,
        long mileage,
        VehicleStatus status,
        Category category
) {
}
