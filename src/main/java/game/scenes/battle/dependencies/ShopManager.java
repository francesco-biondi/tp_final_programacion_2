package game.scenes.battle.dependencies;

import game.scenes.battle.BattleController;
import game.scenes.dependencies.GameManager;
import javafx.animation.*;
import javafx.scene.control.TabPane;
import javafx.util.Duration;

public class ShopManager {

    public static boolean isOpen = false, isAnimating = false;
    private static TranslateTransition translateTransition;

    public static void configureStore(TabPane shopPane) {
        shopPane.setTranslateX(-400);
        translateTransition = new TranslateTransition(Duration.millis(600), shopPane);
        shopPane.setCache(true);
        shopPane.setCacheShape(true);
    }

    public static void toggleStore(TabPane shopPane) {
        if(!isAnimating){
            isAnimating = true;
            double startX = isOpen ? 0 : -400;
            double endX = isOpen ? -400 : 0;

            translateTransition.setFromX(startX);
            translateTransition.setToX(endX);

            translateTransition.play();

            translateTransition.setOnFinished(e -> {
                isOpen = !isOpen;
                isAnimating = false;
            });
        }
    }

    public static void buy(){

    }
}
