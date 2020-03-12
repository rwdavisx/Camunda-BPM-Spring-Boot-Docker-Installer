package ${groupId}.${rootArtifactId}.bpm.process.example;

import ${groupId}.${rootArtifactId}.bpm.service.example.ExampleService;
import ${groupId}.${rootArtifactId}.bpm.util.TestProcessInstance;
import ${groupId}.${rootArtifactId}.bpm.process.example.delegate.ExampleDelegate;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ${groupId}.${rootArtifactId}.bpm.process.example.ExampleProcessConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExampleProcessTest.class, TestProcessInstance.class, ExampleDelegate.class})
@Deployment(resources = {"${groupId}/${rootArtifactId}/bpm/process/example/ExampleProcess.bpmn"})
public class ExampleProcessTest {

    @Rule
    public ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule();

    @Autowired
    public TestProcessInstance testProcessInstance;

    @MockBean
    private ExampleService exampleService;

    @SpyBean
    private ExampleDelegate exampleDelegate;

    @Before
    public void setup() {
        Mocks.getMocks().put("exampleDelegate", exampleDelegate);
    }

    @After
    public void teardown() {
        Mocks.reset();
    }

    @Test
    public void exampleProcessDefaultFlowTest() {
        Map<String, Object> startVariables = new HashMap<String, Object>();
        startVariables.put("defaultFlow", true);
        Map<String, Object> exampleDelegateOutput = new HashMap<String, Object>();
        exampleDelegateOutput.put("exampleVar1", 1337);
        exampleDelegateOutput.put("exampleVar2", true);
        doReturn(exampleDelegateOutput).when(exampleDelegate).showMessage((String) any());
        List<String> userTaskIds = new LinkedList<String>();
        userTaskIds.add(USERTASK_DEFAULT);

        testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_EXAMPLE, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds, new HashMap<String, Object>())
                .validateVariablesExist(exampleDelegateOutput)
                .validateIsEnded();
    }

    @Test
    public void exampleProcessSecondaryFlowTest() {
        Map<String, Object> startVariables = new HashMap<String, Object>();
        startVariables.put("defaultFlow", false);
        Map<String, Object> exampleDelegateOutput = new HashMap<String, Object>();
        exampleDelegateOutput.put("exampleVar1", 1337);
        exampleDelegateOutput.put("exampleVar2", true);
        doReturn(exampleDelegateOutput).when(exampleDelegate).showMessage((String) any());
        List<String> userTaskIds = new LinkedList<String>();
        userTaskIds.add(USERTASK_SECONDARY);

        testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_EXAMPLE, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds, new HashMap<String, Object>())
                .validateVariablesExist(exampleDelegateOutput)
                .validateIsEnded();
    }
}