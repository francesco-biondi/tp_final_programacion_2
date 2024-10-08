package game.models.characters;

public class Character {

    protected String name;
    protected long bounty;
    protected String posterImage;

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

    public long getBounty() {
        return bounty;
    }

    public void setBounty(long bounty) {
        this.bounty = bounty;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
