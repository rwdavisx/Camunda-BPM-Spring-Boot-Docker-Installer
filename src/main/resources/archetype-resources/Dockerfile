#
# Packaging stage
#
FROM maven:latest AS pack
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

#
# Build stage
#
FROM maven:latest AS build
COPY . /tmp
COPY --from=pack /usr/share/maven/ref/repository /usr/share/maven/ref/repository
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml package

#
# Dist stage
#
FROM openjdk:12
COPY --from=build /tmp/target/${artifactId}-${version}.jar /dist/app.jar
EXPOSE 8080
EXPOSE 8443
ENTRYPOINT ["java","-jar","/dist/app.jar"]