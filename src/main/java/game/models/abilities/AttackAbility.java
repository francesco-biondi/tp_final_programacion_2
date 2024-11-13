package game.models.abilities;

import game.models.characters.Character;
import game.models.characters.Enemy;
import game.models.exceptions.*;
import game.scenes.dependencies.EffectManager;
import game.scenes.dependencies.GameManager;

public class AttackAbility extends Ability {

    @Override
    public double use(Character enemy) {
        if(this.available){
            if(enemy instanceof Enemy e){
                effect();
                e.setHealth(e.getHealth() - this.strength);
                cooldown();
                return strength;
            } else {
                throw new IllegalArgumentException("Target must be an enemy.");
            }
        } else {
            throw new AbilityNotAvailableException("This ability is not available.");
        }
    }

    @Override
    public void effect() {
        super.effect();
        EffectManager.damageEnemyEffect(GameManager.getCurrentEnemy().getImageView(), BASE_STRENGTH/5);
    }
}
