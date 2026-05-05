package com.systemdesign.model.gate;

import com.systemdesign.PaymentService;
import com.systemdesign.PricingService;
import com.systemdesign.model.Ticket;
import com.systemdesign.strategy.paymentStrategy.PaymentStrategy;

public class ExitGate {

    private final PricingService pricingService;

    public ExitGate(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public double getAmount(Ticket ticket) {
        return pricingService.calculate(ticket);
    }

    public void makePayment(Ticket ticket, double amount, PaymentStrategy strategy) {


        PaymentService paymentService = new PaymentService(strategy);
        paymentService.process(amount);

        ticket.getSpot().releaseSpot();

        System.out.println("Spot " + ticket.getSpot().getSpotId() + " has been released.");
    }
}
