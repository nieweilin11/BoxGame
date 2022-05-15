package Controller;

import GameView.SetJavaFxObject;
import Model.Round;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static GameView.SetJavaFxObject.*;


/**
 * @author Nie Weilin
 */

@Data
public class RoundController {
    private static RoundController roundController =new RoundController();
    private Round round= Round.getRound();
    private PlayerController playerController = PlayerController.getPlayerController();
    private SaveController saveController = SaveController.getSaveController();

    public final int totalBox=16;
    public final int totalStone=6;

    private int roundCounter =0;
    private double score;
    private int empty=10;

    private List<Circle> puzzleList =new ArrayList<>();
    private List<Polyline>boxList =new ArrayList<>();

    public boolean isPassSelect() {
        return passSelect;
    }

    public void setPassSelect(boolean passSelect) {
        this.passSelect = passSelect;
    }

    private ArrayList<Integer> select =round.getPlayerStep();
    private ArrayList<Integer> tempSelect = new ArrayList<>();

    private LocalDateTime start;
    private LocalDateTime end;

    private HashMap<String,Double>table;

    private boolean reset=false;
    private boolean passSelect=false;
    private boolean flash=false;

    /**
     * initialize the JavaFx puzzle
     */
    public void init(){
        for (int i=1;i<totalBox+2;i++){
            boxList.add(setBoxPosition(setBox(),60.0*i));
        }
        getPuzzleList().add(setRedStone(0));
        getPuzzleList().add(setBlackStone(1));
        getPuzzleList().add(setRedStone(2));
        getPuzzleList().add(setBlackStone(3));
        getPuzzleList().add(setRedStone(4));
        getPuzzleList().add(setBlackStone(5));
        for (int i=totalStone;i<totalBox;i++) {
            getPuzzleList().add(SetJavaFxObject.setEmpty(i));
        }
        roundController.initPuzzle(round.getPlayerStep());
    }
    /**
     * initial the puzzle
     *
     */
    public void initPuzzle(ArrayList<Integer> arrayList) {
        int total = 16, stone = 6;
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
     * convert the puzzle to JavaFx puzzle to visualize the graphical interface
     *
     */
    public void displayStones() {
        ArrayList<Integer> arrayList=round.getPlayerStep();
        for (int i = 0; i < arrayList.size(); i++) {
            int index = arrayList.get(i);
                if (index == 0) {
                    getPuzzleList().get(i).setFill(Color.rgb(139, 69, 19));
                }
                if (index == 1) {
                    getPuzzleList().get(i).setFill(Color.rgb(0, 0, 0));
                }
                if (index == 2) {
                    getPuzzleList().get(i).setFill(Color.rgb(128, 0, 0));
                }
            }
    }

    /**
     *
     * @param table
     * @return
     */
    public void scoreTable(HashMap<String,Double> table){
            table.put(round.getPlayerName(), getScore());
    }

    /**
     * to judge the statement weather it is win statement or not
     */
    public void judgePlayerMovement(){
        ArrayList<Integer> arrayList=Round.getRound().getPlayerStep();
        int r1,r2,r3,b1,b2,b3;
        for(int i=1;i<empty+1;i++){
            r1=arrayList.get(i);r2=arrayList.get(i+1);r3=arrayList.get(i+2);
            b1=arrayList.get(i+3);b2=arrayList.get(i+4);b3=arrayList.get(i+5);

            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                round.setFinished(true);
                System.out.println("win");
            }

        }
    }

    /**
     *
     * @return start time :LocalDateTime
     */
    public LocalDateTime startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game Start at: "+dt);
        setStart(dt);
        round.setStart(dt);
        return dt;
    }

    /**
     *
     * @return end time :LocalDateTime
     */
    public LocalDateTime endTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game Over at: "+dt);
        setEnd(dt);
        round.setEnd(dt);
        return dt;
    }

    public double getScore(){
        java.time.Duration duration = java.time.Duration.between(startTime(),endTime());
        double time =duration.toSeconds();
        return getRoundCounter()/time;
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

    public boolean isReset() {
        return reset;
    }
    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }



    public static RoundController getRoundController() {
        return roundController;
    }
    public ArrayList<Integer> getTempSelect() {
        for(int j=0;j<roundController.totalBox;j++){tempSelect.add(0);}
        return tempSelect;
    }


}
