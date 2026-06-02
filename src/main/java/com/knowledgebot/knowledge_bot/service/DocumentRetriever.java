package com.knowledgebot.knowledge_bot.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentRetriever {

    private static final String DOCS_PATH = "docs/";

    public List<String> findRelevantLines(String question) {
        var results = new ArrayList<String>();
        try {
            var docsFolder = new File(DOCS_PATH);
            var files = docsFolder.listFiles(
                    (dir, name) -> name.endsWith(".txt") || name.endsWith(".md")
            );

            if (files == null || files.length == 0) {
                results.add("No documents found");
                return results;
            }

            var normalizedQuestion = question.toLowerCase();
            var keywords = normalizedQuestion.split("\\s+");

            for (File file : files) {
                var lines = Files.readAllLines(file.toPath());
                for (int i = 0; i < lines.size(); i++) {
                    var line = lines.get(i).toLowerCase();
                    var matchScore = 0;

                    for (String keyword : keywords) {
                        if (keyword.length() < 4) {
                            continue;
                        }
                        if (line.contains(keyword)) {
                            matchScore++;
                        }
                    }
                    if (matchScore > 0) {
                        for (int j = i; j < Math.min(i + 4, lines.size()); j++) {
                            results.add(lines.get(j));
                        }
                        return results;
                    }
                }
            }
            results.add("No relevant information found.");
        } catch (Exception e) {
            results.add("Error reading documents: " + e.getMessage());
        }
        return results;
    }
}