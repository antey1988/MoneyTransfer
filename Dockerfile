FROM openjdk:17
RUN mkdir -p /var/opt/app
COPY ./target/*.jar /var/opt/MoneyTransfer.jar
WORKDIR /var/opt/
ENTRYPOINT ["java", "-jar", "MoneyTransfer.jar"]
