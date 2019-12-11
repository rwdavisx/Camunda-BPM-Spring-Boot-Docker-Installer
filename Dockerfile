#
# Packaging stage
#
FROM maven:3.6.3-ibmjava-8-alpine AS pack
COPY . /tmp
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml install

#
# Run stage
#
FROM maven:3.6.3-ibmjava-8-alpine AS run
COPY camunda-setup.sh /tmp/camunda-setup.sh
COPY --from=pack /usr/share/maven/ref/repository /usr/share/maven/ref/repository
ENTRYPOINT ["/tmp/camunda-setup.sh"]