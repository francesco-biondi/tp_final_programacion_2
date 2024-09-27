package com.tpfinalprogramacion2.models.characters;

import com.tpfinalprogramacion2.models.abilities.Ability;

public class Nakama extends Ability {

    /**
     * Atributos
     */
    private String poster;
    private String poster2;

    /**
     * Constructor
     */


    /**
     * Getters y setters
     */
    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
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
}
