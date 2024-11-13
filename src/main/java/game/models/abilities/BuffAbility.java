package game.models.abilities;

import game.models.abilities.enums.AbilityNames;
import game.models.characters.Character;
import game.models.characters.Player;
import game.models.exceptions.*;
import game.services.SchedulerService;

import java.util.concurrent.TimeUnit;

public class BuffAbility extends Ability{

    @Override
    public double use(Character player) {
        if(this.available){
            if(player instanceof Player p){
                effect();
                Ability buffedAbility = p.getAbility(AbilityNames.PUNCH);
                applyBuff(buffedAbility);
                duration(buffedAbility);
                cooldown();
                return strength;
            } else {
                throw new IllegalArgumentException("Target must be a player.");
            }
        } else {
            throw new AbilityNotAvailableException("This ability is not available.");
        }
    }

    private void applyBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() * (1 + strength));
    }

    private void removeBuff(Ability buffedAb){
        buffedAb.setStrength(buffedAb.getStrength() / (1 + strength));
    }

    public void duration(Ability buffedAb){
        SchedulerService.getScheduler().schedule(() -> {
            removeBuff(buffedAb);
        }, durationTime, TimeUnit.SECONDS);
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }
}
