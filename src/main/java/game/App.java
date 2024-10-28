package game;

import game.scenes.dependencies.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static game.services.Resource.*;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream(APP_FONT_ONE_PIECE), 100);
        Font.loadFont(getClass().getResourceAsStream(APP_FONT_PRESS_START), 70);

        configureStage(stage);
        stage.show();
    }

    public void configureStage(Stage stage) {
        Scene scene = new Scene(SceneManager.getRoot(VIEW_MAIN_MENU));
        stage.setScene(scene);
        SceneManager.setScene(scene);
        stage.getIcons().add(new Image(APP_ICON));
        stage.setTitle("One Piece");
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch();
    }
}