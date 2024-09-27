package com.tpfinalprogramacion2.scenes.dependencies;

import com.tpfinalprogramacion2.App;
import com.tpfinalprogramacion2.models.resource.Resource;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public abstract class SceneManager {
    private static Scene scene;
    private static MediaPlayer backgroundMusic;

    public static void setMainScene(Scene scene) {
        SceneManager.scene = scene;
    }

    public static void changeScene(String fxmlView) {
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

    private static void applyFadeTransition(Parent root) {

        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), scene.getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            scene.setRoot(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }

    public static void setBackgroundMusic(Media media, double volume) {
        backgroundMusic = new MediaPlayer(media);
        backgroundMusic.setVolume(volume);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    public static MediaPlayer getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void toggleMusic() {
        backgroundMusic.setMute(!backgroundMusic.isMute());
    }

}
