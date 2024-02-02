# Food Delivery Application with Saga Pattern
## Overview
This project demonstrates a food delivery application built using a microservices architecture and employing the Saga pattern to manage complex transactions across multiple services. The application consists of various microservices, each responsible for specific functionalities such as order management, food availability, payment processing, etc.

## Microservices

- **Main Service**: The central service that interacts with other microservices for initiating and tracking orders.

- **Order Service**: Handles food availability and inventory management. Responsible for order creation and management.

- **Payment Service**: Handles payment processing for orders.

- **Saga Service**: Orchestrates the saga pattern for order fulfillment, coordinating activities across services to ensure a successful order.

## Technologies Used
- **Spring Boot**: A Java-based framework for creating standalone, production-grade Spring-based applications.
- **Spring Data JDBC**: Part of the larger Spring Data project, it provides a simple and consistent way to access relational databases.
- **Maven**: A widely used build automation and dependency management tool.
- **Java 21**: The programming language used.

## Saga Pattern
The application employs the Saga pattern to ensure reliable order processing across multiple services. This pattern is pivotal in ensuring that an order is successfully processed only if all conditions (like food availability and payment processing) are met. The saga orchestrator coordinates these activities, maintaining consistency and managing compensating transactions in case of failures.

## Setup and Run using Docker Compose
### Prerequisites
* Docker and Docker Compose installed on your machine.
* Basic understanding of Docker and microservices architecture.

### Clone the Repository
Clone this repository to your local machine using:

```bash
git clone https://github.com/Djokic00/FoodDelivery.git
```

### Navigate to the Project Directory

```bash
cd FoodDelivery
```

### Build and Run with Docker Compose

Run the following command to build and start all the microservices:

```bash
docker-compose up --build
```

This command builds the Docker images for each service (if not already built) and starts the containers as defined in your `docker-compose.yml` file.

### Accessing the Application

Once all services are up and running, you can access the application through the defined service endpoints or through the main service's interface.

### Stopping the Application

To stop the application, use:

```bash
docker-compose down
```

This command stops and removes the containers, networks, and volumes created by `docker-compose up`.

## Notes

- The application is set up to run using Java 21.
- If modifications are made to the source code, you may need to rebuild the Docker images using `docker-compose build` or `docker-compose up --build`.
