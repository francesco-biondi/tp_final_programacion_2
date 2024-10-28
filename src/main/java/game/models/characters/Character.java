package game.models.characters;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Character {

    protected String name;
    private final SimpleStringProperty bounty = new SimpleStringProperty("0");
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

    public StringProperty bountyProperty() {
        return bounty;
    }

    public String getBounty() {
        return bounty.get();
    }

    public void setBounty(String bounty) {
        this.bounty.set(bounty);
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
