package com.eci.gae.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

// Review Class
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @DBRef
    private User user;
    private String name;
    private double rating;
    private String comment;
}
