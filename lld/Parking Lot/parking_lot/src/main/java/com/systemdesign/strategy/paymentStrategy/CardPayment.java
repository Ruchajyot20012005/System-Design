package com.systemdesign.strategy.paymentStrategy;

public class CardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Card Payment done for amount: " + amount);
    }
}
