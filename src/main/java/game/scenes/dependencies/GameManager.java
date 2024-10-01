package game.scenes.dependencies;

import game.models.characters.Enemy;
import game.models.saves.Save;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GameManager {

    private static Save currentSave;
    public static ObjectProperty<Enemy> enemy = new SimpleObjectProperty<>();

    public static Save getCurrentSave() {
        return currentSave;
    }

    public static void setCurrentSave(Save currentSave) {
        GameManager.currentSave = currentSave;
    }

    public static void setEnemy(int index){
        enemy.set(getCurrentSave().getEnemy(index));
    }

//    public static void unlockEnemy(int index){
//        enemy.get().setUnlocked(true);
//    }

}
