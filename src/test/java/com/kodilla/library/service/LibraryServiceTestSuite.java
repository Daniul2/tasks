package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.exception.AlreadyRentedException;
import com.kodilla.library.exception.NoAvailableCopiesException;
import com.kodilla.library.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LibraryServiceTestSuite {

    @Autowired
    private LibraryService libraryService;

    // ── TITLE ────────────────────────────────────────────────────────────

    @Test
    void testSaveTitle() {
        // Given
        Title title = new Title(null, "Test Title", "Test Author", 2024);

        // When
        Title saved = libraryService.saveTitle(title);

        // Then
        assertNotNull(saved.getId());
        assertEquals("Test Title", saved.getTitle());
        assertEquals("Test Author", saved.getAuthor());
        assertEquals(2024, saved.getReleaseYear());
    }

    // ── READER ───────────────────────────────────────────────────────────

    @Test
    void testSaveReaderSetsSignUpDateAutomatically() {
        // Given
        Reader reader = new Reader(null, "Jan", "Kowalski", null);

        // When
        Reader saved = libraryService.saveReader(reader);

        // Then
        assertNotNull(saved.getId());
        assertNotNull(saved.getSignUpDate());
    }

    // ── AVAILABLE COUNT ──────────────────────────────────────────────────

    @Test
    void testGetAvailableCountReturnsCorrectNumber() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Dune", "Frank Herbert", 1965)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.RENTED)
        );

        // When
        long count = libraryService.getAvailableCount("Dune");

        // Then
        assertEquals(2, count);
    }

    // ── RENT BOOK ────────────────────────────────────────────────────────

    @Test
    void testRentBookSuccessfully() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "1984", "George Orwell", 1949)
        );
        BookInstance instance = libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        Reader reader = libraryService.saveReader(
                new Reader(null, "Anna", "Nowak", null)
        );

        // When
        Rental rental = libraryService.rentBook(reader.getId(), title.getId());

        // Then
        assertNotNull(rental.getId());
        assertNotNull(rental.getRentalDate());
        assertNull(rental.getReturnDate());
        assertEquals(
                BookStatus.RENTED,
                rental.getBookInstance().getStatus()
        );
    }

    @Test
    void testRentBookThrowsWhenNoCopiesAvailable() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Solaris", "Stanisław Lem", 1961)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.RENTED)
        );
        Reader reader = libraryService.saveReader(
                new Reader(null, "Piotr", "Wiśniewski", null)
        );

        // When & Then
        assertThrows(
                NoAvailableCopiesException.class,
                () -> libraryService.rentBook(reader.getId(), title.getId())
        );
    }

    @Test
    void testRentBookThrowsWhenReaderNotFound() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Pan Tadeusz", "Mickiewicz", 1834)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );

        // When & Then
        assertThrows(
                NotFoundException.class,
                () -> libraryService.rentBook(999L, title.getId())
        );
    }

    @Test
    void testRentBookThrowsWhenAlreadyRented() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Wiedźmin", "Sapkowski", 1993)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        Reader reader = libraryService.saveReader(
                new Reader(null, "Marek", "Zielony", null)
        );

        libraryService.rentBook(reader.getId(), title.getId());

        // When & Then
        assertThrows(
                AlreadyRentedException.class,
                () -> libraryService.rentBook(reader.getId(), title.getId())
        );
    }

    // ── RETURN BOOK ──────────────────────────────────────────────────────

    @Test
    void testReturnBookSetsReturnDateAndStatusAvailable() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Hobbit", "Tolkien", 1937)
        );
        BookInstance instance = libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );
        Reader reader = libraryService.saveReader(
                new Reader(null, "Ewa", "Kwiatkowska", null)
        );
        libraryService.rentBook(reader.getId(), title.getId());

        // When
        libraryService.returnBook(instance.getId());

        // Then
        long available = libraryService.getAvailableCount("Hobbit");
        assertEquals(1, available);
    }

    @Test
    void testReturnBookThrowsWhenNoActiveRental() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Faust", "Goethe", 1808)
        );
        BookInstance instance = libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );

        // When & Then
        assertThrows(
                NotFoundException.class,
                () -> libraryService.returnBook(instance.getId())
        );
    }

    // ── UPDATE STATUS ────────────────────────────────────────────────────

    @Test
    void testUpdateInstanceStatus() {
        // Given
        Title title = libraryService.saveTitle(
                new Title(null, "Lalka", "Prus", 1890)
        );
        BookInstance instance = libraryService.saveInstance(
                new BookInstance(null, title, BookStatus.AVAILABLE)
        );

        // When
        libraryService.updateInstanceStatus(instance.getId(), BookStatus.LOST);

        // Then
        assertEquals(0, libraryService.getAvailableCount("Lalka"));
    }

    @Test
    void testUpdateInstanceStatusThrowsWhenNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> libraryService.updateInstanceStatus(
                        999L, BookStatus.DAMAGED
                )
        );
    }
}
