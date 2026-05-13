package com.kodilla.library.repository;

import com.kodilla.library.domain.BookInstance;
import com.kodilla.library.domain.BookStatus;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface BookInstanceRepository extends CrudRepository<BookInstance, Long> {
    List<BookInstance> findByTitle_IdAndStatus(Long titleId, BookStatus status);
    long countByTitle_TitleAndStatus(String title, BookStatus status);
}
