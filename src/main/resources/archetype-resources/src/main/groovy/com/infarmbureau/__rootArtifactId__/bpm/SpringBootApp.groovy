package ${groupId}.${rootArtifactId}.bpm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = "${groupId}")
class SpringBootApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApp.class)

    static void main(String... args) {
        SpringApplication.run(SpringBootApp.class, args)
        LOGGER.info("Camunda App Started!")
    }
}
