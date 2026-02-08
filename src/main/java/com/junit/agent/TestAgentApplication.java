package com.junit.agent;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.time.Duration;

@SpringBootApplication
@RestController
public class TestAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAgentApplication.class, args);
    }

    @PostMapping("/generate-test")
    public String generateTest(@RequestBody Map<String, String> payload) {
        try {
            // 1. Extract the code from the request
            String userCode = payload.get("code");
            if (userCode == null || userCode.isEmpty()) {
                return "Error: No code provided.";
            }

            // 2. Setup the AI Model (Using gpt-4o-mini for speed and polyglot support)
            OpenAiChatModel model = OpenAiChatModel.builder()
                    .apiKey("demo") // Replace "demo" with your real key if it hits limits
                    .modelName("gpt-4o-mini")
                    .timeout(Duration.ofSeconds(60))
                    .logRequests(true)
                    .logResponses(true)
                    .build();

            // 3. Polyglot Instructions
            String instructions = """
                You are a Developer. 
                Tasks:
                1. Identify the programming language of the code provided.
                2. Write a professional unit test suite using the most standard framework:
                   - Java -> JUnit 5 / Mockito
                   - Python -> PyTest
                   - JavaScript/TypeScript -> Jest
                   - C++ -> Google Test
                3. Include edge cases, mock dependencies if needed, and all necessary imports.
                4. Write a brief explanation (1-2 sentences) of what the test is verifying.
                5. Provide the corresponding code block.
                    Ensure the output is in Markdown format so the UI can render it clearly.
                        Code to analyze:
                """ + userCode;

            // 4. Generate and return
            return model.generate(instructions + "\n\nSource Code:\n" + userCode);

        } catch (Exception e) {
            e.printStackTrace();
            return "Server Error: " + e.getMessage();
        }
    }
}