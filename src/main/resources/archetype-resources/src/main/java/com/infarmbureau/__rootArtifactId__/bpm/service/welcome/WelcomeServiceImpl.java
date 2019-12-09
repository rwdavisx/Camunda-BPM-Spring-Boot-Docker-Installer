package com.infarmbureau.${rootArtifactId}.bpm.service.welcome;

import org.springframework.stereotype.Service;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    public void printMessage(String message) {
        System.out.println(message);
    }
}