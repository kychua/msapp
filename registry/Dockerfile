FROM openjdk:8-jdk-alpine
ADD ./target/Registry.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/Registry.jar"]

EXPOSE 1111