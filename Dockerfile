# Clone the repository
FROM alpine/git as clone

WORKDIR /app
RUN git clone https://bitbucket.infarmbureau.com/scm/icaf/camunda-starter.git && \
	cd camunda-starter && \
	git checkout master

# Install the Archetype
FROM maven:latest as install

WORKDIR /app
COPY --from=clone /app/camunda-starter /app
RUN mvn clean install
RUN cd .. && \
    rm -r /app/*
COPY camunda-setup.sh ./
RUN ["chmod", "+x", "./camunda-setup.sh"]
ENTRYPOINT ["./camunda-setup.sh"]