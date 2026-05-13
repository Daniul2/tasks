package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookInstance;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.BookInstanceDto;
import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.dto.TitleDto;
import com.kodilla.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LibraryMapper {
    private final TitleRepository titleRepository;

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(readerDto.id(), readerDto.firstname(), readerDto.lastname(), readerDto.signUpDate());
    }

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(titleDto.id(), titleDto.title(), titleDto.author(), titleDto.releaseYear());
    }

    public BookInstance mapToBookInstance(BookInstanceDto dto) {
        Title title = titleRepository.findById(dto.titleId())
                .orElseThrow(() -> new RuntimeException("Title not found"));
        return new BookInstance(dto.id(), title, dto.status());
    }
}
