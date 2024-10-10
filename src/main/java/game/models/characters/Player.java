package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import game.models.abilities.enums.NakamaNames;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import game.models.abilities.enums.AbilityNames;
import game.models.saves.dependencies.SaveManager;

import java.util.Map;

import static game.services.Resource.ABILITIES;
import static game.services.Resource.NAKAMAS;

public class Player extends Character{

    private int gold = 0;

    private transient StringProperty goldProperty;
    private transient StringProperty bountyProperty;

    Map<AbilityNames, Ability> abilities;
    Map<NakamaNames, Nakama> nakamas;

    public Player(String name) {
        this.name = name;
        this.abilities = SaveManager.loadFileMap(ABILITIES, new TypeToken<Map<AbilityNames, Ability>>(){}.getType());
        this.nakamas = SaveManager.loadFileMap(NAKAMAS, new TypeToken<Map<NakamaNames, Nakama>>(){}.getType());
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
}
