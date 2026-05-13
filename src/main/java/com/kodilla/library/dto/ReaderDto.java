package com.kodilla.library.dto;
import java.time.LocalDate;

public record ReaderDto(Long id, String firstname, String lastname, LocalDate signUpDate) {}
