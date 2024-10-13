package game.models.characters;

import com.google.gson.reflect.TypeToken;
import game.models.abilities.Ability;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import game.models.abilities.Nakama;
import game.models.abilities.enums.NakamaNames;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import game.models.abilities.enums.AbilityNames;
import game.models.saves.dependencies.SaveManager;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static game.services.Resource.*;

public class Player extends Character{

    private int gold = 0;

    private transient StringProperty goldProperty;
    private transient StringProperty bountyProperty;

    private Map<AbilityNames, AttackAbility> attackAbilities;
    private Map<AbilityNames, BuffAbility> buffAbilities;
    private Map<NakamaNames, Nakama> nakamas;

    public Player(String name) {
        this.name = name;
        this.attackAbilities = SaveManager.loadFileMap(ATTACK_ABILITIES, new TypeToken<Map<AbilityNames, AttackAbility>>(){}.getType());
        this.buffAbilities = SaveManager.loadFileMap(BUFF_ABILITIES, new TypeToken<Map<AbilityNames, BuffAbility>>(){}.getType());
        this.nakamas = SaveManager.loadFileMap(NAKAMAS, new TypeToken<Map<NakamaNames, Nakama>>(){}.getType());
    }

    public Map<AbilityNames, AttackAbility> getAttackAbilities() {
        return attackAbilities;
    }

    public void setAttackAbilities(Map<AbilityNames, AttackAbility> attackAbilities) {
        this.attackAbilities = attackAbilities;
    }

    public Ability getAbility(AbilityNames abilityName) {
        if(this.attackAbilities.containsKey(abilityName)){
            return this.attackAbilities.get(abilityName);
        }
        if (this.buffAbilities.containsKey(abilityName)) {
            return this.buffAbilities.get(abilityName);
        }
        return null;
    }

    public Map<AbilityNames, BuffAbility> getBuffAbilities() {
        return buffAbilities;
    }

    public void setBuffAbilities(Map<AbilityNames, BuffAbility> buffAbilities) {
        this.buffAbilities = buffAbilities;
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
}
