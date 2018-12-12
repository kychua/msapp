FROM openjdk:8-jdk-alpine
ADD ./target/Gateway.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/Gateway.jar"]

EXPOSE 4444