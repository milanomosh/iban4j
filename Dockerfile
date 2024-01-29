FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/iban4j-3.2.7-RELEASE.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]