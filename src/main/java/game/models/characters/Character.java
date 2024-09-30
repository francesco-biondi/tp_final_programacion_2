package game.models.characters;

public class Character {

    protected String name;
    protected double bounty;
    protected String image;

    public Character(String name, double bounty, String image) {
        this.name = name;
        this.bounty = bounty;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
