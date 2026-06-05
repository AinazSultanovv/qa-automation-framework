package org.qa.core;

public interface EmailService {
    void sendVerificationEmail(String email, String verificationCode);
    void sendWelcomeEmail(String email);
}
