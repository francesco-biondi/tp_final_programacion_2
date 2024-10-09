package game.scenes.components;

import game.models.abilities.Ability;
import game.models.abilities.enums.AbilityNames;
import game.scenes.dependencies.GameManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ShopPane extends TabPane {

    @FXML
    private GridPane abilityGrid, nakamaGrid;

    public ShopPane() {
        FXMLLoader loader = new FXMLLoader(
                ShopPane.class.getResource(
                        "shop-pane.fxml"
                )
        );
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        displayShop();
    }

    private void displayShop(){
        for(Node child : abilityGrid.getChildren()){
            if(child instanceof AbilityPane abilityPane) {
                abilityPane.setAbilityData(GameManager.getCurrentPlayer().getAbility(AbilityNames.valueOf(abilityPane.getId())));
            }
        }
        for(Node child : nakamaGrid.getChildren()){
            if(child instanceof AbilityPane abilityPane){
                abilityPane.setAbilityData(GameManager.getCurrentPlayer().getNakama(Integer.parseInt(abilityPane.getId().replace("nakama", ""))));
            }
        }
    }


}
