package com.junit.agent; // This matches your folder structure in the screenshot

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import dev.langchain4j.model.openai.OpenAiChatModel;

@SpringBootApplication
@RestController
public class TestAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAgentApplication.class, args);
    }

    @PostMapping("/generate-test")
    public String generateTest(@RequestBody java.util.Map<String, String> payload) {
        try {
            String userCode = payload.get("code");

            // Use the builder to specify gpt-4o-mini (it is faster and more reliable for demos)
            OpenAiChatModel model = OpenAiChatModel.builder()
                    .apiKey("demo") // Or your real key: System.getenv("OPENAI_API_KEY")
                    .modelName("gpt-4o-mini")
                    .logRequests(true) // This helps you see the actual error in console
                    .logResponses(true)
                    .build();

            return model.generate("Create a JUnit 5 test class for this code:\n\n" + userCode);
        } catch (Exception e) {
            e.printStackTrace(); // This prints the EXACT error to your IntelliJ console
            return "Server Error: " + e.getMessage();
        }
    }
}