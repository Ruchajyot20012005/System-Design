package com.systemdesign.strategy.pricingStrategy;

import com.systemdesign.model.Ticket;
import com.systemdesign.model.vehicle.Vehicle;

import java.util.Map;

public class FlatPricingStrategy implements PricingStrategy {

    private final Map<Class<? extends Vehicle>, Double> rates;

    public FlatPricingStrategy(Map<Class<? extends Vehicle>, Double> rates) {
        this.rates = rates;
    }

    @Override
    public double calculateAmount(Ticket ticket) {
        return rates.getOrDefault(
                ticket.getVehicle().getClass(),
                50.0
        );
    }
}