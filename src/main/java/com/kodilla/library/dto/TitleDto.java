package com.kodilla.library.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TitleDto(
        Long id,

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 255, message = "Title too long")
        String title,

        @NotBlank(message = "Author cannot be blank")
        @Size(max = 100, message = "Author name too long")
        String author,

        @Min(value = 1000, message = "Release year seems too old")
        @Max(value = 2100, message = "Release year seems too far in future")
        int releaseYear
) {}
