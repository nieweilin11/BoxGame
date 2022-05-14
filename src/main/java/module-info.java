module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires jakarta.xml.bind;
    requires lombok;
    requires org.json;
    requires org.tinylog.api;
    requires slf4j.api;
    requires com.alibaba.fastjson2;
    opens GameView to javafx.fxml;
    exports GameView;
}