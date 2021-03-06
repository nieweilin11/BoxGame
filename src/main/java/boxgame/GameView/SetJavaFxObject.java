package boxgame.GameView;

import boxgame.Controller.RoundController;
import boxgame.Model.Player;
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
    /**
     * Used to determine the number of stones to choose.
     */
    static int pair = 2;
    private static final Player PLAYER = Player.getPlayer();
    private static final RoundController ROUND_CONTROLLER = RoundController.getRoundController();
    private static ArrayList <Integer>select = ROUND_CONTROLLER.getSelect();
    private static final ArrayList <Integer> TEMP_SELECT = ROUND_CONTROLLER.getTempSelect();

    /**
     * set button position.
     * @param button
     * @param x
     * @param y
     */
    public static void setButtonPosition(Button button, final int x, final int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    /**
     * set label position.
     * @param label
     * @param x
     * @param y
     */
    public static void setLabelPosition(Label label, final int x, final int y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
    }
    /**
     * set label size.
     * @param label
     * @param x
     * @param y
     */
    public static void setLabelSize(Label label, final double x, final double y) {
        label.setPrefHeight(x);
        label.setPrefWidth(y);
    }

    /**
     * set text position.
     * @param textFile
     * @param x
     * @param y
     */
    public static void setTextFile(TextField textFile, final int x, final int y) {
        textFile.setLayoutX(x);
        textFile.setLayoutY(y);
    }

    /**
     * create and set red stone.
     * @param garb
     * @return Circle
     */
    public static Circle setRedStone(final int garb) {
        int s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(255, 0, 0));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * create and set black stone.
     * @param garb
     * @return Circle
     */
    public static Circle setBlackStone(final int garb) {
        int s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(0, 0, 0));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * create and set empty.
     * @param garb
     * @return Circle
     */
    public static Circle setEmpty(final int garb) {
        double s = 60;
        Circle circle = new Circle(11);
        circle.setFill(Color.rgb(139, 69, 19));
        circle.setTranslateX(48 + s * garb);
        circle.setLayoutY(220);
        return circle;
    }

    /**
     * set the Box size and color.
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
     * set boxes position.
     * @param polyline
     * @param garb
     * @return Polyline
     */
    public static Polyline setBoxPosition(Polyline polyline, final double garb) {
        polyline.setLayoutX(garb - 90);
        polyline.setLayoutY(200);
        return polyline;
    }


    /**
     * store player's chose and determine whether the rules are met.
     * @param i
     * @param choose
     */
    public static void selected(int i, int choose) {
        if (PLAYER.getPlayerStep().get(i) != 0) {
            pair += choose;
            final int maxChoose = 2;
            //store two stones which were player chosen
            if (pair > 0) {
                TEMP_SELECT.set(i, PLAYER.getPlayerStep().get(i));
                Logger.trace("First select  " + TEMP_SELECT);
               }
            //the second chose has 3 situations 1.at begin position 2. at middle position 3.at last position
            if (pair == 0) {
                if (i != 0 && i != TEMP_SELECT.get(ROUND_CONTROLLER.totalBox - 1) && (TEMP_SELECT.get(i + 1) != 0 || TEMP_SELECT.get(i - 1) != 0)) {
                    TEMP_SELECT.set(i, PLAYER.getPlayerStep().get(i));
                    Logger.trace("Second select " + TEMP_SELECT);
                }
                if (i == 0 && TEMP_SELECT.get(i + 1) != 0) {
                    TEMP_SELECT.set(i, PLAYER.getPlayerStep().get(i));
                    Logger.trace("Second select First " + TEMP_SELECT);
                }
                if (i == (ROUND_CONTROLLER.totalBox - 1) && TEMP_SELECT.get(i - 1) != 0) {
                    TEMP_SELECT.set(i, PLAYER.getPlayerStep().get(i));
                    Logger.trace("Second select Last " + TEMP_SELECT);
                }
            }
            //select more than two stones setReset  put the previous stone back
            else if (pair < 0 || pair > maxChoose) {
                reset();
                Logger.trace("reset select");
            }
        }
        //chose an empty place to put two stones
        if  (PLAYER.getPlayerStep().get(i) == 0 && pair == 0) {
            Logger.trace("Current puzzle" + PLAYER.getPlayerStep());
            if (i == 0) {
                reset();
            }
                if (TEMP_SELECT.get(i) == 0) {
                    for (int j = 0; j < TEMP_SELECT.size(); j++) {
                        if (TEMP_SELECT.get(j) != 0) {
                            if (select.get(i - 1 + pair) != 0 || select.get(i) != 0) {
                                reset();
                                Logger.trace("Doesn't select two empty area ");
                                break;
                            }
                            select.set(i - 1 + pair, TEMP_SELECT.get(j));
                            select.set(j,0);
                            ROUND_CONTROLLER.setPassSelect(true);
                            ROUND_CONTROLLER.setReset(true);
                            pair++;
                        }
                    }
                    Logger.trace("Combine puzzle" + select);
            }
                else {
                    reset();
                }
                reset();
        }
        if(PLAYER.getPlayerStep().get(i) == 0 && pair != 0) {
            reset();
            Logger.trace("Haven't select to stone");
        }
    }

    /**
     * reset the select List and TEMP_SELECT List ,pair=2.
     */
    public static void reset() {
        select = PLAYER.getPlayerStep();
        for (int i = 0; i < ROUND_CONTROLLER.totalBox; i++) {
            TEMP_SELECT.set(i,0);
        }
        pair = 2;
        ROUND_CONTROLLER.setReset(true);
    }

    /**
     * stone click event.
     */
    public static void selectEvent() {
        Player round = Player.getPlayer();
        if (!PLAYER.isFinished()) {
        for (int i = 0; i < round.getPlayerStep().size(); i++) {
            int finalI = i;
            RoundController roundController = RoundController.getRoundController();
            List<Circle> puzzleList = roundController.getPuzzleList();
            if (!roundController.isPassSelect()) {
                puzzleList.get(i).setOnMouseClicked(mouseEvent -> {
                    if (puzzleList.get(finalI).getLayoutY() > 180) {
                        puzzleList.get(finalI).setLayoutY(180);
                        selected(finalI, -1);
                    }
                    else {
                        puzzleList.get(finalI).setLayoutY(220);
                        selected(finalI, 1);
                    }
                    if (roundController.isReset()) {
                        for (int j = 0; j < roundController.totalBox; j++) {
                            puzzleList.get(j).setLayoutY(220);
                        }
                        roundController.setReset(false);
                    }

                    if (roundController.isPassSelect()) {
                        roundController.setRoundCounter(roundController.getRoundCounter() + 1);
                        roundController.displayStones();
                        roundController.judgePlayerMovement();
                        roundController.setPassSelect(false);
                        Logger.trace("Round" + roundController.getRoundCounter());
                    }
                });
            }
        }
     }
    }
}

