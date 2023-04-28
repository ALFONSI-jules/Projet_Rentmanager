package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.validateur.ValidateurClient;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientsTest {
    @Test
    void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr",  LocalDate.of(2001, 12, 23));
        // Then
        assertTrue(ValidateurClient.isLegal(legalUser));
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client illegalUser = new Client("John", "Doe", "john.doe@ensta.fr",  LocalDate.of(2012, 5, 8));
        // Then
        assertFalse(ValidateurClient.isLegal(illegalUser));
    }
    @Test
    void nomValide_should_return_true_when_name_is_valide() {
        // Given
        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr",  LocalDate.of(2001, 12, 23));
        // Then
        assertTrue(ValidateurClient.nomValide(legalUser));

    }
    @Test
    void nomValide_should_return_false_when_name_is_invalid() {
        // Given
        Client illegalUser = new Client("Jo", "Doe", "john.doe@ensta.fr",  LocalDate.of(2001, 12, 23));
        // Then
        assertFalse(ValidateurClient.nomValide(illegalUser));

    }

}
