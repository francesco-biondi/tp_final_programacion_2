package game.models.characters;

import game.models.abilities.interfaces.I_Ability;

public class Nakama extends Character implements I_Ability {

    /**
     * Atributos
     */
    private double strength;
    private int level;
    private int attackSpeed;

    /**
     * Constructor
     */
    public Nakama(String name, double bounty, String image, double strength, int level, int attackSpeed) {
        super(name, bounty, image);
        this.strength = strength;
        this.level = level;
        this.attackSpeed = attackSpeed;
    }

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
