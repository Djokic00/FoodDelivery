# Stage 1: Build and install shared-dto-service
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copy and install shared-dto-service
COPY shared-service/pom.xml /app/shared-service/
COPY shared-service/src /app/shared-service/src/
RUN (cd shared-service && mvn install -DskipTests)

# Build order-service
COPY order-service/pom.xml /app/order-service/
COPY order-service/src /app/order-service/src/
RUN (cd order-service && mvn clean package -DskipTests)

# Stage 2: Create the final Docker image
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/order-service/target/order-service-0.0.1-SNAPSHOT.jar .
CMD ["java","-Dspring.profiles.active=prod","-jar","order-service-0.0.1-SNAPSHOT.jar"]