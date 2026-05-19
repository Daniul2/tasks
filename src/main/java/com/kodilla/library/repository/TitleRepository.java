package com.kodilla.library.repository;

import com.kodilla.library.domain.Title;
import org.springframework.data.repository.CrudRepository;


public interface TitleRepository extends CrudRepository<Title, Long> {
}
