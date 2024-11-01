package game.models.shop;

import game.DataProvider;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import game.models.abilities.Nakama;
import game.models.abilities.enums.AbilityNames;
import game.models.abilities.enums.NakamaNames;
import game.models.characters.Player;
import game.models.saves.Save;
import game.scenes.dependencies.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de prueba para la clase Shop
 */
public class ShopTest {

    private Save testSave;
    private Player testPlayer;
    private AttackAbility testAA;
    private BuffAbility testBA;
    private Nakama testNakama;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializacia un saveMock, un player, una attack ability, una buff ability y un nakama.
     */
    @BeforeEach
    public void setUp(){

        testSave = DataProvider.saveMock();
        GameManager.setCurrentSave(testSave);

        testPlayer = GameManager.getCurrentPlayer();

        testAA = (AttackAbility) testPlayer.getAbility(AbilityNames.PISTOL);
        testBA = (BuffAbility) testPlayer.getAbility(AbilityNames.GEAR_2);
        testNakama = testPlayer.getNakama(NakamaNames.ZORO);
    }

    /**
     * Prueba la compra exitosa de una habilidad de ataque.
     * Verifica que el oro se descuente correctamente y
     * el nivel de la habilidad aumente.
     */
    @Test
    public void testBuyItemSuccessfullyWithAttackAbility(){
        // Given
        int initialGold = 100;
        int initialPrice = 50;
        int initialLevel = 4;

        testPlayer.setGold(initialGold);
        testAA.setPrice(initialPrice);
        testAA.setLevel(initialLevel);

        int expectedLevel = 5;
        int expectedGold = initialGold - testAA.getPrice();

        // When
        Shop.buyItem(testAA);

        // Then
        assertEquals(expectedGold, testPlayer.getGold());
        assertEquals(expectedLevel, testAA.getLevel());
    }
}
