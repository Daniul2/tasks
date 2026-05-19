package com.kodilla.library.dto;

import com.kodilla.library.domain.BookStatus;
import jakarta.validation.constraints.NotNull;

public record BookInstanceDto(
        Long id,

        @NotNull(message = "Title ID is required")
        Long titleId,

        @NotNull(message = "Status is required")
        BookStatus status
) {}
