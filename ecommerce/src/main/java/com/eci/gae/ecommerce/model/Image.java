package com.eci.gae.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Image Class
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private String publicId;
    private String url;

}
