package game.scenes.components;

import game.models.abilities.Ability;
import game.models.abilities.Nakama;
import game.models.abilities.enums.AbilityNames;
import game.models.exceptions.InsufficientGoldException;
import game.models.shop.Shop;
import game.scenes.dependencies.GameManager;
import game.scenes.dependencies.NotificationManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

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
    private Ability ability;

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
    }

    @FXML
    private void buy(MouseEvent event) {
        try {
            Shop.buyItem(ability);
            this.description = ability.toString();
        } catch (InsufficientGoldException e){
            NotificationManager.toolTip(buyButton, e.getMessage(), "error", 200);
        }
    }

    @FXML
    private void displayAbilityInfo(MouseEvent event) {
        NotificationManager.toolTip(abilityImage, description, "gameInfo", 200);
    }

    @FXML
    private void handleDrag(MouseEvent event) {
        if(!(ability instanceof Nakama) && ability.isUnlocked() && !ability.getName().equals("ATTACK")) {
            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putImage(abilityImage.getImage());
            content.putString(abilityName.getText());

            dragboard.setContent(content);

            event.consume();
        }
    }

    public void setShopItemData(Ability ability) {
        this.ability = ability;
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