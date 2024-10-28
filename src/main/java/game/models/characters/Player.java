package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import game.models.abilities.enums.NakamaNames;
import game.models.saves.dependencies.GsonManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import game.models.abilities.enums.AbilityNames;

import java.util.*;

import static game.services.Resource.*;

public class Player extends Character{

    private int gold = 0;

    private transient StringProperty goldProperty;
    private transient StringProperty bountyProperty;

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
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
        this.goldProperty.set(Integer.toString(gold));
    }

    public void updateGold(int amount){
        this.gold += amount;
        setGold(this.gold);
    }

    public StringProperty goldProperty() {
        return goldProperty == null ? goldProperty  = new SimpleStringProperty(Integer.toString(gold)) : goldProperty;
    }

    @Override
    public void setBounty(long bounty) {
        this.bounty = bounty;
        this.bountyProperty.set(Long.toString(bounty));
    }

    public StringProperty bountyProperty() {
        return bountyProperty == null ? bountyProperty  = new SimpleStringProperty(Long.toString(bounty)) : bountyProperty;
    }

    public List<String> getAbilityDeck() {
        return abilityDeck == null ? new ArrayList<>() : abilityDeck;
    }

    public void setAbilityDeck(List<String> abilityDeck) {
        this.abilityDeck = abilityDeck;
    }
}
