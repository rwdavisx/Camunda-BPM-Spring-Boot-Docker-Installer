package ${groupId}.${rootArtifactId}.bpm.util

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables
import org.camunda.bpm.engine.task.Task
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration
import org.junit.Assert
import org.junit.Rule
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class TestProcessInstance {

    @Rule
    public final ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule()

    String processId

    private ProcessInstanceWithVariables processInstanceWithVariables

    ProcessInstanceWithVariables getProcessInstance() {
        return processInstanceWithVariables
    }

    RuntimeService getRuntimeService() {
        return processEngineRule.processEngine.runtimeService
    }

    TaskService getTaskService() {
        return processEngineRule.processEngine.taskService
    }

    TestProcessInstance setGlobalVariable(String name, Object value) {
        runtimeService.setVariable(name, "", value)
        return this
    }

    TestProcessInstance setGlobalVariables(Map<String, Object> variables) {
        for (variable in variables) {
            setGlobalVariable(variable.key, variable.value)
        }
        return this
    }

    TestProcessInstance setLocalVariable(String name, Object value) {
        runtimeService.setVariableLocal(name, name, value)
        return this
    }

    TestProcessInstance setLocalVariables(Map<String, Object> variables) {
        for (variable in variables) {
            setLocalVariable(variable.key, variable.value)
        }
        return this
    }

    TestProcessInstance startProcessWithVariablesAndValidateExists(String processId, Map<String, Object> variables = null) {
        this.processId = processId
        this.processInstanceWithVariables = (ProcessInstanceWithVariables) runtimeService.startProcessInstanceByKey(processId, variables)
        Assert.assertTrue(runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.id).singleResult().id == processInstance.id)
        validateVariablesExist(variables)
        return this
    }

    TestProcessInstance validateAndCompleteUserTasksWithVariables(List<String> userTaskIds = null, Map<String, Object> variables = null) {
        List<Task> tasks = taskService.createTaskQuery().list()
        Assert.assertTrue(tasks.size() == userTaskIds.size())
        tasks.each {
            Task t ->
                Assert.assertTrue(userTaskIds.contains(t.taskDefinitionKey))
                taskService.complete(t.getId(), variables)
        }
        return this
    }

    TestProcessInstance validateVariablesExist(Map<String, Object> variables) {
        Map<String, Object> vars = processInstanceWithVariables.variables
        variables.each {
            v ->
                Assert.assertTrue(vars.containsKey(v.key))
        }
        return this
    }

    TestProcessInstance validateIsEnded() {
        Assert.assertTrue(runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.id).singleResult() == null)
        return this
    }
}
