module com.tpfinalprogramacion2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.tpfinalprogramacion2 to javafx.fxml;
    exports com.tpfinalprogramacion2;
    exports com.tpfinalprogramacion2.scenes.main_menu;
    opens com.tpfinalprogramacion2.scenes.main_menu to javafx.fxml;
    opens com.tpfinalprogramacion2.models.saves to com.google.gson;
    opens com.tpfinalprogramacion2.models.saves.dependencies to com.google.gson;
}