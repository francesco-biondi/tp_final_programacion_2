package game.scenes.map;

import game.scenes.dependencies.*;
import game.services.Resource;
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
        SoundManager.setBackgroundMusic(Resource.MAP_BACKGROUND_MUSIC_PATH, 0.15);
        displayLevels();
        configureMap();
    }

    @FXML
    void handleHover(MouseEvent event) {
        EffectManager.applyScaleEffect(event, 1.1);
    }

    @FXML
    void selectLevel(MouseEvent event) {
        ImageView level = (ImageView) event.getSource();
        GameManager.setEnemy((Integer.parseInt(level.getId().replace("lvl", ""))));
        if (GameManager.getCurrentEnemy().isDefeated()) GameManager.getCurrentEnemy().setHealth(GameManager.getCurrentEnemy().MAX_HEALTH);
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
