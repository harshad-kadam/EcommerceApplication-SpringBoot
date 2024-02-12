package com.eci.gae.ecommerce.controller;

import com.eci.gae.ecommerce.exception.CustomResponseEntityExceptionHandler;
import com.eci.gae.ecommerce.exception.CustomException;
import com.eci.gae.ecommerce.model.Order;
import com.eci.gae.ecommerce.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value="Order Management System")
public class OrderController {
    @Autowired
    private CustomResponseEntityExceptionHandler exceptionHandler;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/new")
    public ResponseEntity<Object> createOrder(@RequestBody Order orderRequest,
                                             @RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extract user ID from the authorization header
            //String userId = extractUserIdFromHeader(authorizationHeader);

            Order createdOrder = orderService.createOrder(orderRequest);//orderService.createOrder(orderRequest, userId);
            ResponseEntity<Object> response = new ResponseEntity<>(Map.of(
                    "success", true,
                    "order", createdOrder
            ), HttpStatus.CREATED);
            return response;
        } catch (CustomException e) {
            // Handle order creation exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    private String extractUserIdFromHeader(String authorizationHeader) {
//        // Implement logic to extract and return the user ID from the header
//        // Example: Decode the JWT token and extract user ID
//        return "user123"; // Placeholder, replace with actual logic
//    }
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    public static String extractUserIdFromHeader(HttpHeaders headers) {
//        try {
//            Objects.requireNonNull(headers, "Headers must not be null");
//
//            if (!headers.containsKey(AUTHORIZATION_HEADER)) {
//                throw new IllegalArgumentException("Authorization header is missing");
//            }
//
//            String authorizationHeader = headers.getFirst(AUTHORIZATION_HEADER);
//
//            // Assuming a simple format like "Bearer {token}", you may adjust accordingly
//            String[] headerParts = authorizationHeader.split("\\s+");
//
//            if (headerParts.length != 2 || !"Bearer".equals(headerParts[0])) {
//                throw new IllegalArgumentException("Invalid Authorization header format");
//            }
//
//            // Here, you might want to decode the token and extract user information
//            // For simplicity, let's assume the second part is the user ID
//            return headerParts[1];
//        } catch (Exception e) {
//            // Handle and log exceptions as needed
//            throw new RuntimeException("Failed to extract user ID from Authorization header", e);
//        }
//    }


    @GetMapping("/order/{orderId}")

    public ResponseEntity<Object> getSingleOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.getSingleOrder(orderId);
            return ResponseEntity.ok().body(Map.of("success", true, "order", order));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false,"error", e.getMessage()));
        }
    }
    @GetMapping("/admin/orders")
    public ResponseEntity<Object> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();

            // Calculate total amount
            double totalAmount = orders.stream()
                    .mapToDouble(Order::getTotalPrice)
                    .sum();

            return ResponseEntity.ok().body(Map.of("success", true, "totalAmount", totalAmount, "orders", orders));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }
    @PutMapping("/admin/order/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable String orderId, @RequestBody Order updatedOrder) {
        try {
            System.out.println("1st");
            orderService.updateOrder(orderId, updatedOrder);
            System.out.println("latst");
            ResponseEntity<Object> response = new ResponseEntity<>(Map.of(
                    "success", true
            ), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing payment: " + e.getMessage());
        } /*catch (Exception ex) {
            return exceptionHandler.handleException(ex, HttpStatus.BAD_REQUEST, null);
        }*/
    }

    @DeleteMapping("/admin/order/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                                    "success", false,
                                    "error", e.getMessage()
                            ));
        }
    }

//    @GetMapping("/orders/me")
//    public ResponseEntity<Object> getMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
//        try {
//            List<Order> orders = orderService.getMyOrders(userDetails.getUsername());
//            return ResponseEntity.ok().body(Collections.singletonMap("success", true, "orders", orders));
//        } catch (CustomException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
}
