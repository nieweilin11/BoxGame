package Controller;

import GameView.SetJavaFxObject;
import Model.Player;
import Model.Round;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import lombok.Data;
import org.tinylog.Logger;

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
    private Player player= Player.getPlayer();
    private Round round= Round.getRound();
    private PlayerController playerController = PlayerController.getPlayerController();
    private SaveController saveController = SaveController.getSaveController();

    public final int totalBox=16;
    public final int totalStone=6;
    private int rounds =0;
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
    private boolean chessMax =false;
    private boolean reset=false;
    private boolean passSelect=false;

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
     * @param arrayList
     */
    public void displayStones(ArrayList<Integer>arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int index = arrayList.get(i);
                if (index == 0) {
                    getPuzzleList().set(i,SetJavaFxObject.setEmpty(i));
                    Logger.trace("Empty");
                }
                if (index == 1) {
                    getPuzzleList().set(i,setRedStone(i));
                    Logger.trace("Red");
                }
                if (index == 2) {
                    getPuzzleList().set(i,setBlackStone(i));
                    Logger.trace("Black");
                }
            }
    }


    /**
     *
     * @param table
     * @return
     */
    public void scoreTable(HashMap<String,Double> table){
            table.put(player.getPlayerName(), getScore());
    }

    /**
     * to judge the statement weather it is win statement or not
     */
    public void judgePlayerMovement(){
        ArrayList<Integer> arrayList=SetJavaFxObject.getSelect();
        int r1,r2,r3,b1,b2,b3;
        for(int i=1;i<empty+1;i++){
            r1=arrayList.get(i);r2=arrayList.get(i+1);r3=arrayList.get(i+2);
            b1=arrayList.get(i+3);b2=arrayList.get(i+4);b3=arrayList.get(i+5);

            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                System.out.println("win");
            }
            else {
                updatePuzzle();
            }
        }
    }
    public void updatePuzzle(){}

    /**
     *
     * @return start time :LocalDateTime
     */
    public LocalDateTime startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at: "+dt);
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
        System.out.println("Game start at: "+dt);
        setEnd(dt);
        round.setEnd(dt);
        return dt;
    }
    public void roundCounter(int i) {
        rounds+=i;
    }

    public double getScore(){
        java.time.Duration duration = java.time.Duration.between(startTime(),endTime());
        double time =duration.toSeconds();
        return getRounds()/time;
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

    public int getRounds() {
        return rounds;
    }

    public boolean isReset() {
        return reset;
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

    public boolean isChessMax() {
        return chessMax;
    }

    public static RoundController getRoundController() {
        return roundController;
    }
    public ArrayList<Integer> getTempSelect() {
        for(int j=0;j<roundController.totalBox;j++){tempSelect.add(0);}
        return tempSelect;
    }


}
