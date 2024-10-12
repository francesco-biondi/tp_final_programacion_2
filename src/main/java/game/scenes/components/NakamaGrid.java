package game.scenes.components;

import game.models.abilities.enums.NakamaNames;
import game.scenes.dependencies.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class NakamaGrid extends GridPane {
    public NakamaGrid() {
        FXMLLoader loader = new FXMLLoader(
                NakamaGrid.class.getResource(
                        "nakama-grid.fxml"
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
        loadPosterImages();
    }

    private void loadPosterImages(){
        for(Node child : this.getChildren()){
            if(child instanceof PosterPane posterPane){
                NakamaNames nakamaName = NakamaNames.valueOf(posterPane.getId());
                Image posterImage = new Image(GameManager.getCurrentPlayer().getNakama(nakamaName).getImage());
                posterPane.setPosterImage(posterImage);
                posterPane.visibleProperty().bind(GameManager.getCurrentPlayer().getNakama(nakamaName).unlockProperty());
            }
        }
    }
}
