package com.tpfinalprogramacion2.scenes.battle;

import com.tpfinalprogramacion2.scenes.battle.dependencies.ButtonManager;
import com.tpfinalprogramacion2.scenes.battle.dependencies.CursorManager;
import com.tpfinalprogramacion2.scenes.battle.dependencies.ShopManager;
import com.tpfinalprogramacion2.scenes.dependencies.SceneManager;
import com.tpfinalprogramacion2.scenes.dependencies.Scenes;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class BattleController {

    @FXML
    private TabPane shopPane;

    @FXML
    private ImageView enemyImage;

    @FXML
    public void initialize(){
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
    }

}
