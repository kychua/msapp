FROM openjdk:8-jdk-alpine
ADD ./target/BookService.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/BookService.jar"]

EXPOSE 2222