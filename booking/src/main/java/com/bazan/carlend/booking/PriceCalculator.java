package com.bazan.carlend.booking;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceCalculator implements IPriceCalculator {
    @Override
    public BigDecimal calculatePrice(BigDecimal vehiclePricePerDay, int totalDays) {
        return vehiclePricePerDay.multiply(new BigDecimal(totalDays));
    }
}
