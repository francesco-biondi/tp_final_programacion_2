package com.tpfinalprogramacion2.scenes.battle;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BattleController {

    @FXML
    private TabPane shopPane;

    boolean isOpen = false;
    TranslateTransition translateTransition;

    @FXML
    public void initialize(){
        translateTransition = new TranslateTransition(Duration.millis(600), shopPane);
        shopPane.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            shopPane.setTranslateX(-shopPane.getWidth()*1.5);
        });
    }

    public void toggleStore(TabPane shopPane) {

        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setFromX(isOpen ? 0 : -shopPane.getWidth()*1.5);
        translateTransition.setToX(isOpen ? -shopPane.getWidth()*1.5 : 0);
        translateTransition.play();
        isOpen = !isOpen;
    }

    @FXML
    void attack(MouseEvent event) {

    }

    @FXML
    void button_pressed(MouseEvent event) {
        toggleStore(shopPane);
    }

    @FXML
    void fist_cursor(MouseEvent event) {

    }

    @FXML
    void reset_cursor(MouseEvent event) {

    }

}
