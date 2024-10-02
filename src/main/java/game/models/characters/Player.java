package game.models.characters;

import game.models.abilities.Ability;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Player extends Character{

    private int gold = 0;
    private transient IntegerProperty goldProperty;

    ArrayList<Ability> abilities = new ArrayList<>();
    ArrayList<Nakama> nakamas = new ArrayList<>();

    public Player(String name, double bounty, String image, ArrayList<Ability> abilities, ArrayList<Nakama> nakamas) {
        super(name, bounty, image);
        this.abilities = abilities;
        this.nakamas = nakamas;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
        this.goldProperty.set(gold);
    }

    public IntegerProperty goldProperty() {
        return goldProperty == null ? goldProperty  = new SimpleIntegerProperty(gold) : goldProperty;
    }
}
