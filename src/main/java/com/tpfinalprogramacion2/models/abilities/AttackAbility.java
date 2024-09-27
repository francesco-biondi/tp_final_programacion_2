package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.characters.Character;

public class AttackAbility extends Ability {

    public AttackAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, int maxLevel, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating) {
        super(BASE_STRENGTH, name, type, descripcion, level, maxLevel, strength, isAvailable, cooldownTime, isAnimating);
    }

    @Override
    public double use(Character character) {
        return 0;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void animation() {

    }

}
