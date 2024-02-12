package com.eci.gae.ecommerce.model;

import com.eci.gae.ecommerce.util.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data//no need to write getters and setters
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Document(collection = "users") // Equivalent to mongoose.model("User", userSchema)
public class User {

    @MongoId
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id; // MongoDB _id field

    @NonNull
    private String name;

    @NonNull
    @Indexed(unique = true) // Equivalent to unique: true in Mongoose
    private String email;

    @NonNull
    private String password;

    @NonNull
    private Avatar avatar;

    private String role = "user";

    private Date createdAt;

    private String resetPasswordToken;

    private Date resetPasswordExpire;

    // Other methods and annotations...

    // Constructor, getters, setters, and other methods...
}
