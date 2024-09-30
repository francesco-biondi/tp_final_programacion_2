package game.scenes.dependencies;

import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EffectManager {

    private static final Map<Node, Effect> originalEffects = new HashMap<>();

    public static void applyVisualEffect(MouseEvent event, Consumer<Node> effectOnHover) {
        Node node = (Node) event.getSource();

        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            originalEffects.putIfAbsent(node, node.getEffect());
            effectOnHover.accept(node);
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            Effect originalEffect = originalEffects.get(node);
            node.setEffect(originalEffect);
            originalEffects.remove(node);
        }
    }

    public static void applyGlowEffect(MouseEvent event) {
        applyVisualEffect(event,
                node -> node.setEffect(new Glow(0.8)));
    }

    public static void applyDropShadowEffect(MouseEvent event) {
        applyVisualEffect(event,
                node -> node.setEffect(new DropShadow()));
    }

    public static void applyScaleEffect(MouseEvent event, double scaleFactor) {
        Node node = (Node) event.getSource();
        boolean isHovering = event.getEventType() == MouseEvent.MOUSE_ENTERED;
        node.setScaleX(isHovering ? scaleFactor : 1);
        node.setScaleY(isHovering ? scaleFactor : 1);
    }

}