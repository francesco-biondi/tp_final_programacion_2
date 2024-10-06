package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.interfaces.I_Ability;
import game.services.SchedulerService;

import java.util.concurrent.TimeUnit;

public abstract class Ability implements I_Ability {

    protected final double BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String descripcion;
    protected int level;
    protected final int maxLevel = 10;
    protected double strength;
    protected int cooldownTime;
    protected boolean isAvailable;
    protected boolean isAnimating;

    public Ability(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.name = name;
        this.type = type;
        this.descripcion = descripcion;
        this.level = level;
        this.strength = strength;
        this.isAvailable = isAvailable;
        this.cooldownTime = cooldownTime;
        this.isAnimating = isAnimating;
    }

    @Override
    public void cooldown(){
        this.isAvailable = false;
        SchedulerService.getScheduler().schedule(() -> {
            this.isAvailable = true;
        }, this.cooldownTime, TimeUnit.SECONDS);
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
