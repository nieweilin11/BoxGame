module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jakarta.xml.bind;

    opens com.example.game to javafx.fxml;
    exports com.example.game;
}