package com.tpfinalprogramacion2.scenes.main_menu;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class MainMenuController {

    private MainMenuState menuState;
    private final Media sound = new Media(getClass().getResource("/assets/music/One_Piece_OST_Overtaken.mp3").toExternalForm());
    private AudioClip  buttonSound = new AudioClip(getClass().getResource("/assets/sounds/button_click.mp3").toExternalForm());

    @FXML
    Text text_1, text_2, text_3, text_4;

    @FXML
    public void initialize() {
        mainMenu();


        MediaPlayer backgroundMusic = new MediaPlayer(sound);

        backgroundMusic.setVolume(0.15);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    @FXML
    void selected_text(MouseEvent event) {
        Text text = (Text) event.getSource();
        text.setScaleX(1.3);
        text.setScaleY(1.3);
        text.setEffect(new DropShadow());
    }

    @FXML
    void reset_text(MouseEvent event) {
        Text text = (Text) event.getSource();
        text.setEffect(null);
        text.setScaleX(1);
        text.setScaleY(1);
    }

    @FXML
    void clicked_text(MouseEvent event) {
        Text clickedText = (Text) event.getSource();
        buttonSound.play();
        switch (clickedText.getId()) {
            case "text_1" -> handleText1Action();
            case "text_2" -> handleText2Action();
            case "text_3" -> handleText3Action();
            case "text_4" -> handleText4Action();
        }
    }

    private void handleText1Action() {
        switch (menuState) {
            case MAIN_MENU -> newGame();
            case NEW_GAME -> {} // Aca va la logica de New Game
            case LOAD_GAME -> {} // Aca va la logica de Load Game
        }
    }

    private void handleText2Action() {
        switch (menuState) {
            case MAIN_MENU -> loadGame();
            case NEW_GAME -> {} // Aca va la logica de New Game
            case LOAD_GAME -> {} // Aca va la logica de Load Game
        }
    }

    private void handleText3Action() {
        switch (menuState) {
            case MAIN_MENU -> System.exit(0);
            case NEW_GAME -> {} // Aca va la logica de New Game
            case LOAD_GAME -> {} // Aca va la logica de Load Game
        }
    }

    private void handleText4Action() {
        if (menuState != MainMenuState.MAIN_MENU) {
            mainMenu();
        }
    }

    @FXML
    void mainMenu() {
        text_1.setText("NEW GAME");
        text_2.setText("LOAD GAME");
        text_3.setText("EXIT");
        text_4.setText(null);
        menuState = MainMenuState.MAIN_MENU;
    }

    @FXML
    void newGame() {
        text_1.setText("NEW GAME");
        text_2.setText("NEW GAME");
        text_3.setText("NEW GAME");
        text_4.setText("BACK");
        menuState = MainMenuState.NEW_GAME;
    }

    @FXML
    void loadGame() {
        text_1.setText("GAME 1");
        text_2.setText("GAME 2");
        text_3.setText("GAME 3");
        text_4.setText("BACK");
        menuState = MainMenuState.LOAD_GAME;
    }

}