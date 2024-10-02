package game.models.characters;

import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Player extends Character{

    private int gold = 0;
    private transient IntegerProperty goldProperty;

    ArrayList<Ability> abilities = new ArrayList<>();
    ArrayList<Nakama> nakamas = new ArrayList<>();

    public Player(String name, double bounty, String image, int gold, ArrayList<Ability> abilities, ArrayList<Nakama> nakamas) {
        super(name, bounty, image);
        this.gold = gold;
        this.abilities = abilities;
        this.nakamas = nakamas;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
    }

    public Ability getAbility(int i){
        return abilities.get(i);
    }

    public void removeAbility(Ability ability){
        abilities.remove(ability);
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
