package org.qa.core;

import org.qa.models.PaymentResult;

public interface PaymentGateway {
    PaymentResult processPayment(String userId, double amount);
    PaymentResult refund(String transactionId);
    String getTransactionStatus(String transactionId);
}
