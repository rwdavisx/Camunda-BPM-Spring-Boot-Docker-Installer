package com.infarmbureau.${rootArtifactId}.bpm.process.welcome.delegate;

import com.infarmbureau.${rootArtifactId}.bpm.process.welcome.WelcomeProcessVariables;
import com.infarmbureau.${rootArtifactId}.bpm.service.welcome.WelcomeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WelcomeDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeDelegate.class);

    @Autowired
    WelcomeService welcomeService;

    private Map<String, Object> outputVariables;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Entering ${execution.processDefinitionId}");
        WelcomeProcessVariables vars = new WelcomeProcessVariables(execution);
        String message = vars.getWelcomeMessage();
        try {
            outputVariables = showMessage(message);
            execution.setVariables(outputVariables);
        } catch (Exception e) {
            throw new BpmnError("Generic Exception");
        }
        LOGGER.info("Exiting ${execution.processDefinitionId}");
    }

    public Map<String, Object> showMessage(String message) {
        welcomeService.printMessage(message);
        HashMap map = new HashMap();
        map.put("sentMessage", true);
        return map;
    }
}