package game.models.shop;

import game.models.abilities.Ability;
import game.models.characters.Player;
import game.models.exceptions.InsufficientGoldException;

public class Shop {

        public static void buyItem(Player player, Ability item){
        int price = item.getPrice();

        if(player.getGold() >= price){
            player.setGold(player.getGold() - price);
        } else {
            throw new InsufficientGoldException("msj");
        }
    }
}
