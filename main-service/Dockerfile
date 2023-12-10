# Stage 1: Build and install shared-dto-service
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copy and install shared-dto-service
COPY shared-service/pom.xml /app/shared-service/
COPY shared-service/src /app/shared-service/src/
RUN (cd shared-service && mvn install -DskipTests)

# Build main-service
COPY main-service/pom.xml /app/main-service/
COPY main-service/src /app/main-service/src/
RUN (cd main-service && mvn clean package -DskipTests)

# Stage 2: Create the final Docker image
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/main-service/target/main-service-0.0.1-SNAPSHOT.jar .
CMD ["java","-Dspring.profiles.active=prod","-jar","main-service-0.0.1-SNAPSHOT.jar"]
