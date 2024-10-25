package game.scenes.dependencies;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;

public class EffectManager {

    private static final Map<Node, Effect> originalEffects = new HashMap<>();
    private static boolean isAnimating = false;

    public static void damageEnemyEffect(ImageView enemy, int movementFactor){
        if (!isAnimating) {

            isAnimating = true;

            ColorAdjust hitEffect = new ColorAdjust();
            hitEffect.setBrightness(0.5);
            enemy.setEffect(hitEffect);


            TranslateTransition shake = new TranslateTransition(Duration.millis(30), enemy);

            shake.setByX(movementFactor);
            shake.setCycleCount(movementFactor);

            shake.setAutoReverse(true);
            shake.play();

            shake.setOnFinished(e -> {
                enemy.setEffect(null);
                isAnimating = false;
            });

        }
    }

    public static void applyVisualEffect(Node node, Effect effect) {
        originalEffects.putIfAbsent(node, node.getEffect());
        node.setEffect(effect);
    }

    public static void resetVisualEffect(Node node) {
        if(originalEffects.containsKey(node)) {
            Effect originalEffect = originalEffects.get(node);
            node.setEffect(originalEffect);
            originalEffects.remove(node);
        }
    }

    public static void applyScaleEffect(MouseEvent event, double scaleFactor) {
        Node node = (Node) event.getSource();
        boolean isHovering = event.getEventType() == MouseEvent.MOUSE_ENTERED;
        node.setScaleX(isHovering ? scaleFactor : 1);
        node.setScaleY(isHovering ? scaleFactor : 1);
    }

}