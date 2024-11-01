package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.NakamaNames;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;

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
    
}
