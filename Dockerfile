FROM openjdk:17-jdk-slim
WORKDIR /app

# Define a build argument to accept the JAR file name
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
