package com.systemdesign;


import com.systemdesign.strategy.paymentStrategy.PaymentStrategy;

public class PaymentService {

    private final PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void process(double amount) {
        strategy.pay(amount);
    }
}
