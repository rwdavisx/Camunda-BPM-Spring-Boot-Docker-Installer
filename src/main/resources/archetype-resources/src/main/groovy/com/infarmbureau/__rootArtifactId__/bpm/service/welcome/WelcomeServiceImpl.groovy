package com.infarmbureau.${rootArtifactId}.bpm.service.welcome

import org.springframework.stereotype.Service

@Service
class WelcomeServiceImpl implements WelcomeService {
    void printMessage(String message) {
        println message
    }
}