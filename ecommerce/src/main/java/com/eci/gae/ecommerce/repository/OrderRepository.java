package com.eci.gae.ecommerce.repository;

import com.eci.gae.ecommerce.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {//string--> ObjectId>
    // Add custom queries if needed
}

