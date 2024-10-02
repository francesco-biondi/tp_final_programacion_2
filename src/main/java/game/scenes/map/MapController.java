package game.scenes.map;

import game.scenes.dependencies.EffectManager;
import game.scenes.dependencies.GameManager;
import game.scenes.dependencies.SceneManager;
import game.scenes.dependencies.Scenes;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class MapController {

    @FXML
    private AnchorPane root;

    @FXML
    private Pane map;

    @FXML
    void initialize() {
        displayLevels();
        configureMap();
    }

    @FXML
    void handleHover(MouseEvent event) {
        EffectManager.applyGlowEffect(event);
    }

    @FXML
    void selectLevel(MouseEvent event) {
        ImageView level = (ImageView) event.getSource();
        GameManager.setEnemy((Integer.parseInt(level.getId().replace("lvl", ""))));
        SceneManager.changeScene(Scenes.BATTLE);
    }

    void configureMap() {
        Scale scale = new Scale();
        scale.xProperty().bind(root.widthProperty().divide(1280));
        scale.yProperty().bind(root.heightProperty().divide(800));

        map.getTransforms().add(scale);
    }

    void displayLevels() {
        int i = 0;
        for(Node node : map.getChildren()) {
            if(node instanceof Group level) {
                level.visibleProperty().bind(GameManager.getCurrentSave().getEnemy(i).unlockedProperty());
                level.getChildren().get(1).visibleProperty().bind(GameManager.getCurrentSave().getEnemy(i++).defeatedProperty());
            }
        }
    }

}
