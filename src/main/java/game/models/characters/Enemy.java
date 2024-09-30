package game.models.characters;

public class Enemy extends Character{

    public final double MAX_HEALTH;
    public final int GOLD_BY_CLICK;

    private double health;

    public Enemy(String name, double bounty, String image, double MAX_HEALTH, int GOLD_BY_CLICK, double health) {
        super(name, bounty, image);
        this.MAX_HEALTH = MAX_HEALTH;
        this.GOLD_BY_CLICK = GOLD_BY_CLICK;
        this.health = health;
    }

    public double getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public int getGOLD_BY_CLICK() {
        return GOLD_BY_CLICK;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
