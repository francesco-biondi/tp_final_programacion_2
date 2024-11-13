package game.scenes.battle.dependencies;

import game.scenes.dependencies.SceneManager;
import game.services.Resource;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class CursorManager {

    public static void setCursorToFist() {
        Image cursorImage = new Image(Resource.CURSOR_IMAGE_PATH);
        ImageCursor customCursor = new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2);
        SceneManager.getScene().setCursor(customCursor);
    }

    public static void resetCursor() {
        SceneManager.getScene().setCursor(Cursor.DEFAULT);
    }
}