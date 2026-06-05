package org.qa.unit.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qa.core.AuditLogger;
import org.qa.core.NotificationService;
import org.qa.core.PaymentGateway;
import org.qa.core.PaymentService;
import org.qa.models.PaymentResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    PaymentGateway paymentGateway;

    @Mock
    NotificationService notificationService;

    @Mock
    AuditLogger auditLogger;

    @InjectMocks
    PaymentService paymentService;

    @Test
    void shouldProcessPaymentSuccessfully() {
        // Arrange
        PaymentResult paymentResult = new PaymentResult(true, "TXN123", "Payment successful");
        when(paymentGateway.processPayment("user123", 100.0)).thenReturn(paymentResult);

        // Act
        PaymentResult result = paymentService.processPayment("user123", 100.0);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("TXN123", result.getTransactionId());
        verify(notificationService).sendPaymentConfirmation("user123", "TXN123", 100.0);
        verify(auditLogger).logTransaction("TXN123", "user123", 100.0, "SUCCESS");
    }

    @Test
    void shouldHandleFailedPayment(){
        // Arrange
        PaymentResult paymentResult = new PaymentResult(false, null, "Insufficient funds");
        when(paymentGateway.processPayment("user456", 500.0)).thenReturn(paymentResult);

        // Act
        PaymentResult result = paymentService.processPayment("user456", 500.0);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals("Insufficient funds", result.getMessage());
        verify(notificationService).sendErrorNotification("user456", "Insufficient funds");
        verify(notificationService, never()).sendPaymentConfirmation(anyString(), anyString(), anyDouble());
        verify(auditLogger, never()).logTransaction(anyString(), anyString(), anyDouble(), anyString());
    }

    @Test
    void shouldRefundSuccessfully(){
        // Arrange
        PaymentResult paymentResult = new PaymentResult(true,"REFUND456","Refund successful");
        when(paymentGateway.refund("TXN123")).thenReturn(paymentResult);

        // Act
        PaymentResult result = paymentService.refund("TXN123", "user123", 100.0);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("REFUND456", result.getTransactionId());
        verify(notificationService).sendRefundNotification("user123", "TXN123", 100.0);
        verify(auditLogger).logRefund("TXN123", "user123", 100.0);
    }
}