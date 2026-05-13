package com.kodilla.library.service;

import com.kodilla.library.domain.BookInstance;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rental;
import com.kodilla.library.domain.Title;
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
        return readerRepository.save(reader);
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public BookInstance saveInstance(BookInstance instance) {
        return bookInstanceRepository.save(instance);
    }

    public void updateInstanceStatus(Long id, String status) {
        BookInstance instance = bookInstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instance not found"));
        instance.setStatus(status);
        bookInstanceRepository.save(instance);
    }

    public long getAvailableCount(String title) {
        return bookInstanceRepository.countByTitle_TitleAndStatus(title, "available");
    }

    @Transactional
    public Rental rentBook(Long readerId, Long titleId) {
        List<BookInstance> availableInstances = bookInstanceRepository.findByTitle_IdAndStatus(titleId, "available");

        if (availableInstances.isEmpty()) {
            throw new RuntimeException("No available instances for this title");
        }

        BookInstance instanceToRent = availableInstances.get(0);
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        instanceToRent.setStatus("rented");
        bookInstanceRepository.save(instanceToRent);

        Rental rental = new Rental(null, instanceToRent, reader, LocalDate.now(), null);
        return rentalRepository.save(rental);
    }

    @Transactional
    public void returnBook(Long instanceId) {
        Rental rental = rentalRepository.findByBookInstance_IdAndReturnDateIsNull(instanceId)
                .orElseThrow(() -> new RuntimeException("No active rental found for this instance"));

        rental.setReturnDate(LocalDate.now());

        rental.getBookInstance().setStatus("available");

        rentalRepository.save(rental);
    }
}
