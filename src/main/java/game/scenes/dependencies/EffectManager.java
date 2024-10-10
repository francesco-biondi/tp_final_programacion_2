package game.scenes.dependencies;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class EffectManager {

    private static final Map<Node, Effect> originalEffects = new HashMap<>();

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