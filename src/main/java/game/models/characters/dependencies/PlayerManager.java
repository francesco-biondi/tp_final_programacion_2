package game.models.characters.dependencies;

import game.models.characters.Enemy;
import game.models.characters.Player;

public class PlayerManager {

    public static void addGoldByClick(Player player, Enemy enemy){
        int goldByClick = enemy.getGOLD_BY_CLICK();
        player.setGold(goldByClick);
    }
}
