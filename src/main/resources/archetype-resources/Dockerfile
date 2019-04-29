#
# Build stage
#
FROM maven:slim AS build
ARG ee_username
ARG ee_password
ENV ee_username=$ee_username
ENV ee_password=$ee_password
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app/${artifactId}
COPY pom.xml pom.xml
COPY . .
COPY settings.xml /root/.m2/settings.xml
RUN mvn clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#RUN mkdir -p /usr/local
COPY --from=build /usr/src/app/${artifactId}/target/${artifactId}-${version}.jar /usr/local/app.jar
EXPOSE 8443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/app.jar", "--spring.config.location=file:./config/application-default.properties,file:env.properties"]