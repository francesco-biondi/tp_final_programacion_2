package game.scenes.map;

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

    void configureMap() {
        Scale scale = new Scale();
        scale.xProperty().bind(root.widthProperty().divide(1280));
        scale.yProperty().bind(root.heightProperty().divide(800));

        levels.getTransforms().add(scale);
    }

}
