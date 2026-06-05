package org.qa.core;

public interface AuditLogger {
    public void logTransaction(String transactionId, String userId, double amount, String status);
    public void logRefund(String transactionId, String userId, double amount);
    public void logError(String userId, String error);
}
