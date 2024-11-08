FROM openjdk:17

# Create a non-root user and group
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Set up the working directory
WORKDIR /app

# Define the ARGs
ARG NEXUSUSERNAME
ARG NEXUSPASSWORD
ARG PROJECTVERSION

# Download the JAR file using provided credentials
RUN curl -u $NEXUSUSERNAME:$NEXUSPASSWORD -O http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/${PROJECTVERSION}/kaddem-${PROJECTVERSION}.jar

# Set file permissions for the non-root user
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser
EXPOSE 9009

ENTRYPOINT ["sh", "-c", "java -jar /app/${JAR_FILE}"]
