package ${groupId}.${rootArtifactId}.bpm.process.init

import org.camunda.bpm.engine.delegate.VariableScope

class InitProcessVariables {

    private VariableScope variableScope

    InitProcessVariables(VariableScope variableScope) {
        this.variableScope = variableScope
    }

    String getWelcomeMessage() {
        return (String) variableScope.getVariable(InitProcessConstants.VAR_NAME_MESSAGE)
    }
}
