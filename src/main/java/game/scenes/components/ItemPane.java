package game.scenes.components;

import game.models.abilities.Ability;
import game.scenes.dependencies.NotificationManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ItemPane extends VBox {

    @FXML
    private Text abilityName;

    @FXML
    private Text abilityLevel;

    @FXML
    private WoodenButton buyButton;

    @FXML
    private ImageView abilityImage;

    private String description;

    public ItemPane() {
        FXMLLoader loader = new FXMLLoader(
                ItemPane.class.getResource(
                        "item-pane.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        abilityImage.setOnMouseEntered(event -> NotificationManager.toolTip(abilityImage, description, "gameInfo", 200));
    }

    public void setShopItemData(Ability ability) {
        this.description = ability.toString();
        this.abilityName.setText(ability.getName());
        this.abilityImage.setImage(new Image(ability.getImage()));
        buyButton.textProperty().bind(Bindings.concat("฿ ", ability.priceProperty()));
        abilityLevel.textProperty().bind(ability.levelProperty());
        unlockHandle(ability.unlockProperty());
    }

    private void unlockHandle(BooleanProperty unlocked) {
        InnerShadow unlockedEffect = new InnerShadow();
        unlockedEffect.setChoke(0.4);

        ColorAdjust lockedEffect = new ColorAdjust();
        lockedEffect.setInput(unlockedEffect);
        lockedEffect.setBrightness(-0.5);

        abilityImage.effectProperty().bind(
                Bindings.createObjectBinding(
                        () -> unlocked.get() ? unlockedEffect : lockedEffect,
                        unlocked
                )
        );
    }

}