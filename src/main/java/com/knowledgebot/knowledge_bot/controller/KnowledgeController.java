    package com.knowledgebot.knowledge_bot.controller;

    import com.knowledgebot.knowledge_bot.service.AnswerService;
    import com.knowledgebot.knowledge_bot.service.DocumentRetriever;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.Map;

    @RestController
    @RequestMapping("/api")
    public class KnowledgeController {

        private final AnswerService answerService;

        public KnowledgeController(AnswerService answerService) {
            this.answerService = answerService;
        }

        @GetMapping("/ask")
        public Map<String, String> ask(@RequestParam String question) {
            var answer =answerService.getAnswer(question.toLowerCase());
            var response = new HashMap<String, String>();
            response.put("question", question);
            response.put("answer", answer);
            return response;
        }
    }
