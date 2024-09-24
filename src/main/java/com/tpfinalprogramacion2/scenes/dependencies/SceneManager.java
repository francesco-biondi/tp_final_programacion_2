package com.tpfinalprogramacion2.scenes.dependencies;

import com.tpfinalprogramacion2.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;


public abstract class SceneManager {
    private static Stage stage;
    private static MediaPlayer backgroundMusic;

    public static void setStage(Stage stage){
        SceneManager.stage = stage;
    }

    private static Scene loadScene(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlPath));
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeScene(String fxmlView) {
        if (stage != null) {
            Scene scene = loadScene(fxmlView);
            stage.setScene(scene);
        }
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
