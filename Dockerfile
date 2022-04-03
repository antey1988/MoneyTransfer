FROM openjdk:17-alpine
RUN mkdir -p /var/opt/app
COPY ./target/*.jar /var/opt/MoneyTransfer.jar
WORKDIR /var/opt/
ENTRYPOINT ["java", "-jar", "MoneyTransfer.jar"]
