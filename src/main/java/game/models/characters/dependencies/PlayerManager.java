package game.models.characters.dependencies;

import game.models.abilities.Ability;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import game.models.abilities.Nakama;
import game.models.characters.Enemy;
import game.models.characters.Player;

import static game.models.abilities.enums.AbilityNames.PUNCH;

public class PlayerManager {

    public void addGoldByClick(Player player, Enemy enemy){
        int goldByClick = enemy.getGOLD_BY_CLICK();
        player.updateGold(goldByClick);
    }

    private static void applyAbility(Player player, Enemy enemy, Ability ability){
        if(ability instanceof AttackAbility){
            ability.use(enemy);
        } else if(ability instanceof BuffAbility){
            ability.use(player);
        }
    }

    public void basicAttack(Player player, Enemy enemy){
        Ability basic = player.getAbility(PUNCH);

        applyAbility(player, enemy, basic);
    }

    public void stopAllNakamas(Player player){
        for (Nakama nakama : player.getNakamas()) {
            nakama.stopUse();
        }
    }

    // NO TERMINADA
//    private static AbilityNames getAbilityNameFromButton() { // NO SE QUE PARAMETROS LE LLEGA DE LA SCENE
//        return switch () {
//            case "button_1" -> PUNCH; // NO SE SI VAN A SEGUIR LLAMANDOSE button_n O SE LLAMARAN CON EL NOMBRE DE LA HABILIDAD
//            case "button_2" -> GUM_GUM_PISTOL;
//            case "button_3" -> GUM_GUM_WHIP;
//            case "button_4" -> GEAR_2;
//
//        };
//    }
}
