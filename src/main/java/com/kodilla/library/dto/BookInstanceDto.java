package com.kodilla.library.dto;

import com.kodilla.library.domain.BookStatus;

public record BookInstanceDto(Long id, Long titleId, BookStatus status) {}
