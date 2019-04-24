package ${groupId}.${rootArtifactId}.bpm.process.welcome.delegate
import com.infarmbureau.example.bpm.process.welcome.WelcomeProcessVariables
import org.camunda.bpm.engine.delegate.BpmnError
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WelcomeDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.infarmbureau.example.bpm.process.welcome.delegate.WelcomeDelegate.class)

    private Map<String, Object> outputVariables

    @Override
    void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Entering ${execution.processDefinitionId}")
        WelcomeProcessVariables vars = new WelcomeProcessVariables(execution)
        String message = vars.getWelcomeMessage()
        try {
            outputVariables = showMessage(message)
            execution.setVariables(outputVariables)
        } catch (Exception e) {
            throw new BpmnError("Generic Exception")
        }
        LOGGER.info("Exiting ${execution.processDefinitionId}")
    }

    Map<String, Object> showMessage(String message) {
        println "${message}"
        return [sentMessage: true]
    }
}