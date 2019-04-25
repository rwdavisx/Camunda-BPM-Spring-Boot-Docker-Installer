package com.infarmbureau.${rootArtifactId}.bpm.process.welcome.delegate

import com.infarmbureau.${rootArtifactId}.bpm.service.welcome.WelcomeServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import static org.junit.Assert.*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [WelcomeDelegate.class, WelcomeServiceImpl.class])
class WelcomeDelegateTest {

    @Autowired
    private WelcomeDelegate welcomeDelegate

    @Test
    void WelcomeDelegateMainTest() {
        String message = "Hello World!"
        Map<String, Object> expectedVariables
        Map<String, Object> outputVariables

        outputVariables = welcomeDelegate.showMessage(message)
        assertTrue(outputVariables == expectedVariables)
    }

    @Test
    void PoliciesFoundTest() {

    }
}