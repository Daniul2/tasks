package com.kodilla.library.repository;

import com.kodilla.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    Optional<Rental> findByBookInstance_IdAndReturnDateIsNull(Long bookInstanceId);
}
