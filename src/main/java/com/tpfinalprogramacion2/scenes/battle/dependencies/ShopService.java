package com.tpfinalprogramacion2.scenes.battle.dependencies;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TabPane;
import javafx.util.Duration;

public class ShopService {

    public static boolean isOpen = false;
    private static TranslateTransition translateTransition;

    public static void configureStore(TabPane shopPane) {
        shopPane.setCache(true);
        shopPane.setCacheShape(true);
        translateTransition = new TranslateTransition(Duration.millis(600), shopPane);
    }

    public static void toggleStore() {
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);

        double fromX = isOpen ? 0 : -400;
        double toX = isOpen ? -400 : 0;

        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.play();

        isOpen = !isOpen;
    }
}
