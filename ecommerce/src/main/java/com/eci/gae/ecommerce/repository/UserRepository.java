package com.eci.gae.ecommerce.repository;

import com.eci.gae.ecommerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Add custom queries if needed
}

