package ${groupId}.${rootArtifactId}.bpm.process.example;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ExampleProcessVariables {

    public VariableScope variableScope;

    public ExampleProcessVariables(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public String getExampleMessage() {
        return (String) variableScope.getVariable(ExampleProcessConstants.VAR_EXAMPLEMESSAGE);
    }
}
