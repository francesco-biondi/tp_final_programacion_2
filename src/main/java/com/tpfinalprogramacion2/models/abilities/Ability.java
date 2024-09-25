package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.abilities.interfaces.I_Ability;

public abstract class Ability implements I_Ability {

    private double BASE_STRENGTH;
    private String name;
    private AbilityType type;
    private String descripcion;
    private int level;
    private double strength;
    private int cooldown;
    private int cooldownTime;
    private boolean isAvailable;
    private boolean isAnimating;

    public Ability(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, int cooldown, int cooldownTime, boolean isAvailable, boolean isAnimating) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.name = name;
        this.type = type;
        this.descripcion = descripcion;
        this.level = level;
        this.strength = strength;
        this.cooldown = cooldown;
        this.cooldownTime = cooldownTime;
        this.isAvailable = isAvailable;
        this.isAnimating = isAnimating;
    }

    public void animation(){}
}
