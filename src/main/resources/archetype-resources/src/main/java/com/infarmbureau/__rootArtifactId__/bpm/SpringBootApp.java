package com.infarmbureau.${rootArtifactId}.bpm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
        LOGGER.info("Camunda App Started!");
    }
}
