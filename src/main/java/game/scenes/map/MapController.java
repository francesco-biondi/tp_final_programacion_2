package game.scenes.map;

import game.scenes.dependencies.EffectManager;
import game.scenes.dependencies.SceneManager;
import game.scenes.dependencies.Scenes;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

public class MapController {

    @FXML
    private AnchorPane root;

    @FXML
    private Group levels;

    @FXML
    void initialize() {
        configureMap();
    }

    @FXML
    void handleHover(MouseEvent event) {
        EffectManager.applyGlowEffect(event);
    }

    @FXML
    void selectLevel(MouseEvent event) {
        SceneManager.changeScene(Scenes.BATTLE);
    }

    void configureMap() {
        Scale scale = new Scale();
        scale.xProperty().bind(root.widthProperty().divide(1280));
        scale.yProperty().bind(root.heightProperty().divide(800));

        levels.getTransforms().add(scale);
    }

}
