package com.eci.gae.ecommerce.model;// Update with your actual package name

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
@Document(collection = "orders")
public class Order {

    @MongoId
//    @Field("_id")
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    // Shipping Information
    private ShippingInfo shippingInfo;

    // Order Items
    //@Embedded
    //@ElementCollection
    private List<OrderItem> orderItems;

    // User
    @DBRef
    private User user;

    // Payment Information
    private PaymentInfo paymentInfo;

    private Date paidAt;

    private double itemsPrice;

    private double taxPrice;

    private double shippingPrice;

    private double totalPrice;

    // Order Status
    //@Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Date deliveredAt;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}

