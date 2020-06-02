#
# Installation Image
#
FROM maven:latest AS run
COPY . /tmp
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml clean install
ENTRYPOINT ["/tmp/camunda-setup.sh"]