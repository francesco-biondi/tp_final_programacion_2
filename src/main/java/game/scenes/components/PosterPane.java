package game.scenes.components;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PosterPane extends StackPane {

    @FXML
    private ImageView pin;

    @FXML
    private ImageView posterImage;

    private double initialMouseX;
    private double initialMouseY;
    private double initialPosterX;
    private double initialPosterY;

    public PosterPane() {
        FXMLLoader loader = new FXMLLoader(
                PosterPane.class.getResource(
                        "poster-pane.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setOnMouseDragged(this::handleMouseDragged);
    }

    private void handleMouseDragged(MouseEvent event) {

        double mouseX = this.sceneToLocal(event.getSceneX(), event.getSceneY()).getX();
        double mouseY = this.sceneToLocal(event.getSceneX(), event.getSceneY()).getY();

        double pinX = pin.getBoundsInParent().getCenterX();
        double pinY = pin.getBoundsInParent().getCenterY();

        double deltaX = mouseX - pinX;
        double deltaY = mouseY - pinY;
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX)) - 90;

        double centerX = posterImage.getBoundsInLocal().getCenterX();

        posterImage.getTransforms().clear();
        Rotate rotate = new Rotate(angle, centerX, 15);
        posterImage.getTransforms().add(rotate);

    }

    public void setPosterImage(Image image) {
        posterImage.setImage(image);
    }

}
