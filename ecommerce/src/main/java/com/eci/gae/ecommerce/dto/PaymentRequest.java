package com.eci.gae.ecommerce.dto;

public class PaymentRequest {

    private String amount;
    private String currency;
    private String metadata;

    // Constructors, getters, and setters

    public PaymentRequest() {
    }

    public PaymentRequest(String amount, String currency, String metadata) {
        this.amount = amount;
        this.currency = currency;
        this.metadata = metadata;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
