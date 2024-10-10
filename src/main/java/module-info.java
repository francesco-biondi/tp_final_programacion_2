module game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.desktop;

    /**
     *  Abrir paquetes relacionados con JavaFX y Gson
     */
    opens game to javafx.fxml;
    opens game.scenes.components to javafx.fxml;
    opens game.scenes.main_menu to javafx.fxml;
    opens game.scenes.battle to javafx.fxml;
    opens game.scenes.map to javafx.fxml;

    opens game.models.saves to com.google.gson;
    opens game.models.saves.dependencies to com.google.gson;
    opens game.models.characters to com.google.gson;
    opens game.models.abilities to com.google.gson;

    exports game;
    exports game.models.abilities.enums;
    exports game.scenes.components;
}
