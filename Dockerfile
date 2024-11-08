FROM openjdk:17

WORKDIR /app

ARG NEXUSUSERNAME
ARG NEXUSPASSWORD
ARG PROJECTVERSION

RUN curl -u "$NEXUSUSERNAME:$NEXUSPASSWORD" -O "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/${PROJECTVERSION}/kaddem-${PROJECTVERSION}.jar"

ENV JAR_FILE="kaddem-${PROJECTVERSION}.jar"

RUN echo "Project Version: $JAR_FILE"

EXPOSE 9009

ENTRYPOINT ["sh", "-c", "java -jar /app/${JAR_FILE}"]
