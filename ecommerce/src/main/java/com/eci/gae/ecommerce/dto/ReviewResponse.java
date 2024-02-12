package com.eci.gae.ecommerce.dto;

import com.eci.gae.ecommerce.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private ObjectId userId;
    private String userName;
    private int rating;
    private String comment;

    public ReviewResponse(User user, int rating, String comment) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.rating = rating;
        this.comment = comment;
    }
}