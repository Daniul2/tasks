package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookInstanceRepository;
import com.kodilla.library.repository.ReaderRepository;
import com.kodilla.library.repository.RentalRepository;
import com.kodilla.library.repository.TitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor


public class LibraryService {
    private final ReaderRepository readerRepository;
    private final TitleRepository titleRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final RentalRepository rentalRepository;

    public Reader saveReader(Reader reader) {
        if (reader.getSignUpDate() == null) reader.setSignUpDate(LocalDate.now());
        return readerRepository.save(reader);
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public BookInstance saveInstance(BookInstance instance) {
        return bookInstanceRepository.save(instance);
    }

    public void updateInstanceStatus(Long id, BookStatus status) {
        BookInstance instance = bookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instance not found"));
        instance.setStatus(status);
        bookInstanceRepository.save(instance);
    }

    public long getAvailableCount(String title) {
        return bookInstanceRepository.countByTitle_TitleAndStatus(title, BookStatus.AVAILABLE);
    }

    @Transactional
    public Rental rentBook(Long readerId, Long titleId) {
        List<BookInstance> available = bookInstanceRepository.findByTitle_IdAndStatus(titleId, BookStatus.AVAILABLE);
        if (available.isEmpty()) throw new RuntimeException("No copies available");

        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        BookInstance instance = available.get(0);
        instance.setStatus(BookStatus.RENTED);

        Rental rental = new Rental(null, instance, reader, LocalDate.now(), null);
        return rentalRepository.save(rental);
    }

    @Transactional
    public void returnBook(Long instanceId) {
        Rental rental = rentalRepository.findByBookInstance_IdAndReturnDateIsNull(instanceId)
                .orElseThrow(() -> new RuntimeException("No active rental found"));

        rental.setReturnDate(LocalDate.now());
        rental.getBookInstance().setStatus(BookStatus.AVAILABLE);
        rentalRepository.save(rental);
    }
}
