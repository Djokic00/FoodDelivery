FROM openjdk:21

WORKDIR /app

COPY target/main-service-0.0.1-SNAPSHOT.jar .

CMD ["java","-Dspring.profiles.active=prod","-jar","main-service-0.0.1-SNAPSHOT.jar"]


