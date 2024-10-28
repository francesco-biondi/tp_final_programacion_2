package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.interfaces.I_Ability;
import game.models.exceptions.MaxLevelReachedException;
import game.services.SchedulerService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.TimeUnit;

public abstract class Ability implements I_Ability{

    protected final long BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String description;
    protected final String image;
    protected int level;
    protected final int maxLevel = 10;
    protected int price;
    protected long strength;
    protected int cooldownTime;
    protected boolean available = true;
    protected boolean animating = false;
    protected boolean unlocked = false;

    protected transient StringProperty levelProperty;
    protected transient StringProperty priceProperty;
    protected transient BooleanProperty unlockProperty;

    public Ability(int BASE_STRENGTH, String name, AbilityType type, String description, String image, int level, int price, int strength, int cooldownTime) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.image = image;
        this.name = name;
        this.type = type;
        this.description = description;
        this.level = level;
        this.price = price;
        this.strength = strength;
        this.cooldownTime = cooldownTime;
    }

    @Override
    public void cooldown(){
        this.available = false;
        SchedulerService.getScheduler().schedule(() -> {
            this.available = true;
        }, this.cooldownTime, TimeUnit.SECONDS);
    }

    @Override
    public void upgrade() {
        if(this.level < this.maxLevel){
            if(this.level == 0) this.setUnlocked(true);
            this.setLevel(++this.level);
            this.setPrice((int) (this.price * Math.pow(1.2, level)) + level * 100);
            this.setStrength((long) (this.BASE_STRENGTH * Math.pow(2, level)));
        } else {
            throw new MaxLevelReachedException("La habilidad ha alcanzado el nivel maximo.");
        }
        if(this.level == this.maxLevel){
            this.price = 0;
            this.priceProperty.set("Max Level");
        }
    }

    public StringProperty levelProperty() {
        return levelProperty == null ? levelProperty  = new SimpleStringProperty(Integer.toString(level)) : levelProperty;
    }

    public StringProperty priceProperty() {
        return priceProperty == null ? priceProperty  = new SimpleStringProperty(Integer.toString(price)) : priceProperty;
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
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.priceProperty.set("฿ " + this.price);
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
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        this.levelProperty.set(Integer.toString(level));
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

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
        this.unlockProperty.set(unlocked);
    }

    public BooleanProperty unlockProperty() {
        return unlockProperty == null ? unlockProperty  = new SimpleBooleanProperty(unlocked) : unlockProperty;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s\n\nInfo: %s\n\nPoder: %d\n\nPrecio: %d",
                name, description, strength, price);
    }
}
