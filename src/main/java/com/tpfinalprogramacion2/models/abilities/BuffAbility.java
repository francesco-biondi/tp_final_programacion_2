package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;

public class BuffAbility extends Ability{

    private int duration;
    private Ability buffedAbility;

    public BuffAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, int cooldownTime, boolean isAvailable, boolean isAnimating, int duration, Ability buffedAbility) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, cooldownTime, isAvailable, isAnimating);
        this.duration = duration;
        this.buffedAbility = buffedAbility;
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
