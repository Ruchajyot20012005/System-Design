package com.systemdesign.strategy.pricingStrategy;

import com.systemdesign.model.Ticket;
import com.systemdesign.model.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {

    private final Map<Class<? extends Vehicle>, Double> rates;

    public HourlyPricingStrategy(Map<Class<? extends Vehicle>, Double> rates) {
        this.rates = rates;
    }

    @Override
    public double calculateAmount(Ticket ticket) {

        long hours = Duration
                .between(ticket.getEntryTime(), LocalDateTime.now())
                .toHours();

        hours = Math.max(hours, 1);

        double rate = rates.getOrDefault(
                ticket.getVehicle().getClass(),
                20.0
        );

        return hours * rate;
    }
}
