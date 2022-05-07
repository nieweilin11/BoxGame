package Controller;

import GameView.SetJavaFxObject;
import Model.Player;
import Model.Round;
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
    private Player player= Player.getPlayer();
    private Round round= Round.getRound();

    private PlayerController playerController = PlayerController.getPlayerController();

    private SaveController saveController = SaveController.getSaveController();

    private final int totalBox=16;
    private final int totalStone=6;
    private int empty=10;
    private List<Circle> puzzleList =new ArrayList<>();
    private List<Polyline>boxList =new ArrayList<>();
    private ArrayList<Integer> select =round.getPlayerStep();
    public ArrayList<Integer> tempSelect = new ArrayList<>();

    private LocalDateTime start;
    private LocalDateTime end;

    private HashMap<String,Double>table;

    private boolean chessMax =false;
    private int rounds =0;
    private ArrayList<Integer> playerStep=new ArrayList<>();
    private boolean reset=false;


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

    public ArrayList<Integer> getPlayerStep() {
        return playerStep;
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

    public boolean isChosenMax() {
        return chessMax;
    }

    public void setChessMax(boolean chessMax) {
        this.chessMax = chessMax;
    }
    public static RoundController getRoundController() {
        return roundController;
    }


    public void init(){
        for (int i=1;i<totalBox+2;i++){
            boxList.add(setBoxPosition(setBox(),60.0*i));
        }
        puzzleList.add(setRedStone(0));
        puzzleList.add(setBlackStone(1));
        puzzleList.add(setRedStone(2));
        puzzleList.add(setBlackStone(3));
        puzzleList.add(setRedStone(4));
        puzzleList.add(setBlackStone(5));
        for (int i=totalStone;i<totalBox;i++) {
            puzzleList.add(SetJavaFxObject.setEmpty(i));
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
        public void displayStones(ArrayList<Integer>arrayList){
        for (int i=0;i<arrayList.size();i++){
            int index=arrayList.get(i);
            if (index==0){puzzleList.add(SetJavaFxObject.setEmpty(i));}
            if (index==1){puzzleList.add(setRedStone(i));}
            if (index==2){puzzleList.add(setBlackStone(i));}
        }
        }

    public void roundCounter(int i){
        rounds +=i;
        round.setRoundS();
    }

    /**
     *
     * @param table
     * @return
     */
    public void scoreTable(HashMap<String,Double> table){
            table.put(player.getPlayerName(), score());
    }

    /**
     * to judge the statement weather it is win statement or not
     * @param arrayList
     */
    public void judgePlayerMovement(ArrayList<Integer> arrayList){
        int r1,r2,r3,b1,b2,b3;
        for(int i=1;i<empty+1;i++){
            r1=arrayList.get(i);r2=arrayList.get(i+1);r3=arrayList.get(i+2);
            b1=arrayList.get(i+3);b2=arrayList.get(i+4);b3=arrayList.get(i+5);

            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                System.out.println("win");
            }
            else {
                updatePuzzle(arrayList);
            }
        }
    }
    public void updatePuzzle(ArrayList<Integer> arrayList){}



    public LocalDateTime startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at: "+dt);
        setStart(dt);
        round.setStart(dt);
        return dt;
    }
    public LocalDateTime endTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game start at: "+dt);
        setEnd(dt);
        round.setEnd(dt);
        return dt;
    }    public double score(){
        java.time.Duration duration = java.time.Duration.between(startTime(),endTime());
        double time =duration.toSeconds();
        return getRounds()/time;
    }

}
