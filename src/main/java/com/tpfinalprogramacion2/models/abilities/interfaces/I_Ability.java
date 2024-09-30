package com.tpfinalprogramacion2.models.abilities.interfaces;

import com.tpfinalprogramacion2.models.characters.Character;

public interface I_Ability {

    double use(Character character);
    void cooldown();
    void upgrade();
    void animation();
}
