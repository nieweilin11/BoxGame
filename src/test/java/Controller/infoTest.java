package Controller;

import java.util.ArrayList;


public class infoTest {
public static infoTest infoTest=new infoTest();


    private ArrayList<Integer> playerStep=new ArrayList<>();
    private String playerName;
    private double score;

    public  ArrayList<Integer> getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(ArrayList<Integer> playerStep) {
        this.playerStep = playerStep;
    }

    public  String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public  double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public  boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    private boolean isFinished=false;

}
