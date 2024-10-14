package game.scenes.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.Serializable;

public class WoodenButton extends Button implements Serializable {

    public WoodenButton(String text) {
        initialize();
        setText(text);
    }

    public WoodenButton() {
        initialize();
    }

    private void initialize(){
        FXMLLoader loader = new FXMLLoader(
                ItemPane.class.getResource(
                        "wooden-button.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        managedProperty().bind(textProperty().isNotEmpty());
        visibleProperty().bind(textProperty().isNotEmpty());
    }


}
