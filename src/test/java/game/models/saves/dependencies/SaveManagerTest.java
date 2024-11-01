package game.models.saves.dependencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase SaveManager
 */
public class SaveManagerTest {

    /**
     * Prueba que un nombre de guardado valido sea aceptado.
     */
    @Test
    public void testSetSaveNameValid(){
        // Given
        String validName = "Validname1";
        // When
        String result = SaveManager.setSaveName(validName);
        // Then
        assertEquals(validName, result);
    }

    /**
     * Prueba que los nombres demasiado cortos sean rechazados.
     * Asegura que se cumpla el requisito de longitud minima.
     */
    @Test
    public void testSetSaveNameTooShort(){
        // Given
        String shortName = "x";
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            SaveManager.setSaveName(shortName);
        }, "Invalid save name");
    }

    /**
     * Prueba que los nombres que exceden la longitud maxima sean rechazados.
     * Asegura que se cumpla el requisito de longitus maxima.
     */
    @Test
    public void testSetSaveNameTooLong(){
        // Given
        String longName = "xxxxxxxxxxxxxxxxx";
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            SaveManager.setSaveName(longName);
        }, "Invalid save name");
    }

    /**
     * Prueba que los nombres que contienen caracteres invalidos sean rechazados.
     * Asegura que solo se acepten los caracteres permitidos.
     */
    @Test
    public void testSetSaveNameInvalidCharacters(){
        // Given
        String invalidName = "invalid@@@";
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            SaveManager.setSaveName(invalidName);
        }, "Invalid save name");
    }
}
