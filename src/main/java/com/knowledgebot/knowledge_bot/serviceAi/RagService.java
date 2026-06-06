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

    @Value("${ollama.api.url:http://localhost:11434/api/chat}")
    private String ollamaUrl;

    @Value("${ollama.model:llama3.2:3b}")
    private String ollamaModel;

    private final DocumentLoader loader;
    private final RestTemplate restTemplate;

    public RagService(DocumentLoader loader) {
        this.loader = loader;
        this.restTemplate = new RestTemplate();
    }

    public String ask(String question) {
        var context = loader.getRelevantContext(question);
        var prompt = String.format(
                "You are a company knowledge assistant. " +
                        "Read ALL the context carefully before answering. " +
                        "The context may contain information from multiple documents. " +
                        "Find the most relevant answer from the context. " +
                        "Be direct and specific. " +
                        "If the answer is not explicitly in the context, respond ONLY with: " +
                        "'I don't know based on available documents.' Do not guess or assume.\n\n" +
                        "CONTEXT:\n%s\n\n" +
                        "QUESTION: %s\n\n" +
                        "ANSWER:", context, question);
        return callOpenAi(prompt);
    }

    private String callOpenAi(String prompt) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = Map.of(
                    "model", ollamaModel,
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)
                    ),
                    "stream", false
            );

            var entity = new HttpEntity<>(requestBody, headers);
            var response = restTemplate.postForEntity(ollamaUrl, entity, Map.class);

            if (response.getBody() != null) {
                var message = (Map<String, Object>) response.getBody().get("message");
                return (String) message.get("content");
            }
            return "No response from Ollama";
        } catch (Exception e) {
            return "Error calling Ollama: " + e.getMessage();
        }
    }

}
