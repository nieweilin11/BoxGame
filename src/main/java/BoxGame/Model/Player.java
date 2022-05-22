package BoxGame.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Fish
 */
public class Player {
    private  static final Player PLAYER = new Player();

    private Player(){}


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

    public static Player getPlayer() {
        return PLAYER;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
