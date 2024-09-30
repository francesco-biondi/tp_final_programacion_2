package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.interfaces.I_Ability;

public abstract class Ability implements I_Ability {

    protected double BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String descripcion;
    protected int level;
    protected double strength;
    protected int cooldownTime;
    protected boolean isAvailable;
    protected boolean isAnimating;

    public Ability(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, int cooldownTime, boolean isAvailable, boolean isAnimating) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.name = name;
        this.type = type;
        this.descripcion = descripcion;
        this.level = level;
        this.strength = strength;
        this.cooldownTime = cooldownTime;
        this.isAvailable = isAvailable;
        this.isAnimating = isAnimating;
    }

    public void animation(){}
}
