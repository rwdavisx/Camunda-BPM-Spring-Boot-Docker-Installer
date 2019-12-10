#
# Build stage
#
FROM maven:slim AS build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app/${ARTIFACT_ID}
COPY pom.xml pom.xml
COPY . .
COPY settings.xml /root/.m2/settings.xml
RUN mvn clean package -P $environment

#
# Package stage
#
FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=build /usr/src/app/${artifactId}/target/${artifactId}-${version}.jar /usr/local/app.jar
EXPOSE 8080
EXPOSE 8443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/app.jar"]