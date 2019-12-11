package ${groupId}.${rootArtifactId}.bpm.process.welcome;

import org.camunda.bpm.engine.delegate.VariableScope;

public class WelcomeProcessVariables {

    public VariableScope variableScope;

    public WelcomeProcessVariables(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public String getWelcomeMessage() {
        return (String) variableScope.getVariable(WelcomeProcessConstants.VAR_WELCOMEMESSAGE);
    }
}
