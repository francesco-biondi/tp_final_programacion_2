package game.scenes.battle;

import game.models.characters.Enemy;
import game.models.saves.dependencies.SaveManager;
import game.scenes.battle.dependencies.ButtonManager;
import game.scenes.battle.dependencies.CursorManager;
import game.scenes.battle.dependencies.ShopManager;
import game.scenes.dependencies.GameManager;
import game.scenes.dependencies.SceneManager;
import game.scenes.dependencies.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class BattleController {

    @FXML
    private Text enemyName;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private TabPane shopPane;

    @FXML
    private ImageView enemyImage;

    @FXML
    public void initialize(){

        updateEnemy(GameManager.currentEnemy);
        ShopManager.configureStore(shopPane);
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
        GameManager.currentEnemy.setHealth(GameManager.currentEnemy.getHealth() - 10);
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
            CursorManager.setCursorToFist(enemy.getScene());
        } else {
            CursorManager.resetCursor(enemy.getScene());
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
        healthBar.progressProperty().bind(GameManager.currentEnemy.healthProperty().divide(GameManager.currentEnemy.getMAX_HEALTH()));
        enemyImage.setImage(new Image(enemy.getImage()));
        enemyName.setText(enemy.getName());
    }

}
