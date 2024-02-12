package com.eci.gae.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Payment Information
@Data
@NoArgsConstructor
@AllArgsConstructor
class PaymentInfo {
    private String id;
    private String status;
}
