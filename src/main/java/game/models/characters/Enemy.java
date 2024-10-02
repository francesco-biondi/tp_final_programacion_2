package game.models.characters;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Enemy extends Character{

    public final double MAX_HEALTH;
    public final int GOLD_BY_CLICK;
    private final String image;
    private double health;
    private transient DoubleProperty healthProperty;
    private boolean defeated = false;
    private boolean unlocked = false;

    public Enemy(String name, double bounty, String posterImage, String image, double MAX_HEALTH, int GOLD_BY_CLICK) {
        super(name, bounty, posterImage);
        this.MAX_HEALTH = MAX_HEALTH;
        this.health = MAX_HEALTH;
        this.healthProperty = new SimpleDoubleProperty(MAX_HEALTH);
        this.GOLD_BY_CLICK = GOLD_BY_CLICK;
        this.image = image;
    }

    public double getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public int getGOLD_BY_CLICK() {
        return GOLD_BY_CLICK;
    }

    public String getImage() {
        return image;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
        this.healthProperty.set(health);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public DoubleProperty healthProperty() {
        return healthProperty == null ? healthProperty = new SimpleDoubleProperty(health) : healthProperty;
    }

}
