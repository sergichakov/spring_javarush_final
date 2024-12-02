FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
#"/home/user/IdeaProjects/spring_javarush_final/spring_javarush_final/target/jira-1.0.jar"
#target/*.jar
COPY ${JAR_FILE} /ROOT.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/ROOT.jar"]