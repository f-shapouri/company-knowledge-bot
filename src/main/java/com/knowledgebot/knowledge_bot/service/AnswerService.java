package com.knowledgebot.knowledge_bot.service;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final DocumentRetriever retriever;

    public AnswerService(DocumentRetriever retriever) {
        this.retriever = retriever;
    }

    public String getAnswer(String question) {
        var lines = retriever.findRelevantLines(question.toLowerCase());
        if (lines.isEmpty()) {
            return "Sorry, I could not find an answer to your question.";
        }
        return String.join("\n", lines);
    }
}