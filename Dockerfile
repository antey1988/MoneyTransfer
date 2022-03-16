FROM openjdk:17
WORKDIR /app
COPY target/moneytransfer.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]