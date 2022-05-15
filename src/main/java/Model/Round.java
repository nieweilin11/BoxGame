package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fish
 */
public class Round {
    private  static final Round ROUND = new Round();

    private Round(){}


    private String playerName;
    private LocalDateTime start;
    private LocalDateTime end;
    private double score;
    private boolean isFinished=false;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }


    private ArrayList<Integer> playerStep=new ArrayList<>();


    public ArrayList<Integer> getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(ArrayList<Integer> playerStep) {
        this.playerStep = playerStep;
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
        return ROUND;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
