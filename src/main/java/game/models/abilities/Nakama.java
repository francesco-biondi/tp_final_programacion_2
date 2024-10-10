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
    private String poster2;

    /**
     * Constructor
     */
    public Nakama(double BASE_STRENGTH, String image, String name, AbilityType type, String description, int level, int price, double strength, int cooldownTime, boolean available, boolean animating, boolean unlocked, String poster2) {
        super(BASE_STRENGTH, image, name, type, description, level, price, strength, cooldownTime, available, animating, unlocked);
        this.poster2 = poster2;
    }

    /**
     * Getters y setters
     */

    public String getPoster2() {
        return poster2;
    }

    public void setPoster2(String poster2) {
        this.poster2 = poster2;
    }

    @Override
    public double use(Character character) {
        if(this.available){
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
