package com.infarmbureau.example.bpm.process.welcome

import org.camunda.bpm.engine.delegate.VariableScope

class WelcomeProcessVariables {

    private VariableScope variableScope

    WelcomeProcessVariables(VariableScope variableScope) {
        this.variableScope = variableScope
    }

    String getWelcomeMessage() {
        return (String) variableScope.getVariable(WelcomeProcessConstants.VAR_WELCOMEMESSAGE)
    }
}
