FROM openjdk:21

WORKDIR /app

COPY target/payment-service-0.0.1-SNAPSHOT.jar .

CMD ["java","-Dspring.profiles.active=prod","-jar","payment-service-0.0.1-SNAPSHOT.jar"]

