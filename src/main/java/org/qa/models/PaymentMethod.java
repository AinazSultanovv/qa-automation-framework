
// Модель способа оплаты
package org.qa.models;

public enum PaymentMethod {

    CARD(2.5, 1.0, true) {
        @Override
        public boolean isApplicable(double amount) {
            return amount >= this.getMinAmount();
        }
    },
    CRYPTO(1.0, 10.0, true) {
        @Override
        public boolean isApplicable(double amount) {
            return amount >= this.getMinAmount();
        }
    },
    BANK_TRANSFER(0.5, 100.0, false) {
        @Override
        public boolean isApplicable(double amount) {
            return amount >= this.getMinAmount();
        }
    },
    CASH(0.0, 0.01, true) {
        @Override
        public boolean isApplicable(double amount) {
            return amount >= this.getMinAmount();
        }
    };


    private final double feePercent;
    private final double minAmount;
    private final boolean isInstant;

    PaymentMethod(double feePercent, double minAmount, boolean isInstant) {
        this.feePercent = feePercent;
        this.minAmount = minAmount;
        this.isInstant = isInstant;
    }

    public double getFeePercent() { return feePercent; }
    public double getMinAmount() { return minAmount; }
    public boolean isInstant() { return isInstant; }

    public abstract boolean isApplicable(double amount);
}
