package com.tpfinalprogramacion2;

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
        Font.loadFont(getClass().getResourceAsStream("/assets/fonts/ONE PIECE.ttf"), 100);
        Font.loadFont(getClass().getResourceAsStream("/assets/fonts/PressStart2P-Regular.ttf"), 70);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("file:src/main/resources/assets/icons/opf_icon.png");
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