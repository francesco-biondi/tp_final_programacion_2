package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.interfaces.I_Ability;
import game.models.exceptions.MaxLevelReachedException;
import game.services.SchedulerService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.concurrent.TimeUnit;

public abstract class Ability implements I_Ability{

    protected int BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String description;
    protected String image;
    protected final int maxLevel = 10;
    protected long strength;
    protected int cooldownTime;
    protected boolean available = true;
    protected boolean animating = false;

    protected SimpleIntegerProperty level;
    protected SimpleIntegerProperty price;
    protected SimpleBooleanProperty unlocked;

    @Override
    public void cooldown(){
        this.available = false;
        SchedulerService.getScheduler().schedule(() -> {
            this.available = true;
        }, this.cooldownTime, TimeUnit.SECONDS);
    }

    @Override
    public void upgrade() {
        if(level.get() < maxLevel){
            if(this.getLevel() == 0) this.unlocked.set(true);
            increaseLevel();
            setPrice((int) (price.get() * Math.pow(1.2, level.get())) + level.get() * 100);
            setStrength((long) (BASE_STRENGTH * Math.pow(2, level.get())));
        } else {
            throw new MaxLevelReachedException("La habilidad ha alcanzado el nivel maximo.");
        }
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public long getBASE_STRENGTH() {
        return BASE_STRENGTH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getImage() {
        return image;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    private void increaseLevel(){
        this.setLevel(level.get() + 1);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAnimating() {
        return animating;
    }

    public void setAnimating(boolean animating) {
        this.animating = animating;
    }

    public BooleanProperty unlockedProperty() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked.set(unlocked);
    }

    public boolean isUnlocked() {
        return unlocked.get();
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s\n\nInfo: %s\n\nNivel: %d\n\nPoder: %d\n\nPrecio: %d",
                name, description, level.get(), strength, price.get());
    }
}
