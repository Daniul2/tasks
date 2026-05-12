package com.example.news.data;

import com.example.news.core.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Long id(Long id);
}
