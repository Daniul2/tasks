package com.example.news.integration;

import com.example.news.core.model.Article;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class ExternalNewsClient {

    public List<Article> fetchLatest() {
        return List.of(
                new Article("External news 1", "Content 1", "ExternalAPI", LocalDateTime.now()),
                new Article("External news 2", "Content 2", "ExternalAPI", LocalDateTime.now())
        );
    }
}
