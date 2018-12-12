FROM openjdk:8-jdk-alpine
ADD ./target/WebService.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/WebService.jar"]

EXPOSE 3333