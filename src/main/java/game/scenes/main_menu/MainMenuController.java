package game.scenes.main_menu;

import game.scenes.dependencies.EffectManager;
import game.scenes.dependencies.Scenes;
import game.scenes.dependencies.SoundManager;
import game.services.Resource;
import game.models.saves.dependencies.SaveManager;
import game.scenes.dependencies.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainMenuController {

    private MainMenuState menuState;

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
        SoundManager.setBackgroundMusic(Resource.MAIN_MENU_BACKGROUND_MUSIC_PATH, 0.15);
    }


    @FXML
    private void handleHover(MouseEvent event) {
        EffectManager.applyScaleEffect(event, 1.3);
    }

    @FXML
    private void clicked_text(MouseEvent event) {
        Text clickedText = (Text) event.getSource();
        SoundManager.playButtonSound(0.1);
        handleTextAction(Integer.parseInt(clickedText.getId().replace("text_", "")));
    }

    @FXML
    private void toggleMusic(MouseEvent event) {
        ImageView musicIcon = (ImageView) event.getSource();
        SoundManager.updateMusicIcon(musicIcon);
        SoundManager.toggleMusic();
    }

    private void handleTextFieldAction(KeyEvent event, int index) {
        TextField currentTextField = (TextField) event.getSource();
        switch (event.getCode()) {
            case ENTER -> {
                String saveName = currentTextField.getText().trim();
                try {
                    SaveManager.newGame(saveName, index);
                    updateSlotTexts(true);
                    SceneManager.changeScene(Scenes.BATTLE);
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
            if (SaveManager.saveExists(index)) {
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
            slotTexts[i].setText(SaveManager.saveExists(i) ? SaveManager.loadGame(i).toString() : (isNewGame ? "NEW GAME" : "Empty"));
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

