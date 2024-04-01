FROM maven:3.9.6-amazoncorretto-11
COPY /target/air-manager-docker.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]