package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.characters.Character;
import com.tpfinalprogramacion2.models.characters.Enemy;
import com.tpfinalprogramacion2.models.exceptions.*;

public class AttackAbility extends Ability {

    public AttackAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, isAvailable, cooldownTime, isAnimating);
    }

    @Override
    public double use(Character character) {
        if(this.isAvailable){
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
        if(this.level <= this.maxLevel){
            this.level++;
            this.strength += level * 1; // numero a determinar
        } else {
            throw new MaxLevelReachedException("The level has already reached the maximum allowed.");
        }
    }

    @Override
    public void animation() {

    }

}
