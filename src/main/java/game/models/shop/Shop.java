package game.models.shop;

import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import game.models.abilities.enums.AbilityNames;
import game.models.abilities.enums.NakamaNames;
import game.models.characters.Player;
import game.models.exceptions.InsufficientGoldException;
import game.scenes.dependencies.GameManager;

public class Shop {

    public static void buyItem(Ability item){
        Player player = GameManager.getCurrentPlayer();

        int price = item.getPrice();
        String name = item.getName().replace(" ", "_");

        if(player.getGold() >= price){
            player.setGold(player.getGold() - price);
            if(item instanceof Nakama){
                player.getNakama(NakamaNames.valueOf(name)).upgrade();
            } else {
                player.getAbility(AbilityNames.valueOf(name)).upgrade();
            }
        } else {
            throw new InsufficientGoldException("Oro insuficiente!");
        }
    }
}
