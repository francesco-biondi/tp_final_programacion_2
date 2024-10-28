package game.scenes.battle;

import game.models.characters.Enemy;
import game.models.saves.dependencies.SaveManager;
import game.scenes.components.ShopPane;
import game.scenes.battle.dependencies.CursorManager;
import game.scenes.dependencies.*;
import game.services.Resource;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class BattleController {

    @FXML
    private Text enemyName, gold;

    @FXML
    private StackPane root;

    @FXML
    private GridPane battle;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private ShopPane shopPane;

    @FXML
    private ImageView enemyImage;

    @FXML
    public void initialize(){
        SoundManager.setBackgroundMusic(Resource.BATTLE_BACKGROUND_MUSIC_PATH, 0.15);
        configureScale();
        gold.textProperty().bind(GameManager.getCurrentPlayer().goldProperty());
        updateEnemy(GameManager.getCurrentEnemy());
//        GameManager.startAttackNakamas();
//        El metodo startAttackNakamas bugea el enemigo y hace que se desbloqueen varios niveles
//        puede ser porque se inicia el ataque cada vez que salis y entras y se acumula
    }

    void configureScale() {
        DoubleProperty widthScale = new SimpleDoubleProperty();
        DoubleProperty heightScale = new SimpleDoubleProperty();

        widthScale.bind(root.widthProperty().divide(1280.0));
        heightScale.bind(root.heightProperty().divide(800.0));

        battle.scaleXProperty().bind(Bindings.min(widthScale, heightScale));
        battle.scaleYProperty().bind(Bindings.min(widthScale, heightScale));
    }

    @FXML
    void toggleShop(MouseEvent event){
        shopPane.toggleShop();
    }

    @FXML
    void hitEnemy(MouseEvent event) {
        GameManager.hitBasicAttack();
        GameManager.goldByBasicAttack();
        EffectManager.damageEnemyEffect((ImageView) event.getSource(), 2);
    }

    @FXML
    void button_pressed(MouseEvent event) {

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
//        GameManager.stopAttackNakamas();
    }

    @FXML
    void mainMenu(MouseEvent event) {
        SceneManager.changeScene(Scenes.MAIN_MENU);
//        GameManager.stopAttackNakamas();
        SaveManager.saveGame(GameManager.getCurrentSave());
    }

    void updateEnemy(Enemy enemy){
        healthBar.progressProperty().bind(GameManager.getCurrentEnemy().healthProperty().divide(GameManager.getCurrentEnemy().MAX_HEALTH));
        enemyImage.setImage(new Image(enemy.getImage()));
        enemyName.setText(enemy.getName());
    }

}
