package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.AbilityNames;
import game.models.characters.Player;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;

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
}
