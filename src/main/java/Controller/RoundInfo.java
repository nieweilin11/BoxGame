package Controller;

import Model.Player;
import Model.Round;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Nie Weilin
 */

@Data
public class RoundInfo {
    private static RoundInfo roundInfo=new RoundInfo();
    Player player= Player.getPlayer();
    Round round= Round.getRound();
    PlayerInfo playerInfo=PlayerInfo.getPlayerInfo();
    SaveWrite saveWrite= SaveWrite.getSaveWrite();
    private LocalDateTime start;
    private LocalDateTime end;

    private double score;
    private HashMap<String,Double>table;
    public int empty=10;
     private boolean chessMax =true;
    private int rounds =0;
    private ArrayList<Integer> playerStep=new ArrayList<>();
    private boolean reset=false;



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

    public boolean isChessMax() {
        return chessMax;
    }

    public void setChessMax(boolean chessMax) {
        this.chessMax = chessMax;
    }
    public static RoundInfo getRoundInfo() {
        return roundInfo;
    }

    /**
     * initial the puzzle
     * @param arrayList
     */
    public void initPuzzle(ArrayList<Integer>arrayList) {
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

    public void roundCounter(int i){
        rounds +=i;
        round.setRoundS(rounds);
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
        System.out.println("Game over at: "+dt);
        setEnd(dt);
        round.setEnd(dt);
        return dt;
    }    public double score(){
        java.time.Duration duration = java.time.Duration.between(startTime(),endTime());
        double time =duration.toSeconds();
        return getRounds()/time;
    }

}
