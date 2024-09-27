package com.tpfinalprogramacion2.scenes.battle;

import com.tpfinalprogramacion2.scenes.battle.dependencies.ShopService;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class BattleController {

    @FXML
    private TabPane shopPane;

    @FXML
    public void initialize(){
        ShopService.configureStore(shopPane);
    }

    @FXML
    void toggleShop(MouseEvent event){
        ShopService.toggleStore();
    }

    @FXML
    void attack(MouseEvent event) {

    }

    @FXML
    void button_pressed(MouseEvent event) {

    }

    @FXML
    void fist_cursor(MouseEvent event) {

    }

    @FXML
    void reset_cursor(MouseEvent event) {

    }

}
