package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Fish
 */
public class Round {
    private  static Round round= new Round();
    private Round(){}
    private LocalDateTime start;
    private  int roundS;

    private LocalDateTime end;
    private double score;
    private int rounds =0;
    private ArrayList<Integer> playerStep=new ArrayList<>();


    public ArrayList<Integer> getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(ArrayList<Integer> playerStep) {
        this.playerStep = playerStep;
    }

    public int getRoundS() {
        return roundS;
    }

    public void setRoundS(int roundS) {
        this.roundS = roundS;
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

    public int getRounds() {
        return rounds;
    }

    public static Round getRound() {
        return round;
    }

    public static void setRound(Round round) {
        Round.round = round;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
