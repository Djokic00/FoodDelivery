# Food Delivery Application with Saga Pattern
## Overview
This project demonstrates a food delivery application built using a microservices architecture and employing the Saga pattern to manage complex transactions across multiple services. The application consists of various microservices, each responsible for specific functionalities such as order management, food availability, payment processing, etc.

## Microservices

### Saga Service:

Orchestrates the saga pattern for order fulfillment, coordinating activities across services to ensure a successful order.
Manages the saga instances and the flow of the saga.

### Food Service:

Handles food availability and inventory management.
Ensures that food items are available and updates the saga accordingly.
### Payment Service:

Processes payment for orders.
Coordinates with payment gateways and updates the saga based on payment success or failure.

### Order Service:

Manages order creation and provides order-related functionalities.
Initiates the saga for order processing.

### Main Service:

Main application service that communicates with the saga service and other microservices to initiate and track orders.

## Technologies Used
* Spring Boot: A Java-based framework for creating standalone, production-grade Spring-based applications.
* Spring Data JDBC: Part of the larger Spring Data project, it provides a simple and consistent way to access relational databases.
* Spring Security: Provides comprehensive security services for Java EE-based enterprise software applications.
* Maven: A widely used build automation and dependency management tool.
* Java 17: The latest LTS version of the Java programming language.

## Saga Pattern
The Saga pattern is employed to manage the order fulfillment process across multiple services. It ensures that an order is successfully processed only if the food is available and the payment is processed. The saga orchestrator coordinates the activities, ensuring consistency and handling compensating transactions in case of failures.

## Setup and Run
Clone the repository to your local machine.

Navigate to each microservice directory (e.g., saga-service, food-service) and build the project using Maven:

To change directory to `saga-service` and build the project using Maven:

```bash
cd saga-service
mvn clean install
```

Run each microservice using Spring Boot:
```bash
mvn spring-boot:run
```
Access the services through their respective endpoints.

