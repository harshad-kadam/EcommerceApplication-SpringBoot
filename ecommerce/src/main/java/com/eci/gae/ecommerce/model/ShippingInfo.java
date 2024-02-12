package com.eci.gae.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Shipping Information
@Data
@NoArgsConstructor
@AllArgsConstructor
class ShippingInfo {
    private String address;
    private String city;
    private String state;
    private String country;
    private int pinCode;
    private long phoneNo;
}
