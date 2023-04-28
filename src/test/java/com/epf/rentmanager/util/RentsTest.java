package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.validateur.ValidateurVehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentsTest {
    @Test
    void Reservation_valide_should_return_true_when_reservation_is_under_7_days() {
        // Given
        Reservation reservationValide = new Reservation(1,5,2
                                                        , LocalDate.of(2012, 5, 8)
                                                        , LocalDate.of(2012, 5, 15));
        // Then
        assertTrue(ValidateurVehicle.resa7jours(reservationValide));
    }

    @Test
    void Reservation_valide_should_return_false_when_reservation_is_over_7_days() {
        // Given
        Reservation reservationInvalid = new Reservation(1,5,2,
                                                        LocalDate.of(2012, 5, 8),
                                                        LocalDate.of(2012, 5, 16));
        // Then
        assertFalse(ValidateurVehicle.resa7jours(reservationInvalid));
    }
}
