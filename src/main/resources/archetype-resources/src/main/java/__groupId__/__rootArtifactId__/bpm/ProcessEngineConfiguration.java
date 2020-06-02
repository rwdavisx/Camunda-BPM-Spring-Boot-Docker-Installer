package ${groupId}.${rootArtifactId}.bpm;

import ${groupId}.${rootArtifactId}.bpm.util.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProcessEngineConfiguration {
    @Autowired
    AppProperties properties;
}
