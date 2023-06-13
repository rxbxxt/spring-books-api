FROM eclipse-temurin:11-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["java", "-jar", "/app.jar"]
