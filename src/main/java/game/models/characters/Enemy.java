package game.models.characters;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Enemy extends Character{

    private int id;
    public double MAX_HEALTH;
    public int GOLD_BY_CLICK;
    private String image;
    private SimpleDoubleProperty health;
    private SimpleBooleanProperty defeated;
    private SimpleBooleanProperty unlocked;

    public String getImage() {
        return image;
    }

    public double getHealth() {
        return health.get();
    }

    public int getGOLD_BY_CLICK() {
        return GOLD_BY_CLICK;
    }

    public void setHealth(double health) {
        this.health.set(health);
    }

    public boolean isDefeated() {
        return defeated.get();
    }

    public void setDefeated(boolean defeated) {
        this.defeated.set(defeated);
    }

    public boolean isUnlocked() {
        return unlocked.get();
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked.set(unlocked);
    }

    public DoubleProperty healthProperty() {
        return health;
    }

    public BooleanProperty defeatedProperty() {
        return defeated;
    }

    public BooleanProperty unlockedProperty() {
        return unlocked;
    }

    public int getId() {
        return id;
    }
}
