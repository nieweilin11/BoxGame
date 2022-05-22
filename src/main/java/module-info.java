module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires jakarta.xml.bind;
    requires lombok;
    requires org.tinylog.api;
    requires java.sql;
    requires fastjson;
    requires commons.io;
    opens BoxGame.GameView to javafx.fxml;
    exports BoxGame.GameView;
}