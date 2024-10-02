package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.enums.AbilityType;
import game.models.characters.Character;
import game.models.characters.Player;
import game.models.exceptions.*;

public class BuffAbility extends Ability{

    private int durationTime;
    private Ability buffedAbility;

    public BuffAbility(double BASE_STRENGTH, String name, AbilityType type, String descripcion, int level, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating, int durationTime) {
        super(BASE_STRENGTH, name, type, descripcion, level, strength, isAvailable, cooldownTime, isAnimating);
        this.durationTime = durationTime;
    }

    @Override
    public double use(Character character) {
        if(this.isAvailable){
            if(character instanceof Player player){
                this.buffedAbility = player.getAbility(0);
                // NO SE si es aplicado siempre el buff solo al ataque basico
                // Si es asi, se puede ya asignar en el constructor
                // Si no, hay que ver como se trae la habilidad que acciona para aplicarle el buff

                applyBuff(buffedAbility);
                duration();
                cooldown();
                removeBuff(buffedAbility);
                return strength;
            } else {
                throw new IllegalArgumentException("Character must be an instance of player.");
            }
        } else {
            throw new AbilityNotAvailableException("Ability is not available.");
        }
    }

    private void applyBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() * (1 + strength));
    }

    private void removeBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() / (1 + strength));
    }

    public void duration(){
        // SCHEDULER
    }

    @Override
    public void upgrade() {
        if(this.level <= this.maxLevel){
            this.level++;
            this.strength += 1; // numero a determinar
        } else {
            throw new MaxLevelReachedException("The level has already reached the maximum allowed.");
        }
    }

    @Override
    public void animation() {
    }
}
