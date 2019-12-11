# Camunda BPM

## Quick Start
### Installation

1)  Verify you have [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html), [Maven](https://maven.apache.org/download.cgi), and [Docker](https://www.docker.com/products/docker-desktop) installed on your computer. 

2)  Run this Docker command from a directory you want to install the camunda-starter project
    ```shell script
    docker run \
      -e ARTIFACT_ID=HelloWorld \
      -v $(pwd):/tmp \
    890342892283.dkr.ecr.us-east-1.amazonaws.com/camunda-starter:latest
    ```
    
3)  Package and run your project as a Docker container by running this command from the project root
    ```shell script
    docker build . -t camunda-starter && \
    docker run -p 8080:8080 -p 8443:8443 --name camunda camunda-starter
    ```
    The first command will create an image of your project
    The second command will create a running instance, ie "container", of that image
    This container will then expose [Camunda Cockpit](http://localhost:8443/)
   
    > Default Camunda Cockpit login credentials 
    Username: demo | Password: demo
    
    To shut the container down run 
    ```shell script
    docker container stop camunda
    ```

### Developing BPM Processes
You should now have a running BPM application with some demonstration content to reference
as a starting point. You can browse through the provided code to see how everything connects together
and start creating your own BPM processes. Open the provided **WelcomeProcess.bpmn** for a reference 
on how code integrates with a modeled process.

Head over to the [Camunda Docs](https://docs.camunda.org/manual/latest/) to learn how to get started 
developing your own bpm processes. You can download the [Camunda Modeller](https://camunda.com/download/modeler/) 
in order to easily create or modify bpmn files.

## External Databases
Go to the root of your application:
```bash
docker-compose build
```

This will rebuild your application's docker image.

To start the preconfigured docker services
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