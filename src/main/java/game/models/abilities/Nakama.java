package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.characters.Character;
import game.models.characters.Enemy;
import game.models.exceptions.AbilityNotAvailableException;
import game.models.exceptions.MaxLevelReachedException;
import game.services.SchedulerService;

import java.util.concurrent.TimeUnit;

public class Nakama extends Ability {

    /**
     * Atributos
     */
    private String poster;
    private String poster2;

    /**
     * Constructor
     */
    public Nakama(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, int price, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, String poster, String poster2) {
        super(BASE_STRENGTH, name, type, descripcion, level, price, strength, isAvailable, cooldownTime, isAnimating);
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
        if(this.isAvailable){
            if(character instanceof Enemy e){
                SchedulerService.getScheduler().scheduleAtFixedRate(() -> {
                    e.setHealth(e.getHealth() - this.strength);
                }, 0, 2, TimeUnit.SECONDS);
                return strength;
            } else {
                throw new IllegalStateException("Character must be an enemy.");
            }
        } else {
            throw new AbilityNotAvailableException("This ability is not available.");
        }
    }

    public void stopUse(){
        if(SchedulerService.getScheduler() != null){
            SchedulerService.getScheduler().shutdownNow();
        }
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
