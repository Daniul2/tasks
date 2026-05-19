package com.kodilla.library.repository;

import com.kodilla.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RentalRepository extends CrudRepository<Rental, Long> {

    Optional<Rental> findByBookInstance_IdAndReturnDateIsNull(
            Long instanceId
    );

    boolean existsByReader_IdAndBookInstance_Title_IdAndReturnDateIsNull(
            Long readerId,
            Long titleId
    );
}
