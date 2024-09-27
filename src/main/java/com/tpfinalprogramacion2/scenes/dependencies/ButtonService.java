package com.tpfinalprogramacion2.scenes.dependencies;


import com.tpfinalprogramacion2.models.resource.Resource;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

public class ButtonService {

    public static void updateButtonState(StackPane stackPane, int cooldown) {
        ImageView button = (ImageView) stackPane.getChildren().get(0);
        Text buttonText = (Text) stackPane.getChildren().get(1);

        button.setImage(new Image(Resource.pressedButtonImagePath));
        buttonText.setTranslateY(5);

        ColorAdjust textEffect = new ColorAdjust();
        textEffect.setBrightness(-0.5);
        buttonText.setEffect(textEffect);

        SchedulerService.getScheduler().schedule(() -> {
            resetButton(button, buttonText);
        },  cooldown, TimeUnit.SECONDS);
    }

    private static void resetButton(ImageView button, Text buttonText) {
        button.setImage(new Image(Resource.normalButtonImagePath));
        buttonText.setTranslateY(-5);
        buttonText.setEffect(null);
    }

}
