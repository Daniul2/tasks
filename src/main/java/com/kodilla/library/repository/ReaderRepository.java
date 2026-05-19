package com.kodilla.library.repository;

import com.kodilla.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;


public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
