package game.scenes.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;

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
}
