package com.example.game;

import java.time.LocalDateTime;

public class JudgeState extends RoundInfo {
    public void judgePlayerMovement(){}
    public void updatePuzzle(){}
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
