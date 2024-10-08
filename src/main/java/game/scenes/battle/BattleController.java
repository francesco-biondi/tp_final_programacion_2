package game.scenes.battle;

import game.models.characters.Enemy;
import game.models.saves.dependencies.SaveManager;
import game.scenes.battle.dependencies.ButtonManager;
import game.scenes.battle.dependencies.CursorManager;
import game.scenes.battle.dependencies.ShopManager;
import game.scenes.dependencies.GameManager;
import game.scenes.dependencies.SceneManager;
import game.scenes.dependencies.Scenes;
import game.services.SchedulerService;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class BattleController {

    @FXML
    private Text enemyName, playerName, playerBounty, gold;

    @FXML
    private StackPane root;

    @FXML
    private GridPane battle;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private TabPane shopPane;

    @FXML
    private ImageView enemyImage;

    @FXML
    public void initialize(){
        playerName.setText(GameManager.getCurrentPlayer().getName());
        playerBounty.setText(String.valueOf(GameManager.getCurrentPlayer().getBounty()));
        gold.textProperty().bind(GameManager.getCurrentPlayer().goldProperty());
        updateEnemy(GameManager.getCurrentEnemy());
        ShopManager.configureStore(shopPane);
        configureBattle();

    }

    void configureBattle() {
        DoubleProperty widthScale = new SimpleDoubleProperty();
        DoubleProperty heightScale = new SimpleDoubleProperty();

        widthScale.bind(root.widthProperty().divide(1280.0));
        heightScale.bind(root.heightProperty().divide(800.0));

        battle.scaleXProperty().bind(Bindings.min(widthScale, heightScale));
        battle.scaleYProperty().bind(Bindings.min(widthScale, heightScale));
    }

    @FXML
    void toggleShop(MouseEvent event){
        ShopManager.toggleStore(shopPane);
    }

    @FXML
    void shopBuy(MouseEvent event){
        ButtonManager.updateButtonState((StackPane) event.getSource(), 1);
        ShopManager.buy();
    }

    @FXML
    void hitEnemy(MouseEvent event) {
        GameManager.getCurrentEnemy().setHealth(GameManager.getCurrentEnemy().getHealth() - 500);
        GameManager.getCurrentPlayer().setGold(GameManager.getCurrentPlayer().getGold() + GameManager.getCurrentEnemy().getId());
    }

    @FXML
    void button_pressed(MouseEvent event) {
        ButtonManager.updateButtonState((StackPane) event.getSource(), 1);
    }

    @FXML
    void handleCursor(MouseEvent event) {
        ImageView enemy = (ImageView) event.getSource();
        boolean isHovering = event.getEventType().equals(MouseEvent.MOUSE_ENTERED);
        if (isHovering) {
            CursorManager.setCursorToFist();
        } else {
            CursorManager.resetCursor();
        };
    }

    @FXML
    void toggleMap(MouseEvent event) {
        SceneManager.changeScene(Scenes.MAP);
    }

    @FXML
    void mainMenu(MouseEvent event) {
        SceneManager.changeScene(Scenes.MAIN_MENU);
        SaveManager.saveGame(GameManager.getCurrentSave());
    }

    void updateEnemy(Enemy enemy){
        healthBar.progressProperty().bind(GameManager.getCurrentEnemy().healthProperty().divide(GameManager.getCurrentEnemy().MAX_HEALTH));
        enemyImage.setImage(new Image(enemy.getImage()));
        enemyName.setText(enemy.getName());
    }

}
