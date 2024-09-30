package com.tpfinalprogramacion2.scenes.battle.dependencies;

import com.tpfinalprogramacion2.services.Resource;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class CursorManager {

    public static void setCursorToFist(Scene scene) {
        Image cursorImage = new Image(Resource.cursorImagePath);
        ImageCursor customCursor = new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2);
        scene.setCursor(customCursor);
    }

    public static void resetCursor(Scene scene) {
        scene.setCursor(Cursor.DEFAULT);
    }
}