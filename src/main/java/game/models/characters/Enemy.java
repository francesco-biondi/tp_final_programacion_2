package game.models.characters;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Enemy extends Character{


    private final int id;
    public final double MAX_HEALTH;
    public final int GOLD_BY_CLICK;
    private final String image;
    private double health;
    private transient DoubleProperty healthProperty;
    private boolean defeated = false;
    private transient BooleanProperty defeatedProperty;
    private boolean unlocked = false;
    private transient BooleanProperty unlockedProperty;

    public Enemy(String name, double bounty, String posterImage, int id, String image, double MAX_HEALTH, int GOLD_BY_CLICK) {
        super(name, bounty, posterImage);
        this.id = id;
        this.MAX_HEALTH = MAX_HEALTH;
        this.health = MAX_HEALTH;
        this.healthProperty = new SimpleDoubleProperty(MAX_HEALTH);
        this.GOLD_BY_CLICK = GOLD_BY_CLICK;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public double getHealth() {
        return health;
    }

    public int getGOLD_BY_CLICK() {
        return GOLD_BY_CLICK;
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
        this.defeatedProperty = new SimpleBooleanProperty(defeated);
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
        this.unlockedProperty = new SimpleBooleanProperty(unlocked);
    }

    public DoubleProperty healthProperty() {
        return healthProperty == null ? healthProperty = new SimpleDoubleProperty(health) : healthProperty;
    }

    public BooleanProperty defeatedProperty() {
        return defeatedProperty == null ? defeatedProperty = new SimpleBooleanProperty(defeated) : defeatedProperty;
    }

    public BooleanProperty unlockedProperty() {
        return unlockedProperty == null ? unlockedProperty = new SimpleBooleanProperty(unlocked) : unlockedProperty;
    }

    public int getId() {
        return id;
    }
}
