# Camunda BPM

### Getting Started
---
#### Quick Setup
To get setup for local camunda bpm development:

1) Verify you have [Maven](https://maven.apache.org/download.cgi) installed on your computer 
    ```bash
    mvn -v
    ```
2) Complete the [before running camunda](#### Before running Camunda) section of the camunda licensing section.

3)  Run the following command
    ```bash
    git clone ssh://git@bitbucket.infarmbureau.com:7999/icaf/camunda-bpm.git
    cd camunda-bpm
    mvn clean install
    ```

After completing these steps, a new camunda workspace should exist on your local machine.
This project provides a bootstrapped starter project which can be used to quickly develop
a new BPM application from and an example project for your reference while you develop.

#### Running
To run either the starter or example project:

##### IntelliJ IDE
1) Install the IntelliJ maven plugin if you don't already have it.
2) Select any applicable maven profiles you want applied to your application from the maven plugin toolbar.
3) Setup a Spring Boot run configuration for the main class in the "Edit Configurations.." toolbar.
4) Run the configuration you have set up.
5) Navigate to [http://localhost:8080/](http://localhost:8080/) in your browser to see the camunda webapp.
By default, the login credentials are set to Username: demo / Password: demo
6) Complete the [after startup](#### After Startup) part of the licensing section.
7) You should now have a fully unlocked local instance of Camunda enterprise running.

### IFBI Camunda Core
---
The Camunda Core artifact provided by IFBI is meant to abstract away Camunda's tricky implementation details
and complex project architecture needs in order to facilitate faster bpm process development.

By including this artifact in your spring boot application, most of the camunda related setup and configuration is
taken care of. This makes it easy to jump right in and start creating new bpm processes. 

This artifact also contains a REST controller we refer to as the "Gateway" which essentially exposes your Spring Boot
BPM application to a rest or front end client via a few REST APIs.

#### Gateway
The endpoints currently exposed via the gateway are:

##### Start
    POST: /start
    
    body: {
        "processKey": "exampleProcess",
	    "variables": {
            "var": 12345
        }
    }
##### Status
    GET: /status/{id}
##### Complete
    POST: /complete/{id}
##### Terminate
    DELETE: /terminate/{id}

### Developing a BPM Process
---
Head over to the [Camunda Docs](https://docs.camunda.org/manual/latest/) to learn how to get started developing
your own bpm processes.

Download the [Camunda Modeller](https://camunda.com/download/modeler/) in order to easily create or
modify bpmn files.

Browse through the codebase of the example project to get a better understanding of how bpmn
applications come together.


### Deployments
---
Developing with Spring Boot allows you to have easy deployments.
- No build tools required
- No setup
- No web server configuration

#### CLI
Simply,
```bash
mvn clean install
java -jar [app].jar --spring.config.location=file:/config/,file:/
```
This will automatically pick up any *.properties files placed in the directory this is ran from.

#### Docker
A maven plugin is preconfigured to build a docker image with your application.

Go to the root of your application in the terminal:
```bash
mvn clean package docker:build
```

This will build a new docker image with the name defined by
```xml
<imageName>ifbi-example-bpm</imageName>
```
in your projects pom.xml inside the docker-maven-plugin configuration.

To run the container
```bash
docker run -p 8080:8080 -p 8443:8443 \
-v ~/Desktop/application.properties:/application.properties \
ifbi-example-bpm
```
where *~/Desktop/application.properties* is the path of a properties file you want applied at runtime.

> An easy way to run a persistent database locally is to use docker to run a database
> which is supported by Camunda and apply env variables on startup. 
> 
> For example with postgres:
> ```bash
> docker run --name postgres -d -p 5432:5432 -e POSTGRES_USER=camunda -e POSTGRES_PASSWORD=camunda_password postgres
> ```

### Camunda Enterprise Edition Licensing
---
#### Before running Camunda
To get Camunda Enterprise working locally, be sure to add the following 
configuration to your ~/.m2/settings.xml
```xml
<settings>
  <servers>
    <server>
      <id>camunda-bpm-nexus-ee</id>
      <username>indiana_farm_bureau_insurance</username>
      <password>be3c8276-3523-48a5-9</password>
    </server>
  </servers>
</settings>
```
> This step is required *before* running `mvn install` in order authenticate
> and pull the correct version of camunda from a private repository.

#### After Startup
In order to use the Camunda Enterprise webapp, a license key is required.

Please use the following license key to enable enterprise features of the Camunda BPM platform:
--------------- BEGIN CAMUNDA BPM LICENSE KEY ---------------
KdlmYkgXpsXEIxhQwycBD4VMORQye5Em41SMU4cehS3RWNTDs74PN7SAEY1NN
m6Zb2GF1MlYGNxp9xo64Ed5oFsHuPKqKVI8/dx6GPM0MFdoHKY+3YFkZ1YiKu
sPjDxpku53lt0K6SXCtxA7i7nQOoG+VFYjH5vmbcB9y1w//uo=; Indiana Farm Bureau
Insurance;unlimited--------------- END CAMUNDA BPM LICENSE KEY---------------

To unlock Optimize, please use the following license key:
--------------- BEGIN OPTIMIZE LICENSE KEY ---------------
gXz2RV0uvY3h1OLK8/Uu3sg8iPmWPjA0dGDRLx9HBY0rSqzjmoXJLKsvzR
QhDkBrTOVX+GO9mrHaTjS8bawIDfJwPBRd2gX3sMnYtH4jVZqYhT3UFBwN
LSOumbofwW1RNMIzBgvApCanqBVaY8XkzfxZmxpZclUqkwXiGM5Gn2cXHz
MTaNxC5ySlhimXY7u23ReyeKd1OB87K/omzIzx0o6jMzwgxZD5YP4AKQSH
hwD64XkAO7EZWNFKOKJ+e7QQ+gYdmAi7w2vhsHGvuI8Ki+gZ2EkiF98hlN
5EMVcFe9GkZe3Qd7nORlBVRXrMHt0hZjLxCjAji32RHLEnYxr7Mg==;
Indiana Farm Bureau Insurance;unlimited
---------------  END OPTIMIZE LICENSE KEY  ---------------

> These keys are tied to the lifecycle of the database you run locally.
> A persistent database will need these keys entered once, but the default (h2)
> database restarts each time camunda is ran and need to be supplied each time.

### Testing

> Note: You can not test timer events because of a Camunda limitation.
> Write tests around timers if you need to test a process with a timer.