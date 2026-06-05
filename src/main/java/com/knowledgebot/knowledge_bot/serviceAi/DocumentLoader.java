package com.knowledgebot.knowledge_bot.serviceAi;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class DocumentLoader {

    private static final String DOCS_PATH = "docs/";

    public String getRelevantContext(String question) {
        StringBuilder context = new StringBuilder();
        String[] keywords = question.toLowerCase().split("\\s+");

        try {
            File docsFolder = new File(DOCS_PATH);
            File[] files = docsFolder.listFiles((dir, name) ->
                    name.endsWith(".txt") || name.endsWith(".md"));

            if (files == null || files.length == 0) {
                return "No documents found in /docs folder.";
            }

            for (File file : files) {
                List<String> lines = Files.readAllLines(file.toPath());
                context.append("\n--- ").append(file.getName()).append(" ---\n");

                for (String line : lines) {
                    String lowerLine = line.toLowerCase();
                    boolean isRelevant = false;

                    for (String keyword : keywords) {
                        if (keyword.length() > 2 && lowerLine.contains(keyword)) {
                            isRelevant = true;
                            break;
                        }
                    }

                    if (isRelevant || context.length() < 500) {
                        context.append(line).append("\n");
                    }
                }
            }

            return context.length() > 2000 ?
                    context.substring(0, 2000) : context.toString();

        } catch (Exception e) {
            return "Error reading documents: " + e.getMessage();
        }
    }
}
