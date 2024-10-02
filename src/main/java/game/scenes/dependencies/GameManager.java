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

        currentEnemy.healthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() <= 0 && !currentEnemy.isDefeated()) {
                currentEnemy.setDefeated(true);
                unlockNextEnemy(currentEnemy.getId());
            }
        });
    }

    public static void unlockNextEnemy(int index){
        currentSave.getEnemy(index + 1).setUnlocked(true);
        System.out.println(currentSave.getEnemy(index + 1).getName() + currentSave.getEnemy(index + 1).isUnlocked());
    }




}
