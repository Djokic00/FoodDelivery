FROM openjdk:21

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar .

CMD ["java","-Dspring.profiles.active=prod","-jar","order-service-0.0.1-SNAPSHOT.jar"]


