package com.systemdesign.strategy.paymentStrategy;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Cash Payment done for amount: " + amount);
    }
}
