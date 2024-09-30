package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.characters.Character;
import com.tpfinalprogramacion2.models.exceptions.*;

public class BuffAbility extends Ability{

    private int durationTime;
    private Ability buffedAbility;

    public BuffAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, int durationTime) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, isAvailable, cooldownTime, isAnimating);
        this.durationTime = durationTime;
    }

    @Override
    public double use(Character character) {
        return 0;
    }

    private void applyBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() * (1 + strength));
    }

    private void removeBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() / (1 + strength));
    }

    public void duration(){
        // SCHEDULER
    }

    @Override
    public void upgrade() {
        if(this.level <= this.maxLevel){
            this.level++;
            this.strength += 1; // numero a determinar
        } else {
            throw new MaxLevelReachedException("The level has already reached the maximum allowed.");
        }
    }

    @Override
    public void animation() {
    }
}
