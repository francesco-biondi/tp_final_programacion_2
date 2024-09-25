package com.tpfinalprogramacion2.models.saves.dependencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaveManagerTest {

    /**
     * Tests para el metodo setSaveName (validación del nombre)
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

    @Test
    public void testSetSaveNameTooShort(){
        // Given
        String shortName = "x";
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            SaveManager.setSaveName(shortName);
        }, "Invalid save name");
    }

    @Test
    public void testSetSaveNameTooLong(){
        // Given
        String longName = "xxxxxxxxxxxxxxxxx";
        // When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            SaveManager.setSaveName(longName);
        }, "Invalid save name");
    }

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
