package com.eci.gae.ecommerce.service;

import com.eci.gae.ecommerce.dto.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

//    public String processPayment(int amount, String currency, String metadata) throws StripeException {
//        Stripe.apiKey = stripeSecretKey;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("amount", amount);
//        params.put("currency", currency);
//        params.put("metadata", metadata);
//
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//        return paymentIntent.getClientSecret();
//    }

    public String processPayment(PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentRequest.getAmount());
        params.put("currency", paymentRequest.getCurrency());
        params.put("metadata", paymentRequest.getMetadata());

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent.getClientSecret();
    }
}
