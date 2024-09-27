package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.abilities.interfaces.I_Ability;

public abstract class Ability implements I_Ability {

    protected final double BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String descripcion;
    protected int level;
    protected int maxLevel;
    protected double strength;
    protected int cooldownTime;
    protected boolean isAvailable;
    protected boolean isAnimating;

    public Ability(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, int maxLevel, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.name = name;
        this.type = type;
        this.descripcion = descripcion;
        this.level = level;
        this.maxLevel = maxLevel;
        this.strength = strength;
        this.isAvailable = isAvailable;
        this.cooldownTime = cooldownTime;
        this.isAnimating = isAnimating;
    }

    @Override
    public void cooldown(){

    }

    public double getBASE_STRENGTH() {
        return BASE_STRENGTH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }
}
