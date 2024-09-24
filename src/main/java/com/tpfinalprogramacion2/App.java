package com.tpfinalprogramacion2;

import com.tpfinalprogramacion2.models.resource.Resource;
import com.tpfinalprogramacion2.scenes.dependencies.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream(Resource.APP_FONT_ONE_PIECE), 100);
        Font.loadFont(getClass().getResourceAsStream(Resource.APP_FONT_PRESS_START), 70);

        configureStage(stage);
        SceneManager.changeScene(Resource.VIEW_MAIN_MENU);

        stage.show();
    }
    
    public void configureStage(Stage stage) {
        SceneManager.setStage(stage);
        Image icon = new Image(Resource.APP_ICON);
        stage.getIcons().add(icon);
        stage.setTitle("One Piece");
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch();
    }
}