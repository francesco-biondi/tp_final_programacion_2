package game.scenes.dependencies;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.util.Duration;

public class NotificationManager {

    private static String getStyle(String style) {
        return switch (style) {
            case "gameInfo" -> "-fx-font-family: 'Unispace-Bold'; -fx-font-size: 15px; -fx-background-color: #312a24; -fx-opacity: 0.8; -fx-text-fill: white; -fx-padding: 20;";
            case "error" -> "-fx-font-family: 'Unispace-Bold'; -fx-font-size: 15px; -fx-background-color: red; -fx-opacity: 0.8; -fx-text-fill: white; -fx-padding: 20;";
            default -> "-fx-padding: 20; -fx-font-size: 15px;";
        };
    }

    public static void toolTip(Node node, String message, String style, double width) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setStyle(getStyle(style));
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(width);
        Tooltip.install(node, tooltip);
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event -> Tooltip.uninstall(node, tooltip));
        pause.play();
    }

    public static boolean alert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.initOwner(SceneManager.getStage());
        alert.initModality(Modality.WINDOW_MODAL);

        return alert.showAndWait().orElse(buttonTypeNo) == buttonTypeYes;
    }
}
