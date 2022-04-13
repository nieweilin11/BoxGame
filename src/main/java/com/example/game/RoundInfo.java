package com.example.game;

import java.util.Arrays;
import java.util.Scanner;

public class RoundInfo extends PlayerInfo {
    RoundInfo r =new RoundInfo();
    PlayerInfo p=new RoundInfo();
    double[] st = new double[10];
    public int round=0;
    public int[]playerChoose=new int[2];
    public void playerMovement(){
        try {
            System.out.println("Please choose two near chess to move:_ _");

            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                for (int i = 0; i < 2; i++) playerChoose[i] = 0;
            }
            throw new IllegalArgumentException(sc.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public int roundCounter(){
        round++;
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
}
