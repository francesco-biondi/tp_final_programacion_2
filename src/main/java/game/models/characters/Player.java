package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import game.models.abilities.enums.AbilityNames;
import game.models.saves.dependencies.SaveManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Map;

import static game.services.Resource.ABILITIES;
import static game.services.Resource.NAKAMAS;

public class Player extends Character{

    private int gold = 0;
    private transient StringProperty goldProperty;

    Map<AbilityNames, Ability> abilities;
    ArrayList<Nakama> nakamas;

    public Player(String name) {
        this.name = name;
        this.abilities = SaveManager.loadFileMap(ABILITIES, new TypeToken<Map<AbilityNames, Ability>>(){}.getType());
        this.nakamas = SaveManager.loadFile(NAKAMAS, new TypeToken<ArrayList<Ability>>(){}.getType());
    }

    public Map<AbilityNames, Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<AbilityNames, Ability> abilities) {
        this.abilities = abilities;
    }

    public Ability getAbility(AbilityNames abilityName) {
        return abilities.get(abilityName);
    }

    public ArrayList<Nakama> getNakamas() {
        return nakamas;
    }

    public Nakama getNakama(int id) {
        return nakamas.get(id);
    }

    public void setNakamas(ArrayList<Nakama> nakamas) {
        this.nakamas = nakamas;
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
