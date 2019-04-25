package com.infarmbureau.${rootArtifactId}.bpm.service.welcome

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import static org.junit.Assert.assertEquals

@RunWith(SpringRunner.class)
@SpringBootTest()
class WelcomeServiceImplTest {

    @Autowired
    private WelcomeService welcomeService

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()

    @Test
    void printMessageTest() {
        final String message = "Message Test"

        System.setOut(new PrintStream(byteArrayOutputStream))
        welcomeService.printMessage(message)
        assertEquals(message, byteArrayOutputStream.toString().trim())
    }
}
