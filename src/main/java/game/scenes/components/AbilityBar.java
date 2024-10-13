package game.scenes.components;

import game.models.abilities.Ability;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import java.io.IOException;

public class AbilityBar extends ButtonBar {

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
    void onButtonPressed(MouseEvent event) {
        WoodenButton woodenButton = (WoodenButton) event.getSource();
        System.out.println(woodenButton.getId());
    }

    @FXML
    void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    @FXML
    void onDragDropped(DragEvent event) {
        WoodenButton woodenButton = (WoodenButton) event.getSource();
        woodenButton.setText(event.getDragboard().getString());
        event.setDropCompleted(true);

        event.consume();
    }

    @FXML
    void onDragDone(DragEvent event) {}
}
