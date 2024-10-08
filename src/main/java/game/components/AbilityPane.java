package game.components;

import game.models.abilities.Ability;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class AbilityPane extends VBox {

    @FXML
    private Text abilityName;

    @FXML
    private Text abilityLevel;

    @FXML
    private Text abilityPrice;

    @FXML
    private ImageView abilityImage;

    @FXML
    private ImageView buttonImage;

    public AbilityPane() {
        FXMLLoader loader = new FXMLLoader(
                AbilityPane.class.getResource(
                        "ability-pane.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAbilityData(Ability ability) {
        this.abilityName.setText(ability.getName());
        this.abilityImage.setImage(new Image(ability.getImage()));
        abilityPrice.textProperty().bind(ability.priceProperty());
        abilityLevel.textProperty().bind(ability.levelProperty());
    }

}