package ${groupId}.${rootArtifactId}.bpm.process.welcome.delegate;

import ${groupId}.${rootArtifactId}.bpm.process.welcome.WelcomeProcessVariables;
import ${groupId}.${rootArtifactId}.bpm.service.welcome.WelcomeService;
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
        LOGGER.info("Entering " + execution.getProcessDefinitionId());
        WelcomeProcessVariables vars = new WelcomeProcessVariables(execution);
        String message = vars.getWelcomeMessage();
        try {
            outputVariables = showMessage(message);
            execution.setVariables(outputVariables);
        } catch (Exception e) {
            throw new BpmnError("Generic Exception");
        }
        LOGGER.info("Exiting " + execution.getProcessDefinitionId());
    }

    public Map<String, Object> showMessage(String message) {
        welcomeService.printMessage(message);
        HashMap map = new HashMap();
        map.put("sentMessage", true);
        return map;
    }
}