package game.models.characters;

import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Player extends Character{

    private int gold = 0;
    private transient StringProperty goldProperty;

    ArrayList<Ability> abilities = new ArrayList<>();
    ArrayList<Nakama> nakamas = new ArrayList<>();

    public Player(String name) {
        this.name = name;
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
        this.goldProperty.set(Integer.toString(gold));
    }

    public StringProperty goldProperty() {
        return goldProperty == null ? goldProperty  = new SimpleStringProperty(Integer.toString(gold)) : goldProperty;
    }
}
