package com.systemdesign.strategy.pricingStrategy;

import com.systemdesign.model.Ticket;

public interface PricingStrategy {
    double calculateAmount(Ticket ticket);
}
