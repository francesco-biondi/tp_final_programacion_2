package com.tpfinalprogramacion2.scenes.main_menu;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainMenuController {

    private MainMenuState menuState;

    @FXML
    Text text_1, text_2, text_3, text_4;

    @FXML
    public void initialize() {
        mainMenu();
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