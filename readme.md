#Camunda BPM

##Quick Start
###Installation

1)  Verify you have [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html), [Maven](https://maven.apache.org/download.cgi), and [Docker](https://www.docker.com/products/docker-desktop) installed on your computer. 

2)  Run this Docker command from a directory you want to install the camunda-starter project
    ```shell script
    docker run \
      -e ARTIFACT_ID=yourArtifactId \
      -v $(pwd):/app/src \
    890342892283.dkr.ecr.us-east-1.amazonaws.com/camunda-starter:latest
    ```
    > Contact the Enterprise Architecture team for Camunda Enterprise Edition Credentials

3)  Run the application in IntelliJ by clicking the start button next to the main method 
    in the SpringBootApp class. This should create a new run configuration and start a 
    local BPM instance you can view with [Camunda Cockpit](http://localhost:8443/). 
    
    > Default Camunda Cockpit login credentials 
    Username: demo | Password: demo

###Developing BPM Processes
You should now have a running BPM application with some demonstration content to reference
as a starting point. You can browse through the provided code to see how everything connects together
and start creating your own BPM processes. Open the provided **WelcomeProcess.bpmn** for a reference 
on how code integrates with a modeled process.

Head over to the [Camunda Docs](https://docs.camunda.org/manual/latest/) to learn how to get started 
developing your own bpm processes. You can download the [Camunda Modeller](https://camunda.com/download/modeler/) 
in order to easily create or modify bpmn files.

##Deploying with Docker
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
> *ProcessEngineConfiguration.java* should be modified to point to this database.

## Testing
Tests can be written for delegated code and bpmn processes separately. This is important
as it allows for developers to test each type of file in isolation. Application code should work
independently from process models, and vice versa. Testing these domains separately enforces
bpm best practice by keeping the implementation logic of processes out of the bpmn models. Through 
this practice, robust BPM applications can be built and developers will be confident in knowing if
a code change will break any piece of the system.

For reference, look at the difference between the tests located in *WelcomeProcessTest.java*
and *WelcomeDelegateTest.java*

> Note: You can not test timer events because of a Camunda limitation.
> Write tests around timers if you need to test a process with a timer.