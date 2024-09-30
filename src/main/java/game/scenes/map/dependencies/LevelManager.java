package game.scenes.map.dependencies;

import game.models.saves.dependencies.SaveManager;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LevelManager {

    public static void displayLevels(Group levels){
        ObservableList<Node> levelList = levels.getChildren();

        for(Node level : levelList){
            if(level instanceof Group) displayLevel((Group) level);
        }
    }

    private static void displayLevel(Group level){


    }

}
