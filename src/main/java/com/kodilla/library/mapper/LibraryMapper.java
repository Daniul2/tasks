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

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(reader.getId(), reader.getFirstname(), reader.getLastname(), reader.getSignUpDate());
    }

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(titleDto.id(), titleDto.title(), titleDto.author(), titleDto.releaseYear());
    }

    public TitleDto mapToTitleDto(Title title) {
        return new TitleDto(title.getId(), title.getTitle(), title.getAuthor(), title.getReleaseYear());
    }

    public BookInstance mapToBookInstance(BookInstanceDto dto) {
        Title title = titleRepository.findById(dto.titleId())
                .orElseThrow(() -> new RuntimeException("Title not found"));
        return new BookInstance(dto.id(), title, dto.status());
    }

    public BookInstanceDto mapToBookInstanceDto(BookInstance instance) {
        return new BookInstanceDto(instance.getId(), instance.getTitle().getId(), instance.getStatus());
    }
}

