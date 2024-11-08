FROM openjdk:17

RUN groupadd -r appgroup && useradd -r -g appgroup appuser

WORKDIR /app

ARG NEXUSUSERNAME
ARG NEXUSPASSWORD
ARG PROJECTVERSION

RUN curl -u $NEXUSUSERNAME:$NEXUSPASSWORD -O http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/${PROJECTVERSION}/kaddem-${PROJECTVERSION}.jar

RUN chown -R appuser:appgroup /app

USER appuser
ENV JAR_FILE="kaddem-${PROJECTVERSION}.jar"

EXPOSE 9009

ENTRYPOINT ["sh", "-c", "java -jar /app/${JAR_FILE}"]
