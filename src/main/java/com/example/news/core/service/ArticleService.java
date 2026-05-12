package com.example.news.core.service;

import com.example.news.core.model.Article;
import com.example.news.data.ArticleRepository;
import com.example.news.integration.ExternalNewsClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ExternalNewsClient externalNewsClient;

    public ArticleService(ArticleRepository articleRepository,
                          ExternalNewsClient externalNewsClient) {
        this.articleRepository = articleRepository;
        this.externalNewsClient = externalNewsClient;
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found: " + id));
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> syncFromExternal() {
        List<Article> external = externalNewsClient.fetchLatest();
        return articleRepository.saveAll(external);
    }
}
