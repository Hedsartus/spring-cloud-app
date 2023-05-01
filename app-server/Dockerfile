FROM openjdk:17-jdk-slim

EXPOSE 8081:8081

COPY target/app-server-0.0.1-SNAPSHOT.jar app-server.jar

CMD ["java", "-jar", "app-server.jar"]