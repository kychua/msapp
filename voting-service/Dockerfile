FROM openjdk:8-jdk-alpine
ADD ./target/VotingService.jar /app/
CMD ["java", "-Dspring.profiles.active=docker", "-Xmx200m", "-jar", "/app/VotingService.jar"]

EXPOSE 6666