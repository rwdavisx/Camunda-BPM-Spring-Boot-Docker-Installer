package ${groupId}.${rootArtifactId}.bpm.service.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ExampleServiceImplTest {

    @Autowired
    private ExampleService exampleService;

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @Test
    public void printMessageTest() {
        final String message = "Message Test";

        System.setOut(new PrintStream(byteArrayOutputStream));
        exampleService.printMessage(message);
        assertEquals(message, byteArrayOutputStream.toString().trim());
    }
}
