# Camunda BPM

### Getting Started
---
#### Quick Start
To get setup for local camunda bpm development:

1)  Verify you have [Maven](https://maven.apache.org/download.cgi) installed on your computer 
    ```shell script
      docker run \
        -e ARTIFACT_ID=yourArtifactId \
        -e EE_USERNAME=eeUsername \
        -e EE_PASSWORD=eePassword \
        -v $(pwd):/app/src \
      rwdavisx/camunda-ee-starter:latest
    ```

2) Open your project with IntelliJ and run the starter application by clicking the
    start button next to the main method in the SpringBootApp class. This should start a
    local BPM instance that runs on the default port [http://localhost:8443/](http://localhost:8443/).
    The default login credentials are Username: demo / Password: demo
    
3) Complete the **After Startup** part of the licensing section below.

4) You should now have a fully unlocked local instance of Camunda enterprise running.

You should now have a running BPM application with some demonstration content to reference
as a starting point. You can browse through the provided code to see how everything works together
and start creating your own content to build out new BPM implementations.

### Developing New BPM Processes
---
Head over to the [Camunda Docs](https://docs.camunda.org/manual/latest/) to learn how to get started developing
your own bpm processes.

Download the [Camunda Modeller](https://camunda.com/download/modeler/) in order to easily create or
modify bpmn files.

Open the provided *WelcomeProcess.bpmn* for a reference as to how code integrates with the modeled process.

### Deploy with Docker
---
Go to the root of your application:
```bash
docker-compose build
```

This will build a containerized image of your application.

To start the container
```bash
docker-compose up
```

An easy way to use a persistent database with your camunda instance locally
is to use a containerized database that is supported by Camunda.
> For example with postgres, you can uncomment the postgres section in docker-compose to start it up.
> *ProcessEngineConfiguration.groovy* should be modified to point to this database.

### Camunda Enterprise Edition Licensing
---
In order to use the Camunda Enterprise webapp, a license key is required.

Please use the following license key to enable enterprise features of the Camunda BPM platform:

--------------- BEGIN CAMUNDA BPM LICENSE KEY ---------------
KdlmYkgXpsXEIxhQwycBD4VMORQye5Em41SMU4cehS3RWNTDs74PN7SAEY1NN
m6Zb2GF1MlYGNxp9xo64Ed5oFsHuPKqKVI8/dx6GPM0MFdoHKY+3YFkZ1YiKu
sPjDxpku53lt0K6SXCtxA7i7nQOoG+VFYjH5vmbcB9y1w//uo=; Indiana Farm Bureau
Insurance;unlimited
--------------- END CAMUNDA BPM LICENSE KEY---------------

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
---
Tests can be written for delegated code and bpmn processes separately. This is important
as it allows for developers to test each domain in isolation. Application code should work
independently from process models, and vice versa. Testing these domains separately enforces
bpm best practice by kepping the implementation logic out of bpmn models. Through this process,
robust bpm applications can be created and developers will be reliably informed if a code change
breaks any piece of the system.

For your reference, look at the difference between the tests located in *WelcomeProcessTest.groovy*
and *WelcomeDelegateTest.groovy*

> Note: You can not test timer events because of a Camunda limitation.
> Write tests around timers if you need to test a process with a timer.