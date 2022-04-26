package com.example.game;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Nie Weilin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"round","start","end","score"})
@Data
public class RoundInfo extends PlayerInfo {
    public static RoundInfo roundInfo =new RoundInfo();
    private LocalDateTime start;
    private LocalDateTime end;
    private double score;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private HashMap<String,Double>table;
    public int empty=10;
    boolean chessMax =true;
    public int round=0;
    public ArrayList<Integer> playerStep=new ArrayList<>();
    public boolean reset=false;

    public static RoundInfo getRoundInfo() {
        return roundInfo;
    }

    public int getRound() {
        return round;
    }

    public int roundCounter(int i){
        round+=i;
        return round;
    }
    public void Save(){}
    public HashMap<String,Double> scoreTable(HashMap<String,Double> table){
            table.put(getName(), score());
     return table;
    }

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
