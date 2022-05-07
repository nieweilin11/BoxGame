package GameView;

import Controller.RoundController;
import Model.Round;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fish
 */
public class SetJavaFxObject {
    static int pair=2;
    private static Round round= Round.getRound();
    private static  RoundController roundController = RoundController.getRoundController();
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
    public static void convert(ArrayList<Integer> arrayList, List<Circle> list){
        Circle red=new Circle(11);
        red.setFill(Color.rgb(128,0,0));
        Circle black=new Circle(11);
        black.setFill(Color.rgb(0,0,0));
        Circle empty=new Circle(11);
        empty.setFill(Color.rgb(0,0,0));
        for (int i=0;i<list.size();i++){
            if(list.get(i).getFill()==red.getFill()){arrayList.set(i,1);}
            else if(list.get(i).getFill()==black.getFill()){arrayList.set(i,2);}
            else if(list.get(i).getFill()==empty.getFill()){arrayList.set(i,0);}
        }
    }
    public static void selected(int i,int choose){
        System.out.println(i);
        ArrayList <Integer>arrayList= roundController.getSelect();
        ArrayList <Integer>arrayList1= roundController.tempSelect;
        if (round.getPlayerStep().get(i)!=0) {
            pair += choose;
            if (pair >= 0) {
                arrayList1.set(i, round.getPlayerStep().get(i));
            }
            else {
                roundController.setChessMax(true);
            }
        }
        System.out.println(roundController.getPlayerStep());
        if (round.getPlayerStep().get(i)==0&&roundController.isChessMax()) {
            for (int j=0;j<arrayList1.size();i++){
                if (arrayList1.get(i)!=0) {
                    arrayList.set(i,arrayList1.get(i));
                }
            }

        }
        else {
            roundController.setChessMax(false);
        }

    }


}

