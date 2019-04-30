#
# Build stage
#
FROM maven:slim AS build
ARG environment
ENV ee_username=indiana_farm_bureau_insurance
ENV ee_password=be3c8276-3523-48a5-9
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app/${artifactId}
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