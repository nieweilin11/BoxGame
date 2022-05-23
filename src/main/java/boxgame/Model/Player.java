package boxgame.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Nie Weilin
 */
public class Player {
    /**
     * Singleton.
     */
    private  static final Player PLAYER = new Player();

    private Player() {}

    /**
     * Player's name.
     */
    private String playerName;
    /**
     * Game start time.
     */
    private LocalDateTime start;
    /**
     * Game end time.
     */
    private LocalDateTime end;
    /**
     *  Player's score default score = 0 .
     */
    private double score = 0;
    /**
     * Show whether the player finished the game or not.
     */
    private boolean isFinished = false;
    /**
     * The player's game progress.
     */
    private ArrayList<Integer> playerStep = new ArrayList<>();

    public boolean isFinished() {
        return isFinished;
    }
    public void setFinished(boolean finished) {
        isFinished = finished;
    }
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
