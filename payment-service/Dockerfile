FROM openjdk:21

WORKDIR /app

COPY target/payment-service-0.0.1-SNAPSHOT.jar .

COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x wait-for-it.sh

CMD ["java","-Dspring.profiles.active=prod","-jar","payment-service-0.0.1-SNAPSHOT.jar"]

