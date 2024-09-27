package com.tpfinalprogramacion2.models.abilities;

import com.tpfinalprogramacion2.models.abilities.enums.AbilityType;
import com.tpfinalprogramacion2.models.characters.Character;

public class Nakama extends Ability {

    /**
     * Atributos
     */
    private String poster;
    private String poster2;

    /**
     * Constructor
     */
    public Nakama(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, int maxLevel, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, String poster, String poster2) {
        super(BASE_STRENGTH, name, type, descripcion, level, maxLevel, strength, isAvailable, cooldownTime, isAnimating);
        this.poster = poster;
        this.poster2 = poster2;
    }

    /**
     * Getters y setters
     */
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster2() {
        return poster2;
    }

    public void setPoster2(String poster2) {
        this.poster2 = poster2;
    }

    @Override
    public void cooldown() {

    }

    @Override
    public double use(Character character) {
        return 0;
    }


    @Override
    public void upgrade() {

    }

    @Override
    public void animation() {
        
    }
}
