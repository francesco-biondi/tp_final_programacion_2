package game.models.characters;

import game.models.abilities.Ability;

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
}
