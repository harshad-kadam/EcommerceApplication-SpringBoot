# EcommerceApplication-SpringBoot
This was a try to convert existing Node JS application into Spring boot application

## Spring Boot Application API Documentation

This repository contains the source code for a Spring Boot application that provides various APIs for managing orders, payments, products, and users. Below is the API documentation for the endpoints provided by this application:

---

### Order Controller

#### `DELETE /api/v1/admin/order/{orderId}`
- **Description:** Delete an order by its ID.
- **Operation:** deleteOrder

#### `PUT /api/v1/admin/order/{orderId}`
- **Description:** Update an existing order by its ID.
- **Operation:** updateOrder

#### `GET /api/v1/admin/orders`
- **Description:** Retrieve all orders.
- **Operation:** getAllOrders

#### `POST /api/v1/order/new`
- **Description:** Create a new order.
- **Operation:** createOrder

#### `GET /api/v1/order/{orderId}`
- **Description:** Get details of a single order by its ID.
- **Operation:** getSingleOrder

---

### Payment Controller

#### `GET /api/v1/payment/api/error`
- **Description:** Simulate an error response from the payment API.
- **Operation:** simulateError

#### `POST /api/v1/payment/process`
- **Description:** Process a payment.
- **Operation:** processPayment

---

### Product Controller

#### `POST /api/v1/admin/product/new`
- **Description:** Create a new product.
- **Operation:** createProduct

#### `DELETE /api/v1/admin/product/{productId}`
- **Description:** Delete a product by its ID.
- **Operation:** deleteProduct

#### `PUT /api/v1/admin/product/{productId}`
- **Description:** Update an existing product by its ID.
- **Operation:** updateProduct

#### `GET /api/v1/product/{productId}`
- **Description:** Get details of a single product by its ID.
- **Operation:** getProductDetails

#### `GET /api/v1/reviews`
- **Description:** Get reviews for products.
- **Operation:** getProductReviews

---

### User Controller

#### `POST /api/v1/users/forgot-password`
- **Description:** Initiate the password reset process for a user.
- **Operation:** forgotPassword

#### `POST /api/v1/users/login`
- **Description:** Login a user.
- **Operation:** loginUser

#### `GET /api/v1/users/logout`
- **Description:** Logout the currently authenticated user.
- **Operation:** logoutUser

#### `POST /api/v1/users/register`
- **Description:** Register a new user.
- **Operation:** registerUser

#### `POST /api/v1/users/reset-password/{token}`
- **Description:** Reset the password for a user using a reset token.
- **Operation:** resetPassword

---

## License

This project is licensed under the Apache 2.0 License. See the [LICENSE](https://github.com/harshad-kadam/EcommerceApplication-SpringBoot/blob/main/LICENSE) file for details.

---

Feel free to modify this README file as needed and replace the placeholders with actual links and descriptions.
