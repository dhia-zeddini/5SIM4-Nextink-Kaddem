FROM openjdk:17-jdk-slim
WORKDIR /app

ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

ARG VERSION

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
