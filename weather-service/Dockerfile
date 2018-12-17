FROM openjdk:8-jdk-alpine
ADD ./target/WeatherService.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/WeatherService.jar"]

EXPOSE 5555