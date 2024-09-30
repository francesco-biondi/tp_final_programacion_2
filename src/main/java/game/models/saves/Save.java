package game.models.saves;

import game.models.characters.Enemy;
import game.models.characters.Player;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Save implements Serializable {

    private final String saveName;
    private final String saveDate;
    private Player player;
    ArrayList<Enemy> enemies;

    public Save(String saveName) {
        this.saveName = saveName;
        this.saveDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return saveName + " - " + saveDate;
    }
}
