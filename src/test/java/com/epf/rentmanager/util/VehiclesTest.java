package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.validateur.ValidateurVehicle;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehiclesTest {
    @Test
    void nbPlaces_valide_should_return_true_when_nbPlaces_is_between_2_and_9() {
        // Given
        Vehicle vehicleValide = new Vehicle(1,"Ford",5);
        // Then
        assertTrue(ValidateurVehicle.nbPlaces_valide(vehicleValide));
    }

    @Test
    void nbPlaces_valide_should_return_false_when_nbPlaces_is_under_2() {
        // Given
        Vehicle vehicleInvalid = new Vehicle(1,"Ford",1);
        // Then
        assertFalse(ValidateurVehicle.nbPlaces_valide(vehicleInvalid));
    }
    @Test
    void nbPlaces_valide_should_return_false_when_nbPlaces_is_over_9() {
        // Given
        Vehicle vehicleInvalid = new Vehicle(1,"Ford",10);
        // Then
        assertFalse(ValidateurVehicle.nbPlaces_valide(vehicleInvalid));
    }
}
