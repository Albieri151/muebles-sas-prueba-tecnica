FROM eclipse-temurin:21-jdk-alpine

RUN adduser -D appuser

WORKDIR /app

COPY *.jar muebles-sas.jar

ENV JAVA_OPTS=" \
  -XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=70 \
  -Djava.security.egd=file:/dev/./urandom \
"

USER appuser

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar muebles-sas.jar"]
