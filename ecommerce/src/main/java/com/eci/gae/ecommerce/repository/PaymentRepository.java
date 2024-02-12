package com.eci.gae.ecommerce.repository;

import com.eci.gae.ecommerce.dto.PaymentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentRequest, String> {
    // Add custom queries if needed
}

