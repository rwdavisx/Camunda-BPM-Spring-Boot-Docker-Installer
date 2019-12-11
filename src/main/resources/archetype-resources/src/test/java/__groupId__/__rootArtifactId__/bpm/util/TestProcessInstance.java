package ${groupId}.${rootArtifactId}.bpm.util;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Assert;
import org.junit.Rule;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class TestProcessInstance {

    @Rule
    public final ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule();

    String processId;

    private ProcessInstanceWithVariables processInstanceWithVariables;

    public ProcessInstanceWithVariables getProcessInstance() {
        return processInstanceWithVariables;
    }

    public RuntimeService getRuntimeService() {
        return processEngineRule.getProcessEngine().getRuntimeService();
    }

    public TaskService getTaskService() {
        return processEngineRule.getProcessEngine().getTaskService();
    }

    public TestProcessInstance setGlobalVariable(String name, Object value) {
        getRuntimeService().setVariable(name, "", value);
        return this;
    }

    public TestProcessInstance setGlobalVariables(Map<String, Object> variables) {
        for (Map.Entry<String, Object> variable:variables.entrySet()) {
            setGlobalVariable(variable.getKey(), variable.getValue());
        }
        return this;
    }

    public TestProcessInstance setLocalVariable(String name, Object value) {
        getRuntimeService().setVariableLocal(name, name, value);
        return this;
    }

    public TestProcessInstance setLocalVariables(Map<String, Object> variables) {
        for (Map.Entry<String, Object> variable:variables.entrySet()) {
            setLocalVariable(variable.getKey(), variable.getValue());
        }
        return this;
    }

    public TestProcessInstance startProcessWithVariablesAndValidateExists(String processId, Map<String, Object> variables) {
        this.processId = processId;
        this.processInstanceWithVariables = (ProcessInstanceWithVariables) getRuntimeService().startProcessInstanceByKey(processId, variables);
        Assert.assertTrue(getRuntimeService().createProcessInstanceQuery().processInstanceId(getProcessInstance().getId()).singleResult().getId().equals(getProcessInstance().getId()));
        validateVariablesExist(variables);
        return this;
    }

    public TestProcessInstance validateAndCompleteUserTasksWithVariables(List<String> userTaskIds, Map<String, Object> variables) {
        List<Task> tasks = getTaskService().createTaskQuery().list();
        Assert.assertTrue(tasks.size() == userTaskIds.size());
        for (Task t: tasks) {
            Assert.assertTrue(userTaskIds.contains(t.getTaskDefinitionKey()));
            getTaskService().complete(t.getId(), variables);
        }
        return this;
    }

    public TestProcessInstance validateVariablesExist(Map<String, Object> variables) {
        Map<String, Object> vars = processInstanceWithVariables.getVariables();
        for (Map.Entry<String, Object> variable:variables.entrySet()) {
            Assert.assertTrue(vars.containsKey(variable.getKey()));
        }
        return this;
    }

    public TestProcessInstance validateIsEnded() {
        Assert.assertTrue(getRuntimeService().createProcessInstanceQuery().processInstanceId(getProcessInstance().getId()).singleResult() == null);
        return this;
    }
}
