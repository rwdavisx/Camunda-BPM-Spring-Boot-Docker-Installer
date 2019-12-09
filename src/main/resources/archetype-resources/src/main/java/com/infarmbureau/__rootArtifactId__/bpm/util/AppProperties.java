package com.infarmbureau.${rootArtifactId}.bpm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ifbi")
public class AppProperties {

    @Autowired
    Jdbc jdbc;

    @Component
    @ConfigurationProperties("ifbi.jdbc")
    static class Jdbc {

        String url;

        String username;

        String password;

        Class driver;

    }
}