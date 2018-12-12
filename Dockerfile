FROM openjdk:8-jdk-alpine
ADD ./target/WebService.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/WebService.jar"]

EXPOSE 4444