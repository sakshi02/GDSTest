import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAgentController {

    @PostMapping("/generate-test")
    public String generate(@RequestBody String javaCode) {
        // This is where the Agent logic goes later
        // For now, it just says "Agent is thinking..."
        return "Agent analyzed your code: " + javaCode.length() + " characters found.";
    }
}