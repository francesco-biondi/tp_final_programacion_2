package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.Nakama;
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
    private transient IntegerProperty goldProperty;

    Map<AbilityNames, Ability> abilities;
    ArrayList<Nakama> nakamas;

    public Player(String name, double bounty, String image, int gold) {
        super(name, bounty, image);
        this.gold = gold;
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

    public void setNakamas(ArrayList<Nakama> nakamas) {
        this.nakamas = nakamas;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int amount) {
        this.gold += amount;
        this.goldProperty.set(this.gold);
    }

    public IntegerProperty goldProperty() {
        return goldProperty == null ? goldProperty  = new SimpleIntegerProperty(gold) : goldProperty;
    }
}
