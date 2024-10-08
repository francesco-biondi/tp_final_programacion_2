package game.scenes.dependencies;

import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.saves.Save;

public class GameManager {

    private static Save currentSave;
    private static Enemy currentEnemy;
    private static Player currentPlayer;

    public static Save getCurrentSave() {
        return currentSave;
    }

    public static void setCurrentSave(Save currentSave) {
        GameManager.currentSave = currentSave;
        currentPlayer = currentSave.getPlayer();
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public static void setCurrentEnemy(Enemy currentEnemy) {
        GameManager.currentEnemy = currentEnemy;
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
    }




}
