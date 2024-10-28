package game.scenes.dependencies;

import game.models.abilities.Nakama;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.characters.dependencies.PlayerManager;
import game.models.saves.Save;

public class GameManager {

    private static Save currentSave;
    private static Enemy currentEnemy;
    private static Player currentPlayer;
    private static PlayerManager currentPlayerManager = new PlayerManager();

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

    public static PlayerManager getCurrentPlayerManager(){
        return currentPlayerManager;
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

    public static void startAttackNakamas(){
        for(Nakama nakama : currentPlayer.getNakamas().values()){
            if (nakama.isAvailable()){
                nakama.use(currentEnemy);
            }
        }
    }

    public static void stopAttackNakamas(){
        currentPlayerManager.stopAllNakamas(currentPlayer);
    }


}
