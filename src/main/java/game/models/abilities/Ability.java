package game.models.abilities;

import game.models.abilities.enums.AbilityType;
import game.models.abilities.interfaces.I_Ability;
import game.models.characters.Character;
import game.services.SchedulerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.TimeUnit;

public class Ability implements I_Ability {

    protected final double BASE_STRENGTH;
    protected String name;
    protected AbilityType type;
    protected String description;
    protected final String image;
    protected int level;
    protected final int maxLevel = 10;
    protected int price;
    protected double strength;
    protected int cooldownTime;
    protected boolean isAvailable;
    protected boolean isAnimating;

    protected transient StringProperty nameProperty;
    protected transient StringProperty levelProperty;
    protected transient StringProperty priceProperty;

    public Ability(double BASE_STRENGTH, String name, AbilityType type, String description, String image, int level, int price, double strength, boolean isAvailable, int cooldownTime, boolean isAnimating) {
        this.BASE_STRENGTH = BASE_STRENGTH;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.level = level;
        this.price = price;
        this.strength = strength;
        this.isAvailable = isAvailable;
        this.cooldownTime = cooldownTime;
        this.isAnimating = isAnimating;
    }

    @Override
    public double use(Character character) {
        return 0;
    }

    @Override
    public void cooldown(){
        this.isAvailable = false;
        SchedulerService.getScheduler().schedule(() -> {
            this.isAvailable = true;
        }, this.cooldownTime, TimeUnit.SECONDS);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void animation() {

    }

    public StringProperty levelProperty() {
        return levelProperty == null ? levelProperty  = new SimpleStringProperty(Integer.toString(level)) : levelProperty;
    }

    public StringProperty nameProperty() {
        return nameProperty == null ? nameProperty  = new SimpleStringProperty(name) : nameProperty;
    }

    public StringProperty priceProperty() {
        return priceProperty == null ? priceProperty  = new SimpleStringProperty(Integer.toString(price)) : priceProperty;
    }

    public double getBASE_STRENGTH() {
        return BASE_STRENGTH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nameProperty.set(name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.priceProperty.set(Integer.toString(this.price));
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

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }
}
