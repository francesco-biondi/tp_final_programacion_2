package game.scenes.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PosterPane extends StackPane {

    public PosterPane() {
        FXMLLoader loader = new FXMLLoader(
                ItemPane.class.getResource(
                        "poster-pane.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
