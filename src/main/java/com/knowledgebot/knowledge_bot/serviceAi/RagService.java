package com.knowledgebot.knowledge_bot.serviceAi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RagService {

    @Value("${openai.api.key:}")
    private String apiKey;

    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;

    private final DocumentLoader loader;
    private final RestTemplate restTemplate;

    public RagService(DocumentLoader loader) {
        this.loader = loader;
        this.restTemplate = new RestTemplate();
    }

    public String ask(String question){
        var context = loader.getRelevantContext(question);
        var prompt = String.format("""
                You are a company knowledge bot. Answer based ONLY on this context:
                Context: %s
                Question: %s
                            Instructions:
                            - If answer is in context, answer directly and concisely
                            - If answer is NOT in context, say exactly: "I don't know based on available documents."
                            - Do not make up information
                """,context,question);
        return callOpenAi(prompt);
    }

    private String callOpenAi(String prompt) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "OpenAI API key not configured. Please set OPENAI_API_KEY environment variable.";
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> requestBody = Map.of(
                    "model", "gpt-3.5-turbo",
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)
                    ),
                    "temperature", 0.3,
                    "max_tokens", 500
            );

            var entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
            var response = restTemplate.postForEntity(apiUrl, entity, Map.class);

            if (response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                var choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            return "Error: Could not get response from OpenAI";

        } catch (Exception e) {
            return "Error calling OpenAI API: " + e.getMessage();
        }
    }

}
