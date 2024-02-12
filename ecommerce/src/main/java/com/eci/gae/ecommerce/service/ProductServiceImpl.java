package com.eci.gae.ecommerce.service;

import com.eci.gae.ecommerce.exception.ResourceNotFoundException;
import com.eci.gae.ecommerce.model.Image;
import com.eci.gae.ecommerce.model.Product;
import com.eci.gae.ecommerce.dto.ReviewResponse;
import com.eci.gae.ecommerce.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        List<Image> images = new ArrayList<>();
//
//        if (product.getImages() instanceof Object || product.getImages() instanceof String) {
//            images.add((Image) product.getImages());
//        } else {
//            images = (List<Image>) product.getImages();
//        }
//        product.setImages(images);
        images = product.getImages().stream()
                .map(imageUrl -> {
                    // Your logic to upload image to Cloudinary and get public_id and secure_url
                    // CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadImage(imageUrl);
                    // return new Image(cloudinaryResponse.getPublicId(), cloudinaryResponse.getSecureUrl());
                    return new Image("sample_public_id", "sample_secure_url");
                })
                .collect(Collectors.toList());
        // Set images
        product.setImages(images);
        return productRepository.save(product);
    }


//    public Product createProduct(Product productRequest) {//, String userId
//        // Convert image URLs to public_id and secure_url using cloudinary (replace with your logic)
//        List<Image> images = productRequest.getImages().stream()
//                .map(imageUrl -> {
//                    // Your logic to upload image to Cloudinary and get public_id and secure_url
//                    // CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadImage(imageUrl);
//                    // return new Image(cloudinaryResponse.getPublicId(), cloudinaryResponse.getSecureUrl());
//                    return new Image("sample_public_id", "sample_secure_url");
//                })
//                .collect(Collectors.toList());
//
//        // Set user ID
//        productRequest.setUser(userId);
//
//        // Set images
//        productRequest.setImages(images);
//
//        // Create product
//        return productRepository.save(ProductMapper.toEntity(productRequest));
//    }
//    @Override

//    public List<Product> getAllProducts() {
//        // Implement logic to get all products
//        return productRepository.findAll();
//    }
//    public Page<Product> getProducts(int page, int size, String keyword, Double minPrice, Double maxPrice) {
//        ApiFeatures apiFeatures = new ApiFeatures(new Query())
//                .search(keyword)
//                .filter(minPrice, maxPrice);
//
//        PageRequest pageRequest = PageRequest.of(page - 1, size);
//        return productRepository.findAll((org.springframework.data.mongodb.repository.Query) apiFeatures.getQuery(), pageRequest);
//    }
//    @Override
//    public long countProducts() {
//        return productRepository.count();
//    }
@Override
public Product getProductById(ObjectId productId) throws ResourceNotFoundException {
    // Implement logic to get product by ID
    return productRepository.findById(String.valueOf(productId))
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
}
//    public Product getProductById(String productId) {
//        // Implement logic to retrieve product by ID from the repository
//        // Example:
//        // Product product = productRepository.findById(productId).orElse(null);
//
//        // If product is not found, throw an exception
//        if (product == null) {
//            throw new ErrorHandler("Product not found", 404);
//        }
//
//        return product;
//    }


@Override
    // Update a product
    public Product updateProduct(ObjectId productId, Product updatedProduct) throws ResourceNotFoundException, IllegalArgumentException {
        // Find the existing product by ID
        Product existingProduct = productRepository.findById(String.valueOf(productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        // Validate the update (add your validation logic)
//        if (updatedProduct.getName() == null || updatedProduct.getDescription() == null ) {
//            throw new IllegalArgumentException("Invalid request. Please provide name, description for the product.");
//        }

        // Update the fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        // ... other fields

        // Save and return the updated product
        return productRepository.save(existingProduct);
    }


    // Delete a product
    @Override
    public void deleteProduct(ObjectId productId) throws ResourceNotFoundException {
        // Find the existing product by ID
        Product product = productRepository.findById(String.valueOf(productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        // Deleting Images From Cloudinary
//        for (ProductImage image : product.getImages()) {
//            // Assuming ProductImage has a public_id field
//            cloudinary.v2.uploader.destroy(image.getPublicId());
//        }

        // Delete the product
        productRepository.delete(product);
    }

    // Create or Update a product review
//    public void createOrUpdateProductReview(ReviewRequest reviewRequest) throws ResourceNotFoundException {
//        Product product = productRepository.findById(reviewRequest.getProductId())
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + reviewRequest.getProductId()));
//
//        User currentUser = // Assume you have a way to get the current user, possibly from a Spring Security context
//
//                Review review = new Review(currentUser, reviewRequest.getRating(), reviewRequest.getComment());
//
//        Optional<Review> existingReview = product.getReviews().stream()
//                .filter(r -> r.getUser().getId().equals(currentUser.getId()))
//                .findFirst();
//
//        if (existingReview.isPresent()) {
//            // Update existing review
//            Review updatedReview = existingReview.get();
//            updatedReview.setRating(reviewRequest.getRating());
//            updatedReview.setComment(reviewRequest.getComment());
//        } else {
//            // Add new review
//            product.getReviews().add(review);
//            product.setNumOfReviews(product.getReviews().size());
//        }
//
//        // Calculate average rating
//        double avgRating = product.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0.0);
//        product.setRatings(avgRating);
//
//        // Save the product
//        productRepository.save(product);
//    }

    // Get All Reviews of a product
    public List<ReviewResponse> getProductReviews(String productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        return product.getReviews().stream()
                .map(review -> new ReviewResponse(review.getUser(), (int) review.getRating(), review.getComment()))
                .collect(Collectors.toList());
    }
}
