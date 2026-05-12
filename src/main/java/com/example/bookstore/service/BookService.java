package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Page<Book> getBooks(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("title")));
    }

    public Book getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
