package game.scenes.dependencies;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationManager {

    public static void toolTip(Node node, String message, String style, double width) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setStyle(switch(style){
            case "gameInfo" -> "-fx-font-family: 'Unispace-Bold'; -fx-background-color: #312a24; -fx-opacity: 0.8; -fx-text-fill: white;";
            case "error" -> "-fx-background-color: red; -fx-text-fill: white;";
            default -> tooltip.getStyle();
        });
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(width);
        Tooltip.install(node, tooltip);
    }

    public static boolean showConfirmationAlert(String title, String header, String content) {
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
