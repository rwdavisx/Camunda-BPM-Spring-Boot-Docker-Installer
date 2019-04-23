#
# Build stage
#
FROM maven:slim AS build
ARG ee_username
ARG ee_password
ENV ee_username=$ee_username
ENV ee_password=$ee_password
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY ../../../../pom.xml pom.xml
COPY ../../../.. .
COPY ../../../../settings.xml /root/.m2/settings.xml
RUN mvn -pl '!starter-business,!starter-camunda' clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#RUN mkdir -p /usr/local
COPY --from=build /usr/src/app/example-camunda/target/example-camunda-1.0.jar /usr/local/app.jar
EXPOSE 8443
#, "--spring.config.location=file:/config/,file:/"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/app.jar"]