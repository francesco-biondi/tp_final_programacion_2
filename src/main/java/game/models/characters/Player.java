package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import game.models.abilities.enums.NakamaNames;
import game.models.saves.dependencies.GsonManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import game.models.abilities.enums.AbilityNames;

import java.util.*;

import static game.services.Resource.*;

public class Player extends Character{

    private final SimpleIntegerProperty gold = new SimpleIntegerProperty(0);
    private Map<AbilityNames, Ability> abilities;
    private Map<NakamaNames, Nakama> nakamas;
    private List<String> abilityDeck;

    public Player(String name) {
        this.name = name;
        this.abilities = GsonManager.loadFileMap(ABILITIES, new TypeToken<Map<AbilityNames, Ability>>(){}.getType());
        this.nakamas = GsonManager.loadFileMap(NAKAMAS, new TypeToken<Map<NakamaNames, Nakama>>(){}.getType());
    }

    public Map<AbilityNames, Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<AbilityNames, Ability> abilities) {
        this.abilities = abilities;
    }

    public Ability getAbility(AbilityNames abilityName) {
        return this.abilities.get(abilityName);
    }

    public Map<NakamaNames, Nakama> getNakamas() {
        return nakamas;
    }

    public Nakama getNakama(NakamaNames nakamaName) {
        return nakamas.get(nakamaName);
    }

    public void setNakamas(Map<NakamaNames, Nakama> nakamas) {
        this.nakamas = nakamas;
    }

    public int getGold() {
        return gold.get();
    }

    public void setGold(int gold) {
        this.gold.set(gold);
    }

    public void increaseGold(int amount){
        setGold(this.gold.get() + amount);
    }

    public IntegerProperty goldProperty() {
        return gold;
    }

    public List<String> getAbilityDeck() {
        return abilityDeck == null ? new ArrayList<>() : abilityDeck;
    }

    public void setAbilityDeck(List<String> abilityDeck) {
        this.abilityDeck = abilityDeck;
    }
}
