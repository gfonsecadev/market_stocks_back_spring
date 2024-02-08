FROM ubuntu:latest AS buid

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-ea-10-jdk-slim

EXPOSE 8080

COPY  --from=buid /target/*.jar  app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]
