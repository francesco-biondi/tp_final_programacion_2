package game.scenes.components;

import game.scenes.components.dependencies.ButtonManager;
import game.models.abilities.Ability;
import game.scenes.dependencies.NotificationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
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

    @FXML
    private StackPane buttonContainer;

    private String description;

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

        abilityImage.setOnMouseEntered(event -> {
            NotificationManager.toolTip(abilityImage, description, "gameInfo", 200);
        });
        buttonImage.setOnMouseClicked(this::onBuyButtonClicked);
    }

    public void setAbilityData(Ability ability) {
        this.description = ability.getDescription();
        System.out.println(description);
        this.abilityName.setText(ability.getName());
        this.abilityImage.setImage(new Image(ability.getImage()));
        abilityPrice.textProperty().bind(ability.priceProperty().concat(" ฿"));
        abilityLevel.textProperty().bind(ability.levelProperty());
    }

    @FXML
    private void onBuyButtonClicked(MouseEvent event) {
        ButtonManager.updateButtonState(buttonContainer, 400);

        // Aca se llama a la funcion para mejorar las habilidades y sacar el oro
    }

}