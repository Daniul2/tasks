package com.kodilla.library.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReaderDto(
        Long id,

        @NotBlank(message = "Firstname cannot be blank")
        @Size(min = 2, max = 50, message = "Firstname must be 2-50 characters")
        String firstname,

        @NotBlank(message = "Lastname cannot be blank")
        @Size(min = 2, max = 50, message = "Lastname must be 2-50 characters")
        String lastname,

        LocalDate signUpDate
) {}
