# Stage 1: Build and install shared-dto-service
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copy and install shared-dto-service
COPY shared-service/pom.xml /app/shared-service/
COPY shared-service/src /app/shared-service/src/
RUN (cd shared-service && mvn install -DskipTests)

# Build payment-service
COPY payment-service/pom.xml /app/payment-service/
COPY payment-service/src /app/payment-service/src/
RUN (cd payment-service && mvn clean package -DskipTests)

# Stage 2: Create the final Docker image
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/payment-service/target/payment-service-0.0.1-SNAPSHOT.jar .
CMD ["java","-Dspring.profiles.active=prod","-jar","payment-service-0.0.1-SNAPSHOT.jar"]
