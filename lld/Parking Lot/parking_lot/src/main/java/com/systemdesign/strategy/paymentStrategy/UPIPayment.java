package com.systemdesign.strategy.paymentStrategy;

public class UPIPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("UPI Payment done for amount: " + amount);
    }
}
