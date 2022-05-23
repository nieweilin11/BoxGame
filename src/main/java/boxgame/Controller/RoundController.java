package boxgame.Controller;

import boxgame.GameView.SetJavaFxObject;
import boxgame.Model.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static boxgame.GameView.SetJavaFxObject.*;


/**
 * @author Nie Weilin.
 */

public class RoundController {
    private static final RoundController ROUND_CONTROLLER = new RoundController();

    private final Player player = Player.getPlayer();
    /**
     * Total boxes in game.
     */
    public final int totalBox = 16;
    /**
     * Total stones in game.
     */
    public final int totalStone = 6;
    /**
     * For count how many rounds the player spend.
     */
    private int roundCounter = 0;
    /**
     * TO contain the red stone black stone and empty place.
     */
    private final List<Circle> puzzleList = new ArrayList<>();
    /**
     * Contain the boxes which in the game view.
     */
    private final List<Polyline>boxList = new ArrayList<>();
    /**
     * Reference of player.getPlayerStep() be used in SetJavaFxObject.
     */
    private final ArrayList<Integer> select = player.getPlayerStep();
    /**
     * Be used in store the player chosen stones.
     */
    private final ArrayList<Integer> tempSelect = new ArrayList<>();
    /**
     *  Used to determine whether a reset is required.
     */
    private boolean reset = false;
    /**
     * Used to determine whether the stone selection has been completed and the stone has been successfully moved.
     */
    private boolean passSelect = false;

    /**
     * initialize the JavaFx puzzle.
     */
    public void init() {
        final int add = 2;
        for (int i = 1; i < totalBox + add; i++) {
            boxList.add(setBoxPosition(setBox(),60.0 * i));
        }
        player.setPlayerName("casual player");
        player.setScore(0);
        player.setFinished(false);
        getPuzzleList().add(setRedStone(0));
        getPuzzleList().add(setBlackStone(1));
        getPuzzleList().add(setRedStone(2));
        getPuzzleList().add(setBlackStone(3));
        getPuzzleList().add(setRedStone(4));
        getPuzzleList().add(setBlackStone(5));
        for (int i = totalStone; i < totalBox; i++) {
            getPuzzleList().add(SetJavaFxObject.setEmpty(i));
        }
        ROUND_CONTROLLER.initPuzzle(player.getPlayerStep());
    }

    /**
     * initial the puzzle.
     *@param  arrayList
     */
    public void initPuzzle(ArrayList<Integer> arrayList) {
        int total = 16;
        int stone = 6;
        for (int i = 0; i < total; i++) {
            arrayList.add(0);
        }
            for (int i = 0; i < stone; i++) {
                if (i % 2 == 0) {
                    arrayList.set(i, 1);
                } else {
                    arrayList.set(i, 2);
                }
            }
        }

    /**
     * convert the puzzle to JavaFx puzzle to visualize the graphical interface.
     */
    public void displayStones() {
        ArrayList<Integer> arrayList = player.getPlayerStep();
        for (int i = 0; i < arrayList.size(); i++) {
            int index = arrayList.get(i);
                if (index == 0) {
                    getPuzzleList().get(i).setFill(Color.rgb(139, 69, 19));
                }
                if (index == 2) {
                    getPuzzleList().get(i).setFill(Color.rgb(0, 0, 0));
                }
                if (index == 1) {
                    getPuzzleList().get(i).setFill(Color.rgb(255, 0, 0));
                }
            }
    }

    /**
     * To judge the statement weather it is win statement or not if won ,store the Score .
     */
    public void judgePlayerMovement() {
        ArrayList<Integer> arrayList = Player.getPlayer().getPlayerStep();
        int r1;
        int r2;
        int r3;
        int b1;
        int b2;
        int b3;
        int empty = 10;
        for (int i = 0; i< empty+1; i++) {
            r1 = arrayList.get(i);
            r2 = arrayList.get(i + 1);
            r3 = arrayList.get(i + 2);
            b1 = arrayList.get(i + 3);
            b2 = arrayList.get(i + 4);
            b3 = arrayList.get(i + 5);
            if (r1 == 1 && b1 == 2 && r1 == r2 && r2 == r3 && b1 == b2 && b2 == b3) {
                player.setFinished(true);
                player.setScore(score()+player.getScore());
                System.out.println("You Won");
            }
        }
    }

    /**
     *Set game start time .
     */
    public void startTime() {
        LocalDateTime dt = LocalDateTime.now();
        player.setStart(dt);
    }

    /**
     * Set game end time .
     */
    public void endTime(){
        LocalDateTime dt = LocalDateTime.now();
        player.setEnd(dt);
    }

    /**
     * Calculate the score and save two decimal places.
     * @return time
     */
    public double score() {
        LocalDateTime dt = LocalDateTime.now();
        player.setEnd(dt);
        Duration duration = Duration.between(player.getStart(),player.getEnd());
        double time = duration.toSeconds();
        double mathRound = roundCounter/time;
        mathRound = (double) Math.round(mathRound * 100) / 100;
        System.out.println(mathRound);
        return mathRound;
    }


    public ArrayList<Integer> getSelect() {
        return select;
    }
    public List<Circle> getPuzzleList() {
        return puzzleList;
    }
    public List<Polyline> getBoxsList() {
        return boxList;
    }
    public int getRoundCounter() {
        return roundCounter;
    }
    public boolean isPassSelect() {
        return passSelect;
    }
    public void setPassSelect(boolean passSelect) {
        this.passSelect = passSelect;
    }
    public boolean isReset() {
        return reset;
    }
    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }
    public void setReset(boolean reset) {
        this.reset = reset;
    }
    public static RoundController getRoundController() {
        return ROUND_CONTROLLER;
    }
    public ArrayList<Integer> getTempSelect() {
        for(int j = 0; j < ROUND_CONTROLLER.totalBox; j++) {
            if (tempSelect.size()<16) {
                tempSelect.add(0);
            }
        }
        return tempSelect;
    }
}
