package com.example.game;

import java.time.LocalDateTime;

public class JudgeState extends RoundInfo {
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
    public double score(){

        return 0;
    }
    public void winGraph(){}
    public LocalDateTime finishTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at"+dt);
        return dt;
    }
}
