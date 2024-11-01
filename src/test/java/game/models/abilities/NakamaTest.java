package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.NakamaNames;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
