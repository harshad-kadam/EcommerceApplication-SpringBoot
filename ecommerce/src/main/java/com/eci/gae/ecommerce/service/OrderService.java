package com.eci.gae.ecommerce.service;

import com.eci.gae.ecommerce.exception.CustomException;
import com.eci.gae.ecommerce.model.Order;
import com.eci.gae.ecommerce.model.OrderItem;
import com.eci.gae.ecommerce.model.OrderStatus;
import com.eci.gae.ecommerce.model.Product;
import com.eci.gae.ecommerce.repository.OrderRepository;
import com.eci.gae.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
//    MongoTemplate mongoTemplate;
//    MongoDatabase mongoDatabase = mongoTemplate.getDb();
//    MongoCollection<Document> targetCollection
//            = mongoDatabase.getCollection(mongoTemplate.getCollectionName(Order.class));

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

//    public Order createOrder(Order order) {
//        // Add any additional business logic before saving the order
//        return orderRepository.save(order);
//    }


    @Transactional
    public Order createOrder(Order orderRequest) {
        // Validate and process the order request
//        validateOrderRequest(orderRequest);
//
//        // Fetch products and calculate total price
//        List<Product> products = productService.getProductsByIds(orderRequest.getProductIds());
//        double totalPrice = calculateTotalPrice(products, orderRequest.getQuantities());
//
//        // Create and save the order
//        Order order = new Order();
//        order.setUserId(userId);
//        order.setProducts(products);
//        order.setQuantities(orderRequest.getQuantities());
//        order.setTotalPrice(totalPrice);
//        order.setOrderStatus(OrderStatus.PENDING);
//        order.setCreatedAt(new Date());
//
//        Order savedOrder = orderRepository.save(order);
        Order savedOrder = orderRepository.save(orderRequest);

        // Deduct product quantities from the stock
//        updateProductStock(products, orderRequest.getQuantities());

        return savedOrder;
    }

    private void validateOrderRequest(Order orderRequest) {
        // Implement validation logic
        // Example: Check if product IDs and quantities match
    }

    private double calculateTotalPrice(List<Product> products, List<Integer> quantities) {
        // Implement logic to calculate the total price
        // based on the product prices and quantities
        // Example: Sum(product price * quantity) for each product
        return 0.0;
    }

    private void updateProductStock(List<Product> products, List<Integer> quantities) {
        // Implement logic to update product stock
        // Example: Deduct quantities from the product stock
    }



    public Order getSingleOrder(String orderId) {
        // Validate inputs
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID must be provided");
        }

        // Check if the order exists
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found with ID: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

//    public void updateOrder(Long orderId, Order order) {
//        // Implement logic to update an order
//        orderRepository.save(order);
//    }
    public void updateOrder(String orderId, Order order) {
//        Document query = new Document();
//        query.put("_id", orderId);

        // Validate inputs
        String newStatus= String.valueOf(order.getOrderStatus());
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID must be provided");
        }
        // Check if the order exists
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found with ID: " + orderId));
//        Order existingOrder = (Order) targetCollection.find(query);
        // Check if the order is already delivered
        if ("Delivered".equalsIgnoreCase(String.valueOf(existingOrder.getOrderStatus()))) {
            throw new CustomException("Order has already been delivered");
        }
        // Update order status
        existingOrder.setOrderStatus(OrderStatus.valueOf(newStatus));

        // If the status is "Shipped", update stock
        if ("Shipped".equals(newStatus)) {
            // Call a method to update stock (replace with your logic)
            existingOrder.getOrderItems().forEach(this::updateStock);
        }
        // If the status is "Delivered", set deliveredAt
        if ("Delivered".equals(newStatus)) {
            existingOrder.setDeliveredAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        }
        // Save and return the updated order
        orderRepository.save(existingOrder);
    }

    public void updateStock(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        int quantity = orderItem.getQuantity();

        // Retrieve the product from the database
        Product existingProduct = productRepository.findById(String.valueOf(product.getId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the stock
        int updatedStock = existingProduct.getStock() - quantity;
        existingProduct.setStock(updatedStock);

        // Save the updated product
        productRepository.save(existingProduct);
    }

    public void deleteOrder(String orderId) {
        // Validate inputs
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID must be provided");
        }

        // Check if the order exists
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found with ID: " + orderId));

        // Delete the order
        orderRepository.delete(existingOrder);
    }
//    public List<Order> getMyOrders(String userId) {
//        // Validate inputs
//        if (userId == null || userId.isEmpty()) {
//            throw new IllegalArgumentException("User ID must be provided");
//        }
//
//        // Retrieve user's orders
//        return orderRepository.findByUserId(userId);
//    }
}
