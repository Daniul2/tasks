package com.kodilla.library.dto;
import java.time.LocalDate;

public record RentalDto(
        Long id,
        Long bookInstanceId,
        Long readerId,
        LocalDate rentalDate,
        LocalDate returnDate
) {
}
