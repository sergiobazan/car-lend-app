package com.bazan.carlend.booking;

import java.math.BigDecimal;

public interface IPriceCalculator {
    BigDecimal calculatePrice(BigDecimal vehiclePricePerDay, int totalDays);
}
