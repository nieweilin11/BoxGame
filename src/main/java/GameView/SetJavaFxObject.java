package GameView;

import Controller.RoundController;
import Model.Round;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nie Weilin
 */
public class SetJavaFxObject {
    static int pair=2;
    private static final Round ROUND = Round.getRound();
    private static final RoundController ROUND_CONTROLLER = RoundController.getRoundController();
    private static ArrayList <Integer>select= ROUND_CONTROLLER.getSelect();
    private static final ArrayList <Integer> TEMP_SELECT = ROUND_CONTROLLER.getTempSelect();

    /**
     *
     * @param button
     * @param x
     * @param y
     */
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

    public static ArrayList<Integer> getSelect() {
        return select;
    }

    /**
     * store player's chose and determine whether the rules are met
     * @param i
     * @param choose
     */
    public static void selected(int i, int choose){
        if (ROUND.getPlayerStep().get(i)!=0) {
            pair += choose;
            //store two stones which were player chosen
            if (pair > 0) {
                TEMP_SELECT.set(i, ROUND.getPlayerStep().get(i));
                Logger.trace("First select  "+ TEMP_SELECT);
               }
            //the second chose has 3 situations 1.at begin position 2. at middle position 3.at last position
            if (pair==0){
                if (i!=0&&i!= TEMP_SELECT.get(ROUND_CONTROLLER.getTotalBox()-1)&&(TEMP_SELECT.get(i+1)!=0|| TEMP_SELECT.get(i-1)!=0)){
                    TEMP_SELECT.set(i, ROUND.getPlayerStep().get(i));
                    Logger.trace("Second select "+ TEMP_SELECT);
                }
                if (i==0&& TEMP_SELECT.get(i+1)!=0){
                    TEMP_SELECT.set(i, ROUND.getPlayerStep().get(i));
                    Logger.trace("Second select First "+ TEMP_SELECT);
                }
                if (i== ROUND_CONTROLLER.totalBox-1&& TEMP_SELECT.get(i-1)!=0){
                    TEMP_SELECT.set(i, ROUND.getPlayerStep().get(i));
                    Logger.trace("Second select Last "+ TEMP_SELECT);
                }
            }
            //select more than two stones setReset  put the previous stone back
            else if (pair<0||pair>2){
                reSet();
                Logger.trace("reSet select");
            }
        }
        //chose an empty place to put two stones
        if  (ROUND.getPlayerStep().get(i)==0&&pair==0) {
            Logger.trace("Current puzzle"+ ROUND.getPlayerStep());
                if (TEMP_SELECT.get(i)==0) {
                    for (int j = 0; j< TEMP_SELECT.size(); j++) {
                        if (TEMP_SELECT.get(j)!=0) {
                            if (select.get(i-1+pair)!=0||select.get(i)!=0){
                                reSet();
                                Logger.trace("Doesn't select two empty area ");
                                break;
                            }
                            select.set(i-1+pair, TEMP_SELECT.get(j));
                            select.set(j,0);
                            ROUND_CONTROLLER.setPassSelect(true);
                            ROUND_CONTROLLER.setReset(true);
                            pair++;
                        }
                    }

                    Logger.trace("Combine puzzle"+select);
            }
                else {reSet();}
        }
        if(ROUND.getPlayerStep().get(i)==0&&pair!=0) {
            reSet();
            Logger.trace("reSet empty");
        }
    }

    /**
     * Reset the puzzle and player's chose ,set"setReset" is ture
     */
    public static void reSet(){
        select= ROUND.getPlayerStep();
        for (int i = 0; i< ROUND_CONTROLLER.totalBox; i++){
            TEMP_SELECT.set(i,0);}
        pair=2;
        ROUND_CONTROLLER.setReset(true);
    }
}

