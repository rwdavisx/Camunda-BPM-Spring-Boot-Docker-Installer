package ${groupId}.${rootArtifactId}.bpm.process.example.delegate;

import ${groupId}.${rootArtifactId}.bpm.service.example.ExampleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExampleDelegate.class, ExampleServiceImpl.class})
public class ExampleDelegateTest {

    @Autowired
    private ExampleDelegate exampleDelegate;

    @Test
    public void ExampleDelegateDefaultTest() {
        String message = "Hello World!";
        Map<String, Object> expectedVariables = new HashMap<String, Object>();
        expectedVariables.put("sentMessage", true);

        Map<String, Object> outputVariables;
        outputVariables = exampleDelegate.showMessage(message);

        assertEquals(outputVariables, expectedVariables);
    }
}