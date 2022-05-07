package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Fish
 */
public class Round {
    private  static  Round round= new Round();
    private Round(){}
    private LocalDateTime start;
    private LocalDateTime end;
    private double score;
    private ArrayList<Integer> playerStep=new ArrayList<>();


    public ArrayList<Integer> getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(ArrayList<Integer> playerStep) {
        this.playerStep = playerStep;
    }

    public void setRoundS() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }

    public static Round getRound() {
        return round;
    }

}
