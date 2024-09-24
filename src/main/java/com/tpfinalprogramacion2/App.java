package com.tpfinalprogramacion2;

import com.tpfinalprogramacion2.models.resource.Resource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream(Resource.APP_FONT_ONE_PIECE), 100);
        Font.loadFont(getClass().getResourceAsStream(Resource.APP_FONT_PRESS_START), 70);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Resource.APP_VIEW_MAIN_MENU));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image(Resource.APP_ICON);
        stage.getIcons().add(icon);
        stage.setTitle("One Piece");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}