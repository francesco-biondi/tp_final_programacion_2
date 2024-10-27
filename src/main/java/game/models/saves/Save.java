package game.models.saves;

import com.google.gson.reflect.TypeToken;
import game.models.characters.Enemy;
import game.models.characters.Player;
import game.models.saves.dependencies.SaveManager;
import game.services.Resource;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Save implements Serializable {

    private final String savePath;
    private final String saveName;
    private final String saveDate;
    private final Player player;
    ArrayList<Enemy> enemies;

    public Save(String saveName, int slotIndex) {
        this.saveName = saveName;
        this.saveDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.enemies = SaveManager.loadFileList(Resource.ENEMIES, new TypeToken<ArrayList<Enemy>>(){}.getType());
        this.savePath = getSavePath(slotIndex);
        this.player = new Player(saveName);
    }

    @Override
    public String toString() {
        return saveName + " - " + saveDate;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemy(int index) {
        return enemies.get(index);
    }

    public String getSavePath() {
        return savePath;
    }

    public static String getSavePath(int slotIndex) {
        return "src/main/resources/saves/save" + (slotIndex + 1) + ".json";
    }
}
