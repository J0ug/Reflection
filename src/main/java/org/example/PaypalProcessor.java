package org.example;

public class PaypalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal.");
    }
}