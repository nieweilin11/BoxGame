package com.example.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class RoundInfo extends PlayerInfo {
    private LocalDateTime start;
    private LocalDateTime end;
    private double score;
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    double[] st = new double[10];
    boolean chessMax =true;
    public int round=0;
    ArrayList<Integer>p = new ArrayList<>();
    public ArrayList<Integer> playerStep=new ArrayList<>();
    public boolean reset=false;

    public int getRound() {
        return round;
    }

    public int roundCounter(int i){
        round=+i;
        return round;
    }
    public void exitAndSave(){}
    public double[] scoreTable(){
        for (int i=0;i<10;i++){
            st[i]=0;
        }
        Arrays.sort(st);
     return st;
    }

    public void judgePlayerMovement(int[] p){
        int r_1,r_2,r_3,b_1,b_2,b_3;
        for(int i=1;i<11;i++){
            r_1=p[i];r_2=p[i+1];r_3=p[i+2];
            b_1=p[i+3];b_2=p[i+4];b_3=p[i+5];
            if (r_1==1&&b_1==2&&r_1==r_2&&r_2==r_3&&b_1==b_2&&b_2==b_3){
                System.out.println("win");
            }
            else updatePuzzle(p);
        }
    }
    public void updatePuzzle(int[] p){}



    public LocalDateTime startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at: "+dt);
        setStart(dt);
        return dt;
    }
    public LocalDateTime endTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at: "+dt);
        setEnd(dt);
        return dt;
    }    public double score(){
        java.time.Duration duration = java.time.Duration.between(startTime(),endTime());
        double time =duration.toSeconds();
        return time/getRound();
    }
}
