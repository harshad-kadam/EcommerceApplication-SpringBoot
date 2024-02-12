package com.eci.gae.ecommerce.controller;

import com.eci.gae.ecommerce.dto.PaymentRequest;
import com.eci.gae.ecommerce.repository.PaymentRepository;
import com.eci.gae.ecommerce.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@Api(value="Payment Management System")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok("Payment processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing payment: " + e.getMessage());
        }
    }

    @GetMapping("/api/error")
    public ResponseEntity<String> simulateError() {
        throw new RuntimeException("Simulated error");
    }

    // Other endpoints and methods...
}
