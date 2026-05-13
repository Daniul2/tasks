package com.kodilla.library.dto;
public record TitleDto(
        Long id,
        String title,
        String author,
        int releaseYear
) {}
