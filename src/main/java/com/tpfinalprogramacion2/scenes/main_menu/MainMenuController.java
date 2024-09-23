package com.tpfinalprogramacion2.scenes.main_menu;

import com.tpfinalprogramacion2.services.SaveManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenuController {

    private static final String BACKGROUND_MUSIC_PATH = "/assets/music/One_Piece_OST_Overtaken.mp3";
    private static final String BUTTON_CLICK_SOUND_PATH = "/assets/sounds/punch.mp3";
    private static final String MUSIC_ON_ICON_PATH = "file:src/main/resources/assets/icons/musica.png";
    private static final String MUSIC_OFF_ICON_PATH = "file:src/main/resources/assets/icons/sin-musica.png";

    private MainMenuState menuState;

    private final MediaPlayer backgroundMusic = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource(BACKGROUND_MUSIC_PATH)).toExternalForm()));
    private final AudioClip buttonSound = new AudioClip(Objects.requireNonNull(getClass().getResource(BUTTON_CLICK_SOUND_PATH)).toExternalForm());

    @FXML
    private TextField text_field_0, text_field_1, text_field_2;
    private TextField[] slotTextFields;

    @FXML
    private Text text_0, text_1, text_2, text_3;
    private Text[] slotTexts;

    @FXML
    public void initialize() {
        slotTextFields = new TextField[]{text_field_0, text_field_1, text_field_2};
        slotTexts = new Text[]{text_0, text_1, text_2, text_3};
        initializeMainMenu();
        configureBackgroundMusic();
        buttonSound.setVolume(0.1);
    }


    @FXML
    private void handleHover(MouseEvent event) {
        Node node = (Node) event.getSource();
        if (event.getTarget() instanceof Text) {
            node = (Text) event.getTarget();
        } else if (event.getTarget() instanceof ImageView) {
            node = (ImageView) event.getTarget();
        }
        boolean isHovering = event.getEventType() == MouseEvent.MOUSE_ENTERED;
        node.setScaleX(isHovering ? 1.3 : 1);
        node.setScaleY(isHovering ? 1.3 : 1);
        node.setEffect(isHovering ? new DropShadow() : null);
    }

    @FXML
    private void clicked_text(MouseEvent event) {
        Text clickedText = (Text) event.getSource();
        buttonSound.play();
        handleTextAction(Integer.parseInt(clickedText.getId().replace("text_", "")));
    }

    @FXML
    private void toggleMusic(MouseEvent event) {
        ImageView musicIcon = (ImageView) event.getSource();
        boolean isMuted = backgroundMusic.isMute();
        musicIcon.setImage(new Image(isMuted ? MUSIC_ON_ICON_PATH : MUSIC_OFF_ICON_PATH));
        backgroundMusic.setMute(!isMuted);
    }

    private void handleTextFieldAction(KeyEvent event, int index) {
        TextField currentTextField = (TextField) event.getSource();
        switch (event.getCode()) {
            case ENTER -> {
                String saveName = currentTextField.getText().trim();
                try {
                    SaveManager.newGame(saveName, index);
                    updateSlotTexts(true);
                } catch (IllegalArgumentException e) {
                    currentTextField.clear();
                    showError(currentTextField);
                }
            }
            case ESCAPE -> updateSlotTexts(true);
        }

    }

    private void handleTextAction(int index) {
        switch (menuState) {
            case MAIN_MENU -> handleMainMenuAction(index);
            case NEW_GAME -> handleNewGameAction(index);
            case LOAD_GAME -> handleLoadGameAction(index);
        }
    }

    private void handleMainMenuAction(int index) {
        switch (index) {
            case 0 -> updateSlotTexts(true);
            case 1 -> updateSlotTexts(false);
            case 2 -> System.exit(0);
        }
    }

    private void handleNewGameAction(int index) {
        if (index < slotTexts.length - 1) {
            if (SaveManager.slotExists(index)) {
                boolean confirmed = showConfirmationDialog();
                if (confirmed) {
                    showTextField(index);
                }
            } else {
                showTextField(index);
            }
        } else {
            initializeMainMenu();
        }
    }

    private void handleLoadGameAction(int index) {
        if (index < slotTexts.length - 1) {
            SaveManager.loadGame(index);
        } else {
            initializeMainMenu();
        }
    }

    private void configureBackgroundMusic() {
        backgroundMusic.setVolume(0.15);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    @FXML
    private void initializeMainMenu() {
        setTextsVisible();
        clearTextFields();
        text_0.setText("NEW GAME");
        text_1.setText("LOAD GAME");
        text_2.setText("EXIT");
        text_3.setText(null);
        menuState = MainMenuState.MAIN_MENU;
    }

    @FXML
    private void updateSlotTexts(boolean isNewGame) {
        setTextsVisible();
        for (int i = 0; i < slotTexts.length - 1; i++) {
            slotTexts[i].setText(SaveManager.slotExists(i) ? SaveManager.loadGame(i).toString() : (isNewGame ? "NEW GAME" : "Empty"));
        }
        text_3.setText("BACK");
        menuState = isNewGame ? MainMenuState.NEW_GAME : MainMenuState.LOAD_GAME;
        clearTextFields();
    }

    private void showTextField(int index) {
        slotTexts[index].setVisible(false);
        slotTextFields[index].setDisable(false);
        slotTextFields[index].requestFocus();
        slotTextFields[index].setOnKeyPressed(event -> handleTextFieldAction(event, index));
    }

    private void setTextsVisible() {
        for (Text text : slotTexts) {
            text.setVisible(true);
        }
    }

    private void clearTextFields() {
        for (TextField textField : slotTextFields) {
            textField.clear();
            textField.setStyle(null);
            textField.setDisable(true);
        }
    }

    private void showError(TextField textField) {
        Tooltip tooltip = new Tooltip("Nombre inválido. Ingrese un nombre de entre 3 y 10 caracteres.");
        tooltip.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        tooltip.setAutoHide(true);
        tooltip.show(textField.getScene().getWindow(), textField.getScene().getWindow().getX() + textField.getLayoutX(),textField.getScene().getWindow().getY() + textField.getLayoutY());
    }

    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sobrescribir");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de sobrescribir el archivo guardado?");

        ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Stage stage = (Stage) slotTexts[0].getScene().getWindow();
        alert.initOwner(stage);
        alert.initModality(Modality.WINDOW_MODAL);

        return alert.showAndWait().orElse(buttonTypeNo) == buttonTypeYes;
    }
}
