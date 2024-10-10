package game.scenes.components;

import game.models.abilities.enums.AbilityNames;
import game.models.abilities.enums.NakamaNames;
import game.scenes.dependencies.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ShopPane extends TabPane {

    @FXML
    private Text playerName, playerBounty;

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

        displayPlayerData();
        displayShop();
    }

    private void displayPlayerData(){
        this.playerName.setText(GameManager.getCurrentPlayer().getName());
        this.playerBounty.textProperty().bind(GameManager.getCurrentPlayer().bountyProperty());
    }

    private void displayShop(){
        for(Node child : abilityGrid.getChildren()){
            if(child instanceof ItemPane itemPane) {
                itemPane.setShopItemData(GameManager.getCurrentPlayer().getAbility(AbilityNames.valueOf(itemPane.getId())));
            }
        }
        for(Node child : nakamaGrid.getChildren()){
            if(child instanceof ItemPane itemPane){
                itemPane.setShopItemData(GameManager.getCurrentPlayer().getNakama(NakamaNames.valueOf(itemPane.getId())));
            }
        }
    }


}
