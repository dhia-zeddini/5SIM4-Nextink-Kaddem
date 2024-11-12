FROM openjdk:17-jdk-slim
WORKDIR /app

ARG JAR_FILE
COPY target/*.jar app.jar

ARG VERSION

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
