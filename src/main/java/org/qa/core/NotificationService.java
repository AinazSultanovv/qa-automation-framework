package org.qa.core;

public interface NotificationService {
    public void sendPaymentConfirmation(String userId, String transactionId, double amount);
    public void sendRefundNotification(String userId, String transactionId, double amount);
    public void sendErrorNotification(String userId, String message);
}
