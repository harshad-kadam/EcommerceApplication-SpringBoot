package com.eci.gae.ecommerce.controller;
// ProductController.java

import com.eci.gae.ecommerce.exception.CustomResponseEntityExceptionHandler;
import com.eci.gae.ecommerce.exception.ResourceNotFoundException;
import com.eci.gae.ecommerce.model.Product;
import com.eci.gae.ecommerce.dto.ReviewResponse;
import com.eci.gae.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value="Products Management System")
public class ProductController {

    private final ProductService productService;
    @Autowired
    private CustomResponseEntityExceptionHandler exceptionHandler;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/admin/product/new")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        try {
            Product productRes = productService.createProduct(product);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("product", productRes);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @PostMapping("/create-product")
//    public ResponseEntity<Object> createProduct(@RequestBody ProductRequestDto productRequest, @AuthenticationPrincipal UserDetails userDetails) {
//        try {
//            String userId = userDetails.getUsername();
//            Product product = productService.createProduct(productRequest, userId);
//
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(Collections.singletonMap("success", true, "product", product));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }




//    @GetMapping("/products")
//    public Page<Product> getProducts(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) String keyword,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice
//    ) {
//        return productService.getProducts(page, size, keyword, minPrice, maxPrice);
//    }
//    public ResponseEntity<Map<String, Object>> getAllProducts(
//            @RequestParam(defaultValue = "1", value = "page") int page,
//            @RequestParam(defaultValue = "8", value = "resultPerPage") int resultPerPage,
//            HttpServletRequest request
//    ) {
//        // Count total documents
//        long productsCount = productService.countProducts();
//
//        // Create ApiFeatures instance
//        ApiFeatures apiFeatures = new ApiFeatures((Query) productService.getAllProducts(), (Map<String, String>) request)
//                .search()
//                .filter();
//
//        // Clone query to get filtered products
//        List<Product> products = apiFeatures.getQuery().getResultList();
//
//        // Get filtered products count
//        long filteredProductsCount = products.size();
//
//        // Perform pagination
//        apiFeatures.pagination(resultPerPage);
//
//        // Get paginated products
//        products = apiFeatures.getQuery().getResultList();
//
//        // Build response
//        Map<String, Object> response = new HashMap<>();
//        response.put("success", true);
//        response.put("products", products);
//        response.put("productsCount", productsCount);
//        response.put("resultPerPage", resultPerPage);
//        response.put("filteredProductsCount", filteredProductsCount);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping("/product/{productId}")
//    public ResponseEntity<Object> getProductDetails(@PathVariable String productId) {
//        try {
//            ObjectId objectId = new ObjectId(productId);
//            Product product = productService.getProductById(objectId);
//            return new ResponseEntity<>(Map.of("success", true, "product", product), HttpStatus.OK);
//        } catch (ResourceNotFoundException error) {
//            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (IllegalArgumentException ex) {
//            // Handle the case where the provided ID is not a valid ObjectId
//            return new ResponseEntity<>(Map.of("success", false, "error", "Invalid ObjectId format"), HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Object> getProductDetails(@PathVariable ObjectId productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<>(Map.of("success", true, "product", product), HttpStatus.OK);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // Update a product
//    @PutMapping("/admin/product/{productId}")
//    public ResponseEntity<Object> updateProduct(@PathVariable ObjectId productId, @RequestBody Product updatedProduct) {
//        try {
//            Product product = productService.updateProduct(productId, updatedProduct);
//            return new ResponseEntity<>(Map.of("success", true, "product", product), HttpStatus.OK);
//        } catch (ResourceNotFoundException error) {
//            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception error) {
//            System.out.println("I am here harshad"+ error);
//            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
    // Update a product
    @PutMapping("/admin/product/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable ObjectId productId, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(productId, updatedProduct);
            return new ResponseEntity<>(Map.of("success", true, "product", product), HttpStatus.OK);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException error) {
            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception error) {
            return new ResponseEntity<>(Map.of("success", false, "error", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a product
    @DeleteMapping("/admin/product/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable ObjectId productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(Map.of("success", true, "message", "Product deleted successfully"), HttpStatus.OK);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(Map.of("success", false, "error", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create or Update a product review
//    @PostMapping("/review")
//    public ResponseEntity<Object> createOrUpdateProductReview(@RequestBody ReviewRequest reviewRequest) {
//        try {
//            productService.createOrUpdateProductReview(reviewRequest);
//            return new ResponseEntity<>(Map.of("success", true), HttpStatus.OK);
//        } catch (ResourceNotFoundException error) {
//            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception error) {
//            return new ResponseEntity<>(Map.of("success", false, "error", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // Get All Reviews of a product
    @GetMapping("/reviews")
    public ResponseEntity<Object> getProductReviews(@RequestParam String productId) {
        try {
            List<ReviewResponse> reviews = productService.getProductReviews(productId);
            return new ResponseEntity<>(Map.of("success", true, "reviews", reviews), HttpStatus.OK);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity<>(Map.of("success", false, "error", error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(Map.of("success", false, "error", "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// Delete Review

}
