package game.models.characters;

public class Character {

    protected String name;
    protected double bounty;
    protected String posterImage;

    public Character(String name, double bounty, String image) {
        this.name = name;
        this.bounty = bounty;
        this.posterImage = image;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", bounty=" + bounty +
                '}';
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

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
