package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final BookRepository bookRepo;

    public OrderService(OrderRepository orderRepo, BookRepository bookRepo) {
        this.orderRepo = orderRepo;
        this.bookRepo = bookRepo;
    }
    public Order createOrder(List<Long>bookIds){
        List<Book>books = bookRepo.findAllById(bookIds);
        double total = books.stream()
                .mapToDouble(Book::getPrice)
                .sum();

        Order order = new Order(bookIds,total);
        return orderRepo.save(order);
    }
}
