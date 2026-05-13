package com.example.news.core.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 4000)
    private String content;

    private String source;

    private LocalDateTime publishedAt;

    public Article() {
    }

    public Article(String title, String content, String source, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.source = source;
        this.publishedAt = publishedAt;
    }

    // gettery/settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public String getSource() {
        return source;
    }

    public void setSource(String source) { this.source = source; }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
}
