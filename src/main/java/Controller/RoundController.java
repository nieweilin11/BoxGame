package Controller;

import GameView.SetJavaFxObject;
import Model.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static GameView.SetJavaFxObject.*;


/**
 * @author Nie Weilin
 */

public class RoundController {
    private static final RoundController roundController =new RoundController();
    private final Player player = Player.getPlayer();

    public final int totalBox=16;
    public final int totalStone=6;

    private int roundCounter =0;

    private final List<Circle> puzzleList =new ArrayList<>();
    private final List<Polyline>boxList =new ArrayList<>();

    public boolean isPassSelect() {
        return passSelect;
    }

    public void setPassSelect(boolean passSelect) {
        this.passSelect = passSelect;
    }

    private final ArrayList<Integer> select = player.getPlayerStep();
    private final ArrayList<Integer> tempSelect = new ArrayList<>();

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
        player.setPlayerName("casual player");
        player.setScore(0);
        player.setFinished(false);
        getPuzzleList().add(setRedStone(0));
        getPuzzleList().add(setBlackStone(1));
        getPuzzleList().add(setRedStone(2));
        getPuzzleList().add(setBlackStone(3));
        getPuzzleList().add(setRedStone(4));
        getPuzzleList().add(setBlackStone(5));
        for (int i=totalStone;i<totalBox;i++) {
            getPuzzleList().add(SetJavaFxObject.setEmpty(i));
        }
        roundController.initPuzzle(player.getPlayerStep());
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
        ArrayList<Integer> arrayList= player.getPlayerStep();
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
     * to judge the statement weather it is win statement or not
     */
    public void judgePlayerMovement(){
        ArrayList<Integer> arrayList= Player.getPlayer().getPlayerStep();
        int r1,r2,r3,b1,b2,b3;
        int empty = 10;
        for(int i = 1; i< empty +1; i++){
            r1=arrayList.get(i);r2=arrayList.get(i+1);r3=arrayList.get(i+2);
            b1=arrayList.get(i+3);b2=arrayList.get(i+4);b3=arrayList.get(i+5);

            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                player.setFinished(true);
                System.out.println("You Won");
            }

        }
    }

    /**
     *
     * @return start time :LocalDateTime
     */
    public void startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game Start at: "+dt);
        player.setStart(dt);
    }

    /**
     *
     * @return end time :LocalDateTime
     */
    public void endTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game Over at: "+dt);
        player.setEnd(dt);
    }

    public void getScore(){
        java.time.Duration duration = java.time.Duration.between(player.getStart(),player.getEnd());
        double time =duration.toSeconds();
        player.setScore(getRoundCounter()/time);
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


    public static RoundController getRoundController() {
        return roundController;
    }
    public ArrayList<Integer> getTempSelect() {
        for(int j=0;j<roundController.totalBox;j++){tempSelect.add(0);}
        return tempSelect;
    }


}
