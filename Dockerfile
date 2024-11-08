FROM openjdk:17

WORKDIR /app

ARG NEXUSUSERNAME
ARG NEXUSPASSWORD
ARG PROJECTVERSION

# Download the JAR file using PROJECTVERSION
RUN curl -u "$NEXUSUSERNAME:$NEXUSPASSWORD" -O "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/${PROJECTVERSION}/kaddem-${PROJECTVERSION}.jar"

# Set an environment variable for the jar file path
ENV JAR_FILE="kaddem-${PROJECTVERSION}.jar"

RUN echo "Project Version: $JAR_FILE"

EXPOSE 9009

# Use the environment variable in the ENTRYPOINT
ENTRYPOINT ["java", "-jar", "/app/${JAR_FILE}"]
