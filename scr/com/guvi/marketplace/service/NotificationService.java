// com/guvi/marketplace/service/NotificationService.java
package com.guvi.marketplace.service;

public class NotificationService {

    public static void asyncSendOrderConfirmation(int customerId, int orderId) {
        Thread t = new Thread(() -> {
            try {
                // Simulate sending email/SMS (I/O-bound)
                System.out.printf("Sending confirmation to customer %d for order %d%n", customerId, orderId);
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        });
        t.setName("OrderConfirmation-" + orderId);
        t.setDaemon(true);
        t.start();
    }
}
