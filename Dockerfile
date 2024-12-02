FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /ROOT.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/ROOT.jar"]