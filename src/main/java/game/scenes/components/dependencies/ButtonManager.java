package game.scenes.components.dependencies;


import game.services.Resource;
import game.services.SchedulerService;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

public class ButtonManager {

    public static void updateButtonState(StackPane stackPane, int cooldown) {
//        for (Node node : stackPane.getChildren()) {
//            if (node instanceof ImageView button) {
//                pressButton(button, cooldown);
//            }
//            else if (node instanceof Text buttonText) {
//                pressText(buttonText, cooldown);
//            }
//        }
    }

    private static void pressButton(ImageView button, int cooldown) {
//        button.setImage(new Image(Resource.pressedButtonImagePath));
//        SchedulerService.getScheduler().schedule(() -> resetButton(button), cooldown, TimeUnit.MILLISECONDS);
    }

    private static void pressText(Text buttonText, int cooldown) {
//        buttonText.setTranslateY(5);
//        ColorAdjust textEffect = new ColorAdjust();
//        textEffect.setBrightness(-0.5);
//        buttonText.setEffect(textEffect);
//        SchedulerService.getScheduler().schedule(() -> resetButtonText(buttonText), cooldown, TimeUnit.MILLISECONDS);
    }

    private static void resetButton(ImageView button) {
//        button.setImage(new Image(Resource.normalButtonImagePath));
    }

    private static void resetButtonText(Text buttonText) {
//        buttonText.setTranslateY(0);
//        buttonText.setEffect(null);
    }

}
