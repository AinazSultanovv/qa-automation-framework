package org.qa.core;

import org.qa.models.PaymentResult;

public class PaymentService {
    private final PaymentGateway paymentGateway;
    private final NotificationService notificationService;
    private final AuditLogger auditLogger;

    public PaymentService(PaymentGateway paymentGateway, NotificationService notificationService, AuditLogger auditLogger) {
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
        this.auditLogger = auditLogger;
    }

    public PaymentResult processPayment(String userId, double amount) {
        PaymentResult result = paymentGateway.processPayment(userId, amount);

        if (result.isSuccess()) {
            notificationService.sendPaymentConfirmation(userId, result.getTransactionId(), amount);
            auditLogger.logTransaction(result.getTransactionId(), userId, amount, "SUCCESS");
        } else {
            notificationService.sendErrorNotification(userId, result.getMessage());
            auditLogger.logError(userId, result.getMessage());
        }

        return result;
    }

    public PaymentResult refund(String transactionId, String userId, double amount) {
        PaymentResult result = paymentGateway.refund(transactionId);

        if (result.isSuccess()) {
            notificationService.sendRefundNotification(userId, transactionId, amount);
            auditLogger.logRefund(transactionId, userId, amount);
        } else {
            notificationService.sendErrorNotification(userId, result.getMessage());
            auditLogger.logError(userId, result.getMessage());
        }

        return result;
    }
}