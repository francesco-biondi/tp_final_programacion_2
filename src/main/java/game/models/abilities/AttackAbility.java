package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.characters.Character;
import game.models.characters.Enemy;
import game.models.exceptions.*;

public class AttackAbility extends Ability {

    public AttackAbility(int BASE_STRENGTH, String name, AbilityType type, String description, String image, int level, int price, int strength, int cooldownTime) {
        super(BASE_STRENGTH, name, type, description, image, level, price, strength, cooldownTime);
    }

    @Override
    public double use(Character enemy) throws AbilityNotAvailableException, IllegalArgumentException {
        if(this.available){
            if(enemy instanceof Enemy e){
                e.setHealth(e.getHealth() - this.strength);
                cooldown();
                return strength;
            } else {
                throw new IllegalArgumentException("Target must be an enemy.");
            }
        } else {
            throw new AbilityNotAvailableException("This ability is not available.");
        }
    }

    @Override
    public void animation() {

    }

}
