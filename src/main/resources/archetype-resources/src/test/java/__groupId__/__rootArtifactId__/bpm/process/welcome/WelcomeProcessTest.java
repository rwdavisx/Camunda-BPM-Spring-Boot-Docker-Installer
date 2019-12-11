package ${groupId}.${rootArtifactId}.bpm.process.welcome;

import ${groupId}.${rootArtifactId}.bpm.service.welcome.WelcomeService;
import ${groupId}.${rootArtifactId}.bpm.util.TestProcessInstance;
import ${groupId}.${rootArtifactId}.bpm.process.welcome.delegate.WelcomeDelegate;
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

import static ${groupId}.${rootArtifactId}.bpm.process.welcome.WelcomeProcessConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WelcomeProcessTest.class, TestProcessInstance.class, WelcomeDelegate.class})
@Deployment(resources = {"${groupId}/${rootArtifactId}/bpm/process/welcome/WelcomeProcess.bpmn"})
public class WelcomeProcessTest {

    @Rule
    public ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule();

    @Autowired
    public TestProcessInstance testProcessInstance;

    @MockBean
    private WelcomeService welcomeService;

    @SpyBean
    private WelcomeDelegate welcomeDelegate;

    @Before
    public void setup() {
        Mocks.getMocks().put("welcomeDelegate", welcomeDelegate);
    }

    @After
    public void teardown() {
        Mocks.reset();
    }

    @Test
    public void welcomeProcessDefaultFlowTest() {
        Map<String, Object> startVariables = new HashMap<String, Object>();
        startVariables.put("defaultFlow", true);
        Map<String, Object> welcomeDelegateOutput = new HashMap<String, Object>();
        welcomeDelegateOutput.put("exampleVar1", 1337);
        welcomeDelegateOutput.put("exampleVar2", true);
        doReturn(welcomeDelegateOutput).when(welcomeDelegate).showMessage((String) any());
        List<String> userTaskIds = new LinkedList<String>();
        userTaskIds.add(USERTASK_DEFAULT);

        testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_WELCOME, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds, new HashMap<String, Object>())
                .validateVariablesExist(welcomeDelegateOutput)
                .validateIsEnded();
    }

    @Test
    public void welcomeProcessSecondaryFlowTest() {
        Map<String, Object> startVariables = new HashMap<String, Object>();
        startVariables.put("defaultFlow", false);
        Map<String, Object> welcomeDelegateOutput = new HashMap<String, Object>();
        welcomeDelegateOutput.put("exampleVar1", 1337);
        welcomeDelegateOutput.put("exampleVar2", true);
        doReturn(welcomeDelegateOutput).when(welcomeDelegate).showMessage((String) any());
        List<String> userTaskIds = new LinkedList<String>();
        userTaskIds.add(USERTASK_SECONDARY);

        testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_WELCOME, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds, new HashMap<String, Object>())
                .validateVariablesExist(welcomeDelegateOutput)
                .validateIsEnded();
    }
}