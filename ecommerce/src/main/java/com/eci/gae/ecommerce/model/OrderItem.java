package com.eci.gae.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

// Order Item
//@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String name;
    private double price;
    private int quantity;
    private String image;

    @DBRef
    private Product product;
}
