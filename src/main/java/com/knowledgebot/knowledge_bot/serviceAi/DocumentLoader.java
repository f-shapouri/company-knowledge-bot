package com.knowledgebot.knowledge_bot.serviceAi;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class DocumentLoader {

    private static final String DOCS_PATH = "docs/";

    public String getRelevantContext(String question) {
        String[] keywords = question.toLowerCase().split("\\s+");

        try {
            File docsFolder = new File(DOCS_PATH);
            File[] files = docsFolder.listFiles((dir, name) ->
                    name.endsWith(".txt") || name.endsWith(".md"));

            if (files == null || files.length == 0) {
                return "No documents found.";
            }

            int bestScore = 0;
            StringBuilder bestContext = new StringBuilder();

            for (File file : files) {
                List<String> lines = Files.readAllLines(file.toPath());
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i).toLowerCase();
                    int score = 0;
                    for (String keyword : keywords) {
                        if (keyword.length() >= 3 && line.contains(keyword)) {
                            score++;
                        }
                    }
                    if (score > bestScore) {
                        bestScore = score;
                        bestContext = new StringBuilder();
                        bestContext.append("--- ").append(file.getName()).append(" ---\n");
                        for (int j = Math.max(0, i - 1); j < Math.min(i + 6, lines.size()); j++) {
                            bestContext.append(lines.get(j)).append("\n");
                        }
                    }
                }
            }

            return bestScore > 0 ? bestContext.toString() : "No relevant information found.";

        } catch (Exception e) {
            return "Error reading documents: " + e.getMessage();
        }
    }
}
