package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.NakamaNames;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.exceptions.AbilityNotAvailableException;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase Nakama
 */
public class NakamaTest {

    private Save testSave;
    private Player testPlayer;
    private Enemy testEnemy;
    private Nakama testNakama;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializacia un saveMock, un player, un enemy y un nakama.
     */
    @BeforeEach
    public void setUp() {

        testSave = DataProvider.saveMock();
        GameManager.setCurrentSave(testSave);

        testPlayer = GameManager.getCurrentPlayer();
        testEnemy = testSave.getEnemy(0);

        testNakama = testPlayer.getNakama(NakamaNames.ZORO);
    }

    /**
     * Prueba el uso exitoso de un nakama en combate.
     * Verifica que el daño se aplique correctamente al enemigo.
     */
    @Test
    public void testUseNakamaSuccessfully() throws InterruptedException {
        // Given
        testNakama.setAvailable(true);
        double enemyHealth = testEnemy.getHealth();
        double nakamaStrength = testNakama.getStrength();
        double expected = enemyHealth - nakamaStrength;

        // When
        testNakama.use(testEnemy);
        TimeUnit.SECONDS.sleep(1);
//        testNakama.stopUse();

        // Then
        assertEquals(expected, testEnemy.getHealth());
    }

    /**
     * Prueba que se lance una excepcion al intentar usar un nakama que no esta habilitado.
     */
    @Test
    public void testUseAbilityNotAvailable() {
        // Given
        testNakama.setAvailable(false);

        // When & Then
        assertThrows(AbilityNotAvailableException.class, () -> testNakama.use(testEnemy));
    }

    /**
     * Prueba que se lance una excepcion al intentar usar un nakama contra un objetivo que no es enemigo.
     */
    @Test
    public void testUseAbilityOnNonEnemy() {
        // Given
        testNakama.setAvailable(true);

        // When & Then
        assertThrows(IllegalStateException.class, () -> testNakama.use(testPlayer));
    }

//    @Test
//    public void testStopUse() throws InterruptedException {
//        // Given
//        testNakama.setAvailable(true);
//        testNakama.use(testEnemy);
//
//        // When
//        testNakama.stopUse();
//
//        // Then
//        assertTrue(SchedulerService.getScheduler().isShutdown());
//    }

    /**
     * Prueba la mejora exitosa de un nakama.
     * Verifica que el nivel, fuerza y precio se actualicen correctamente.
     */
    @Test
    public void testUpgradeSuccessfully() {
        // Given
        int initialLevel = testNakama.getLevel();
        int initialPrice = testNakama.getPrice();

        // When
        testNakama.upgrade();

        int expectedLevel = initialLevel + 1;
        long expectedStrength = (long) (testNakama.BASE_STRENGTH * Math.pow(2, expectedLevel));
        int expectedPrice = (int) (initialPrice * Math.pow(1.2, expectedLevel)) + expectedLevel * 100;

        // Then
        assertEquals(expectedLevel, testNakama.getLevel());
        assertTrue(testNakama.unlocked.get());
        assertEquals(expectedStrength, testNakama.getStrength());
        assertEquals(expectedPrice, testNakama.getPrice());
    }
}
