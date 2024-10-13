package game.scenes.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilityBar extends HBox {

    Map<String, WoodenButton> abilityDeck = new HashMap<>();

    public AbilityBar() {
        FXMLLoader loader = new FXMLLoader(
                ItemPane.class.getResource(
                        "ability-bar.fxml"
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
    private void initialize() {
    }

    @FXML
    void addButton(DragEvent event) {
        WoodenButton button = new WoodenButton(event.getDragboard().getString());

        if(abilityDeck.size() < 4 && !abilityDeck.containsKey(button.getText())) {
            abilityDeck.put(button.getText(), button);
            button.setOnMouseClicked(this::onButtonPressed);
            button.setOnDragOver(this::onDragOver);
            button.setOnDragDropped(this::onDragDropped);
            getChildren().add(button);
            event.consume();
        }
    }

    @FXML
    void onButtonPressed(MouseEvent event) {
        WoodenButton button = (WoodenButton) event.getSource();

        // Codigo para usar la habilidad en base al button.getText()
    }

    @FXML
    void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    @FXML
    void onDragDropped(DragEvent event) {
        WoodenButton button = (WoodenButton) event.getSource();

        abilityDeck.remove(event.getDragboard().getString());

        button.setText(event.getDragboard().getString());
        event.setDropCompleted(true);

        event.consume();
    }

}
