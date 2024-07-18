FROM eclipse-temurin:21
WORKDIR /app
VOLUME /tmp
COPY target/time-racker-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
