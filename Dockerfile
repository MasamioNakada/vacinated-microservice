FROM openjdk:11.0-jdk-slim-stretch

RUN addgroup -S spring && adduser -S spring -G spring 

USER spring:spring

ARG JAR_FILE=build\libs\demo-0.0.1-SNAPSHOT.jar

COPY  ${JAR_FILE} demo-0.0.1-SNAPSHOT.jar


ENTRYPOINT [ "java","-jar","/demo-0.0.1-SNAPSHOT.jar" ]