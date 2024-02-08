FROM ubuntu:latest AS buid

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean instal -DskipTests

FROM openjdk:17

EXPOSE 8080

COPY  --from=buid /target/spring_angular_backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]
