package game.models.characters.dependencies;

import game.models.abilities.Ability;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import game.models.characters.Enemy;
import game.models.characters.Player;

public class PlayerManager {

    public static void addGoldByClick(Player player, Enemy enemy){
        int goldByClick = enemy.getGOLD_BY_CLICK();
        player.setGold(goldByClick);
    }

    private static void applyAbility(Player player, Enemy enemy, Ability ability){
        if(ability instanceof AttackAbility){
            double damage = ability.use(enemy);
        } else if(ability instanceof BuffAbility){
            double buffDamage = ability.use(player);
        }
    }

    
}
