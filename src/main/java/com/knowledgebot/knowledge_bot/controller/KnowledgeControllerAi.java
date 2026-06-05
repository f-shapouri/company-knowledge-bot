package com.knowledgebot.knowledge_bot.controller;

import com.knowledgebot.knowledge_bot.serviceAi.RagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-ai")
public class KnowledgeControllerAi {
    private final RagService ragService;

    public KnowledgeControllerAi(RagService ragService){
        this.ragService = ragService;
    }

    @GetMapping("/ask")
    public Map<String,String> ask(@RequestParam String question){
        var answer = ragService.ask(question);
        var response = new HashMap<String,String>();
        response.put("question",question);
        response.put("answer",answer);
        return response;
    }
}
