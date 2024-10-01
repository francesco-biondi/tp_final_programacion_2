package game.scenes.dependencies;

import game.models.characters.Enemy;
import game.models.saves.Save;

public class GameManager {

    private static Save currentSave;
    public static Enemy currentEnemy;

    public static Save getCurrentSave() {
        return currentSave;
    }

    public static void setCurrentSave(Save currentSave) {
        GameManager.currentSave = currentSave;
    }

    public static void setEnemy(int index){
        currentEnemy = getCurrentSave().getEnemy(index);
    }

    public static void unlockEnemy(int index){
        currentSave.getEnemy(index).setUnlocked(true);
    }




}
