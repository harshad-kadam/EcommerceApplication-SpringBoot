package com.eci.gae.ecommerce.service;

import com.eci.gae.ecommerce.exception.ResourceNotFoundException;
import com.eci.gae.ecommerce.model.Product;
import com.eci.gae.ecommerce.dto.ReviewResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

//    List<Product> getAllProducts();
//    Page<Product> getProducts(int page, int size, String keyword, Double minPrice, Double maxPrice);
//    long countProducts();

    Product getProductById(ObjectId productId) throws ResourceNotFoundException;

    Product updateProduct(ObjectId productId, Product updatedProduct) throws ResourceNotFoundException;

    void deleteProduct(ObjectId productId) throws ResourceNotFoundException;

    List<ReviewResponse> getProductReviews(String productId);
}
