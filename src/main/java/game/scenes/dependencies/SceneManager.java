package game.scenes.dependencies;

import game.App;
import game.services.Resource;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;


public abstract class SceneManager {
    private static Scene scene;

    public static void setScene(Scene scene) {
        SceneManager.scene = scene;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void changeScene(Scenes scene) {
        String fxmlView = switch (scene){
            case MAIN_MENU -> Resource.VIEW_MAIN_MENU;
            case MAP -> Resource.VIEW_MAP;
            case BATTLE -> Resource.VIEW_BATTLE;
        };
        Parent root = getRoot(fxmlView);
        applyFadeTransition(root);
    }

    public static Parent getRoot(String fxmlView) {
        try {
            return new FXMLLoader(App.class.getResource(fxmlView)).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void applyFadeTransition(Parent newRoot) {

        Parent currentRoot = scene.getRoot();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        newRoot.setOpacity(0.0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newRoot);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);


        fadeOut.setOnFinished(event -> {
            scene.setRoot(newRoot);
            fadeIn.play();
        });

        fadeOut.play();
    }

}
