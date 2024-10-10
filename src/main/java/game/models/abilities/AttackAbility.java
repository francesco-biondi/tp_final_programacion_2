package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.characters.Character;
import game.models.characters.Enemy;
import game.models.exceptions.*;

public class AttackAbility extends Ability {

    public AttackAbility(double BASE_STRENGTH, String image, String name, AbilityType type, String description, int level, int price, double strength, int cooldownTime, boolean available, boolean animating, boolean unlocked) {
        super(BASE_STRENGTH, image, name, type, description, level, price, strength, cooldownTime, available, animating, unlocked);
    }

    @Override
    public double use(Character character) {
        if(this.available){
            if(character instanceof Enemy e){
                e.setHealth(e.getHealth() - this.strength);
                cooldown();
                return strength;
            } else {
                throw new IllegalStateException("Character must be an enemy.");
            }
        } else {
            throw new AbilityNotAvailableException("This ability is not available.");
        }
    }

    @Override
    public void upgrade(){
        if(this.level < this.maxLevel){
            if(this.level == 0){
                this.available = true;
            }
            this.level++;
            this.strength += level * 10; // numero a determinar
        } else {
            throw new MaxLevelReachedException("The level has already reached the maximum allowed.");
        }
    }

    @Override
    public void animation() {

    }

}
