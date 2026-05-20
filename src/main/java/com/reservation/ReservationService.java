package com.reservation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    private Map<Long,Reservation>reservationMap = Map.of(

    );
    public Reservation getReservationById(Long id) {
        return new Reservation(
                id,
                100L,
                40L,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                ReservationStatus.APPROVED
        );
    }

    public List<Reservation> findAllReservation() {
        return List.of(
                new Reservation(
                        1L,
                        100L,
                        40L,
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        ReservationStatus.APPROVED
                ),
                new Reservation(
                        2L,
                        100L,
                        40L,
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        ReservationStatus.APPROVED
                )
        );
    }
}
