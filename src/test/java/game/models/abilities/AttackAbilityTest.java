package game.models.abilities;

import game.DataProvider;
import game.models.abilities.enums.AbilityNames;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.exceptions.AbilityNotAvailableException;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Clase de prueba para la clase AttackAbility
 */
public class AttackAbilityTest {

    private Save testSave;
    private Player testPlayer;
    private Enemy testEnemy;
    private AttackAbility testAA;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializacia un saveMock, un player, un enemy y una attack ability.
     */
    @BeforeEach
    public void setUp(){

        testSave = DataProvider.saveMock();
        GameManager.setCurrentSave(testSave);

        testPlayer = GameManager.getCurrentPlayer();
        testEnemy = testSave.getEnemy(0);

        testAA = (AttackAbility) testPlayer.getAbility(AbilityNames.PISTOL);
    }

    /**
     * Prueba el uso exitoso de una habilidad de ataque.
     * Verifica que el daño se aplique correctamente al enemigo.
     */
    @Test
    public void testUseSuccessfully(){
        // Given
        testAA.setAvailable(true);
        double enemyHealth = testEnemy.getHealth();
        double AAStrength = testAA.getStrength();
        double expected = enemyHealth - AAStrength;

        // When
        testAA.use(testEnemy);

        // Then
        assertEquals(expected, testEnemy.getHealth());
    }

    /**
     * Prueba que se lance una excepcion al intentar usar una habilidad que no esta disponible.
     */
    @Test
    public void testUseAbilityNotAvailable(){
        // Given
        testAA.setAvailable(false);

        // When & Then
        assertThrows(AbilityNotAvailableException.class, () -> testAA.use(testEnemy));
    }

    /**
     * Prueba que se lance una excepcion al intentar usar una habilidad de ataque contra un objetivo que no es enemigo.
     */
    @Test
    public void testUseAbilityOnNonEnemy(){
        // Given
        testAA.setAvailable(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> testAA.use(testPlayer));
    }

}
