package com.tpfinalprogramacion2.models.characters;

public class Nakama extends Character{

    private double strength;
    private int level;
    private int attackSpeed;

    public Nakama(String name, double bounty, String image, double strength, int level, int attackSpeed) {
        super(name, bounty, image);
        this.strength = strength;
        this.level = level;
        this.attackSpeed = attackSpeed;
    }

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
}
