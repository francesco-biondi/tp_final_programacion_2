package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;

public class AttackAbility extends Ability {
    public AttackAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, int cooldown, int cooldownTime, boolean isAvailable, boolean isAnimating) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, cooldown, cooldownTime, isAvailable, isAnimating);
    }

    @Override
    public double use() {
        return 0;
    }

    @Override
    public void cooldown() {

    }

    @Override
    public void upgrade() {

    }

    @Override
    public void animation() {
        super.animation();
    }
}
