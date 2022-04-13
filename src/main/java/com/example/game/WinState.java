package com.example.game;

import java.time.LocalDateTime;

public class WinState extends JudgeState {
    public double score(){

        double scored = r.roundCounter();
        return 0;
    }
    public void winGraph(){}
    public LocalDateTime  finishTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game over at"+dt);
            return dt;
    }
}
