FROM openjdk:21

WORKDIR /app

COPY target/saga-service-0.0.1-SNAPSHOT.jar .

COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x wait-for-it.sh

CMD ["java","-Dspring.profiles.active=prod","-jar","saga-service-0.0.1-SNAPSHOT.jar"]


