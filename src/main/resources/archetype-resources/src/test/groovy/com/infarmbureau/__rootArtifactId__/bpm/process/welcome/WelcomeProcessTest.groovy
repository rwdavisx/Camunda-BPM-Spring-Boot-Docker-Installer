package com.infarmbureau.${rootArtifactId}.bpm.process.welcome

import com.infarmbureau.${rootArtifactId}.bpm.service.welcome.WelcomeService
import com.infarmbureau.${rootArtifactId}.bpm.util.TestProcessInstance
import com.infarmbureau.${rootArtifactId}.bpm.process.welcome.delegate.WelcomeDelegate
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.mock.Mocks
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.test.context.junit4.SpringRunner

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static com.infarmbureau.${rootArtifactId}.bpm.process.welcome.WelcomeProcessConstants.*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [WelcomeProcessTest.class, TestProcessInstance.class, WelcomeDelegate.class])
@Deployment(resources = ["com/infarmbureau/${rootArtifactId}/bpm/process/welcome/WelcomeProcess.bpmn"])
class WelcomeProcessTest {

    @Rule
    public ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule()

    @Autowired
    public TestProcessInstance testProcessInstance

    @MockBean
    private WelcomeService welcomeService

    @SpyBean
    private WelcomeDelegate welcomeDelegate

    @Before
    void setup() {
        Mocks.mocks.putAll([
                welcomeDelegate: welcomeDelegate,
        ])
    }

    @After
    void teardown() {
        Mocks.reset()
    }

    @Test
    void welcomeProcessDefaultFlowTest() {
        Map<String, Object> startVariables = [defaultFlow: true]
        Map<String, Object> welcomeDelegateOutput = [exampleVar1: 1337, exampleVar2: []]
        doReturn(welcomeDelegateOutput).when(welcomeDelegate).showMessage(any())
        List<String> userTaskIds = [USERTASK_DEFAULT]
        TestProcessInstance testProcessInstance = testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_WELCOME, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds)
                .validateVariablesExist(welcomeDelegateOutput)
                .validateIsEnded()
    }

    @Test
    void welcomeProcessSecondaryFlowTest() {
        Map<String, Object> startVariables = [defaultFlow: false]
        Map<String, Object> welcomeDelegateOutput = [exampleVar1: 1337, exampleVar2: []]
        doReturn(welcomeDelegateOutput).when(welcomeDelegate).showMessage(any())
        List<String> userTaskIds = [USERTASK_SECONDARY]
        TestProcessInstance testProcessInstance = testProcessInstance
                .startProcessWithVariablesAndValidateExists(PROCESSKEY_WELCOME, startVariables)
                .validateAndCompleteUserTasksWithVariables(userTaskIds)
                .validateVariablesExist(welcomeDelegateOutput)
                .validateIsEnded()
    }
}