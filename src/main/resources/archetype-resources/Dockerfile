#
# Build stage
#
ARG environment
FROM maven:slim AS build
WORKDIR /src/app
COPY pom.xml pom.xml
COPY . .
COPY settings.xml /root/.m2/settings.xml
RUN mvn clean package -P $environment

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /src/app/${artifactId}/target/${artifactId}-${version}.jar /dist/app.jar
EXPOSE 8080
EXPOSE 8443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/dist/app.jar"]