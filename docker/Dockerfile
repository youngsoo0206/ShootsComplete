FROM openjdk:17-jdk-slim

COPY ./app.jar /app.jar

EXPOSE 1000

ENTRYPOINT ["java", "-Dspring.profiles.active=deploy", "-jar", "app.jar"]