module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires jakarta.xml.bind;
    requires lombok;
    requires org.json;
    opens GameView to javafx.fxml;
    exports GameView;
}