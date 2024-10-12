package game.scenes.components;

import game.models.abilities.enums.AbilityNames;
import game.models.abilities.enums.NakamaNames;
import game.scenes.dependencies.GameManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class ShopPane extends TabPane {

    @FXML
    private Text playerName, playerBounty;

    @FXML
    private GridPane abilityGrid, nakamaGrid;

    public static boolean isOpen = false, isAnimating = false;
    private static TranslateTransition translateTransition;

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
    }

    @FXML
    private void initialize() {
        configureShop();
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

    public void configureShop() {
        isOpen = false;
        this.setTranslateX(-400);
        translateTransition = new TranslateTransition(Duration.millis(600), this);
        this.setCache(true);
        this.setCacheShape(true);
    }

    public void toggleShop() {
        if(!isAnimating){
            isAnimating = true;
            double startX = isOpen ? 0 : -400;
            double endX = isOpen ? -400 : 0;

            translateTransition.setFromX(startX);
            translateTransition.setToX(endX);

            translateTransition.play();

            translateTransition.setOnFinished(e -> {
                isOpen = !isOpen;
                isAnimating = false;
            });
        }
    }

}
