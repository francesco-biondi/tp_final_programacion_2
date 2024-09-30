package com.tpfinalprogramacion2.models.characters;

import com.tpfinalprogramacion2.models.abilities.Ability;
import com.tpfinalprogramacion2.models.abilities.Nakama;

import java.util.ArrayList;

public class Player extends Character{

    private int gold;

    ArrayList<Ability> abilities = new ArrayList<>();
    ArrayList<Nakama> nakamas = new ArrayList<>();

    public Player(String name, double bounty, String image, int gold, ArrayList<Ability> abilities, ArrayList<Nakama> nakamas) {
        super(name, bounty, image);
        this.gold = gold;
        this.abilities = abilities;
        this.nakamas = nakamas;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
    }

    
}
