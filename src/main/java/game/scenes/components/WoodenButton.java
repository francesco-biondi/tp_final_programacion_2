package game.scenes.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class WoodenButton extends Button {
    public WoodenButton() {
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
    }
}
