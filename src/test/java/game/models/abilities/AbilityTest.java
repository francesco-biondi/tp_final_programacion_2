package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.AbilityNames;
import game.models.characters.Player;
import game.models.exceptions.MaxLevelReachedException;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Clase de prueba para la clase Ability
 */
public class AbilityTest {

    private Save testSave;
    private Player testPlayer;
    private AttackAbility testAA;
    private BuffAbility testBA;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializacia un saveMock, un player, una attack ability y una buff ability.
     */
    @BeforeEach
    public void setUp(){

        testSave = DataProvider.saveMock();
        GameManager.setCurrentSave(testSave);

        testPlayer = GameManager.getCurrentPlayer();

        testAA = (AttackAbility) testPlayer.getAbility(AbilityNames.PISTOL);
        testBA = (BuffAbility) testPlayer.getAbility(AbilityNames.GEAR_2);

    }

    /**
     * Prueba la mejora exitosa de una habilidad de ataque.
     * Verifica que el nivel, fuerza y precio se actualicen correctamente.
     */
    @Test
    public void testUpgradeSuccessfullyWithAttackAbility(){
        // Given
        int initialLevel = testAA.getLevel();
        int initialPrice = testAA.getPrice();

        // When
        testAA.upgrade();

        int expectedLevel = initialLevel + 1;
        long expectedStrength = (long) (testAA.BASE_STRENGTH * Math.pow(2, expectedLevel));
        int expectedPrice = (int) (initialPrice * Math.pow(1.2, expectedLevel)) + expectedLevel * 100;

        // Then
        assertEquals(expectedLevel, testAA.getLevel());
        assertTrue(testAA.unlocked.get());
        assertEquals(expectedStrength, testAA.getStrength());
        assertEquals(expectedPrice, testAA.getPrice());
    }

    /**
     * Prueba la mejora exitosa de una habilidad de mejora.
     * Verifica que el nivel, la fuerza y precio se actualicen correctamente.
     */
    @Test
    public void testUpgradeSuccessfullyWithBuffAbility(){
        // Given
        int initialLevel = testBA.getLevel();
        int initialPrice = testBA.getPrice();

        // When
        testBA.upgrade();

        int expectedLevel = initialLevel + 1;
        long expectedStrength = (long) (testBA.BASE_STRENGTH * Math.pow(2, expectedLevel));
        int expectedPrice = (int) (initialPrice * Math.pow(1.2, expectedLevel)) + expectedLevel * 100;

        // Then
        assertEquals(expectedLevel, testBA.getLevel());
        assertTrue(testBA.unlocked.get());
        assertEquals(expectedStrength, testBA.getStrength());
        assertEquals(expectedPrice, testBA.getPrice());
    }

    /**
     * Prueba que se lance una excepcion al intentar mejorar una habilidad que ya alcanzo su nivel maximo.
     */
    @Test
    public void testUpgradeMaxLevelReached(){
        // Given
        testAA.setLevel(testAA.getMaxLevel());
        testBA.setLevel(testBA.getMaxLevel());

        // When & Then
        assertThrows(MaxLevelReachedException.class, () -> testAA.upgrade());
        assertThrows(MaxLevelReachedException.class, () -> testBA.upgrade());
    }
}
