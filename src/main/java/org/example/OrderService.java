package org.example;

public class OrderService {
    @AutoInjectable
    private PaymentProcessor paymentProcessor;

    @AutoInjectable
    private NotificationSender notificationSender;

    public void placeOrder(double amount, String message) {
        paymentProcessor.processPayment(amount);
        notificationSender.sendNotification(message);
    }

    // Геттеры для тестов
    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public NotificationSender getNotificationSender() {
        return notificationSender;
    }
}
