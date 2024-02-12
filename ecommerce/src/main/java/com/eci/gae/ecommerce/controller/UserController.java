package com.eci.gae.ecommerce.controller;

import com.eci.gae.ecommerce.model.User;
import com.eci.gae.ecommerce.service.UserService;
import com.eci.gae.ecommerce.util.JwtTokenUtil;
import com.eci.gae.ecommerce.util.SendEmailUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Api(value="Users Management System")
//@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
//       private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Assuming avatar is provided in the request body
        User createdUser = userService.registerUser(user);
        String token = jwtTokenUtil.generateToken(String.valueOf(createdUser));
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        try {
//            userService.registerUser(user);
//            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return exceptionHandler.handleException(ex, HttpStatus.BAD_REQUEST, null);
//        }
//    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        try {
//            userService.loginUser(user);
//            return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return exceptionHandler.handleException(ex, HttpStatus.BAD_REQUEST, null);
//        }
//    }
    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return ResponseEntity.ok("Logged Out");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody User user) {
        userService.forgotPassword(user.getEmail());
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody User user) {
        userService.resetPassword(token, user.getPassword());
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }

    // Other methods for user details, update password, update profile, get all users, get single user, update user role, delete user
}
