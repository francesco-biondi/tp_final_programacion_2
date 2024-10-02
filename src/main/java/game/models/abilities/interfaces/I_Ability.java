package game.models.abilities.interfaces;

import game.models.characters.Character;

public interface I_Ability {

    double use(Character character);
    void cooldown();
    void upgrade();
    void animation();
}
