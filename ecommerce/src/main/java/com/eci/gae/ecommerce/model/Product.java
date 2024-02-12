package com.eci.gae.ecommerce.model;

import com.eci.gae.ecommerce.util.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @MongoId
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String name;

    private String description;

    private double price;

    private double ratings;

    //@ElementCollection
    private List<Image> images;

    private String category;

    private int stock;

    private int numOfReviews;

    //@ElementCollection
    private List<Review> reviews;

    @DBRef
    private User user;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}

