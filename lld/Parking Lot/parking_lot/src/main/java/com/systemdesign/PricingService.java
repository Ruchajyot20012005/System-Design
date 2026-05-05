package com.systemdesign;

import com.systemdesign.model.Ticket;
import com.systemdesign.strategy.pricingStrategy.PricingStrategy;

public class PricingService {

    private final PricingStrategy strategy;

    public PricingService(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(Ticket ticket) {
        return strategy.calculateAmount(ticket);
    }
}
