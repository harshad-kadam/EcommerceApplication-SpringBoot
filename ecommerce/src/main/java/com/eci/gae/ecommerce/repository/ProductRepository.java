package com.eci.gae.ecommerce.repository;

import com.eci.gae.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    //Page<Product> findAll(Query query, PageRequest pageRequest);
    //Page<Product> findAll(Query query, Pageable pageable);

    // Add custom queries if needed
}
