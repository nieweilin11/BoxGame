package GameView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

/**
 * @author Fish
 */
public class SetView {
    public static void setButtonPosition(Button button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    /**
     * @param label
     * @param x
     * @param y
     */
    public static void setLabelPosition(Label label, int x, int y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    /**
     * @param textFile
     * @param x
     * @param y
     */
    public static void setTextFile(TextField textFile, int x, int y) {
        textFile.setLayoutX(x);
        textFile.setLayoutY(y);
    }

    /**
     * @param label
     * @param x
     * @param y
     */
    public static void setLabelSize(Label label, double x, double y) {
        label.setPrefHeight(x);
        label.setPrefWidth(y);
    }

    /**
     * @param garb
     * @return Circle
     */
    public static Circle setRedStone(int garb) {
        int s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(128, 0, 0));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * @param garb
     * @return Circle
     */
    public static Circle setBlackStone(int garb) {
        int s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(0, 0, 0));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * @param garb
     * @return Circle
     */
    public static Circle setEmpty(int garb) {
        double s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(139, 69, 19));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * @return Polyline
     */
    public static Polyline setBox() {
        Polyline polyline = new Polyline();
        polyline.setStroke(Color.rgb(139, 69, 19));
        polyline.setStrokeWidth(5);
        polyline.getPoints().addAll(0.0, 0.0);
        polyline.getPoints().addAll(0.0, 35.0);
        polyline.getPoints().addAll(35.0, 35.0);
        polyline.getPoints().addAll(35.0, 0.0);
        return polyline;
    }

    /**
     * @param polyline
     * @param garb
     * @return
     */
    public static Polyline setBoxPosition(Polyline polyline, double garb) {
        polyline.setLayoutX(garb - 90);
        polyline.setLayoutY(200);
        return polyline;
    }


}

