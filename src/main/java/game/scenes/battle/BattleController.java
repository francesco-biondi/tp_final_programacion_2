package game.scenes.battle;

import game.models.abilities.enums.AbilityNames;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.saves.dependencies.SaveManager;
import game.scenes.components.AbilityBar;
import game.scenes.components.ShopPane;
import game.scenes.battle.dependencies.CursorManager;
import game.scenes.dependencies.*;
import game.services.Resource;
import game.services.SchedulerService;
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

    private final Player player = GameManager.getCurrentPlayer();
    private final Enemy enemy = GameManager.getCurrentEnemy();

    @FXML
    private Text enemyName, gold;

    @FXML
    private StackPane root;

    @FXML
    private GridPane battle;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private AbilityBar abilityBar;

    @FXML
    private ShopPane shopPane;

    @FXML
    private ImageView enemyImage;

    @FXML
    public void initialize(){
        SoundManager.setBackgroundMusic(Resource.BATTLE_BACKGROUND_MUSIC_PATH, 0.15);
        configureScale();
        gold.textProperty().bind(player.goldProperty().asString());
        updateEnemy(enemy);
        enemy.setImageView(enemyImage);
        GameManager.startAttackNakamas();
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
        GameManager.useAbility(GameManager.getCurrentPlayer().getAbility(AbilityNames.PUNCH));
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
        GameManager.stopAttackNakamas();
    }

    @FXML
    void mainMenu(MouseEvent event) {
        SceneManager.changeScene(Scenes.MAIN_MENU);
        GameManager.stopAttackNakamas();
        SaveManager.saveGame(GameManager.getCurrentSave());
    }

    void updateEnemy(Enemy enemy){
        healthBar.progressProperty().bind(enemy.healthProperty().divide(enemy.MAX_HEALTH));
        enemyImage.setImage(new Image(enemy.getImage()));
        enemyName.setText(enemy.getName());
    }

}
