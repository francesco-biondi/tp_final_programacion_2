package game.scenes.components;

import game.scenes.dependencies.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.*;

public class AbilityBar extends HBox {

    static final int MAX_ABILITY_CAPACITY = 4;
    List<String> abilityDeck;
    Map<String, WoodenButton> buttonMap = new LinkedHashMap<>();

    public AbilityBar() {
        FXMLLoader loader = new FXMLLoader(
                AbilityBar.class.getResource(
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
        abilityDeck = GameManager.getCurrentPlayer().getAbilityDeck();

        for (String ability : abilityDeck) {
            WoodenButton button = newButton(ability);
            buttonMap.put(ability, button);
            getChildren().add(button);
        }
    }

    private WoodenButton newButton(String buttonText) {
        WoodenButton button = new WoodenButton(buttonText);
        button.setOnMouseClicked(this::onButtonPressed);
        button.setOnDragOver(this::onDragOver);
        button.setOnDragDropped(this::onDragDropped);
        return button;
    }

    @FXML
    void addButton(DragEvent event) {
        String droppedText = event.getDragboard().getString();
        if(abilityDeck.size() < MAX_ABILITY_CAPACITY && !abilityDeck.contains(droppedText)) {
            abilityDeck.add(droppedText);
            WoodenButton button = newButton(droppedText);
            buttonMap.put(droppedText, button);
            getChildren().add(button);
            updatePlayerAbilityDeck();
            event.consume();
        }
    }

    @FXML
    void onButtonPressed(MouseEvent event) {
        WoodenButton button = (WoodenButton) event.getSource();

        System.out.println(button.getText());
        System.out.println(abilityDeck);
        // Aca va la logica para usar la habilidad
    }

    @FXML
    void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);

        event.consume();
    }

    @FXML
    void onDragDropped(DragEvent event) {
        String droppedAbility = event.getDragboard().getString();
        WoodenButton eventButton = (WoodenButton) event.getSource();

        if (buttonMap.containsKey(droppedAbility)) {
            swapAbilities(eventButton.getText(), droppedAbility);
            WoodenButton existingButton = buttonMap.get(droppedAbility);

            existingButton.setText(eventButton.getText());
            eventButton.setText(droppedAbility);

            buttonMap.replace(existingButton.getText(), existingButton);
            buttonMap.replace(droppedAbility, eventButton);
        } else{
            replaceAbility(eventButton.getText(), droppedAbility);
            buttonMap.remove(eventButton.getText());
            eventButton.setText(droppedAbility);
            buttonMap.put(droppedAbility, eventButton);
        }
        updatePlayerAbilityDeck();
        event.setDropCompleted(true);
        event.consume();
    }

    private void swapAbilities(String eventText, String droppedText) {
        Collections.swap(abilityDeck, abilityDeck.indexOf(eventText), abilityDeck.indexOf(droppedText));
    }

    private void replaceAbility(String eventText, String droppedText) {
        abilityDeck.set(abilityDeck.indexOf(eventText), droppedText);
    }

    private void updatePlayerAbilityDeck(){
        GameManager.getCurrentPlayer().setAbilityDeck(abilityDeck);
    }

}
