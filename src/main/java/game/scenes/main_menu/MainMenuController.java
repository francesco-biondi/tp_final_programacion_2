package game.scenes.main_menu;

import game.models.exceptions.InsufficientGoldException;
import game.models.exceptions.InvalidNameException;
import game.models.exceptions.SaveNotFoundException;
import game.models.shop.Shop;
import game.scenes.dependencies.*;
import game.services.Resource;
import game.models.saves.dependencies.SaveManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


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
        SoundManager.playSound(Resource.MAIN_MENU_BUTTON_CLICK_SOUND_PATH, 0.1);
        handleTextAction(Integer.parseInt(clickedText.getId().replace("text_", "")));
    }

    @FXML
    private void toggleMusic(MouseEvent event) {
        ImageView musicIcon = (ImageView) event.getSource();
        SoundManager.updateMusicIcon(musicIcon);
        SoundManager.toggleMusic();
    }

    private void handleTextFieldAction(KeyEvent event, int index) throws InvalidNameException {
        TextField currentTextField = (TextField) event.getSource();
        switch (event.getCode()) {
            case ENTER -> {
                String saveName = currentTextField.getText().trim();
                try {
                    SaveManager.newGame(saveName, index);
                    GameManager.setCurrentSave(SaveManager.loadGame(index));
                    updateSlotTexts(true);
                    SceneManager.changeScene(Scenes.MAP);
                } catch (InvalidNameException e) {
                    NotificationManager.toolTip(currentTextField, "Nombre inválido. Ingrese un nombre de entre 3 y 10 caracteres.", "error", 500);
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
        updateSlotTexts(true);
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
            try {
                GameManager.setCurrentSave(SaveManager.loadGame(index));
                SceneManager.changeScene(Scenes.MAP);
            } catch (SaveNotFoundException e){
                NotificationManager.toolTip(slotTexts[index], e.getMessage(), "error", 300);
            }
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

    private boolean showConfirmationDialog() {
        return NotificationManager.alert("Sobrescribir", null, "¿Estás seguro de sobrescribir el archivo guardado?");
    }
}

