package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.characters.Character;
import game.models.exceptions.MaxLevelReachedException;

public class Nakama extends Ability {

    /**
     * Atributos
     */
    private String poster;
    private String poster2;

    /**
     * Constructor
     */
    public Nakama(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, String poster, String poster2) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, isAvailable, cooldownTime, isAnimating);
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
    public double use(Character character) {
        return 0;
    }


    @Override
    public void upgrade(){
        if(this.level < this.maxLevel){
            this.level++;
            this.strength += level * 1; // numero a determinar
            // level = maxlevel cambiar poster
        } else {
            throw new MaxLevelReachedException("The level has already reached the maximum allowed.");
        }
    }

    @Override
    public void animation() {
        
    }
}
