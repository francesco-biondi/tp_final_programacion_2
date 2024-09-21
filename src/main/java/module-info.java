module com.tpfinalprogramacion2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tpfinalprogramacion2 to javafx.fxml;
    exports com.tpfinalprogramacion2;
    exports com.tpfinalprogramacion2.scenes;
    opens com.tpfinalprogramacion2.scenes to javafx.fxml;
}