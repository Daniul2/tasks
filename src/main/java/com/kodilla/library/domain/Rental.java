package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "RENTALS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "BOOK_INSTANCE_ID")
    private BookInstance bookInstance;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    private LocalDate rentalDate;
    private LocalDate returnDate;
}
