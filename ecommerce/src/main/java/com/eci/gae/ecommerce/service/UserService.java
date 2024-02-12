package com.eci.gae.ecommerce.service;

import com.eci.gae.ecommerce.model.User;

public interface UserService {

    User registerUser(User user);

    String loginUser(String email, String password);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);

    User loadUserByUsername(String username);

    // Other methods for user details, update password, update profile, get all users, get single user, update user role, delete user
}
