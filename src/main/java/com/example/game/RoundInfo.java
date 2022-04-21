package com.example.game;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Nie Weilin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RoundInfo extends PlayerInfo {
    private LocalDateTime start;
    private LocalDateTime end;
    private double score;
    public int empty=10;
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
        round+=i;
        return round;
    }
    public void exitAndSave(){}
    public double[] scoreTable(){
        for (int i=0;i<empty;i++){
            st[i]=0;
        }
        Arrays.sort(st);
     return st;
    }

    public void judgePlayerMovement(int[] p){
        int r1,r2,r3,b1,b2,b3;
        for(int i=1;i<empty+1;i++){
            r1=p[i];r2=p[i+1];r3=p[i+2];
            b1=p[i+3];b2=p[i+4];b3=p[i+5];
            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                System.out.println("win");
            }
            else {
                updatePuzzle(p);
            }
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
        return getRound()/time;
    }
}
