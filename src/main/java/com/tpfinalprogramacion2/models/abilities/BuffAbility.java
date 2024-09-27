package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.characters.Character;

public class BuffAbility extends Ability{

    private int duration;
    private Ability buffedAbility;

    public BuffAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, int maxLevel, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, int duration, Ability buffedAbility) {
        super(BASE_STRENGTH, name, type, descripcion, level, maxLevel, strength, isAvailable, cooldownTime, isAnimating);
        this.duration = duration;
        this.buffedAbility = buffedAbility;
    }

    @Override
    public double use(Character character) {
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
    }
}
