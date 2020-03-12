package ${groupId}.${rootArtifactId}.bpm.service.example;

import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    public void printMessage(String message) {
        System.out.println(message);
    }
}