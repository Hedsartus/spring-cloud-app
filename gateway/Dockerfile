FROM openjdk:17-jdk-slim

EXPOSE 8082:8082

COPY target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

CMD ["java", "-jar", "gateway.jar"]