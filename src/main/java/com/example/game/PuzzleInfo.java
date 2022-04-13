package com.example.game;

public class PuzzleInfo extends RoundInfo {
    public int[] puzzleGenerate(){

        int[] puzzle= new int[16];
        for (int i=0;i<16;i++){
            if (i<3){
                puzzle[i]=1;
            }
            else if(i<5){
                puzzle[i]=2;
            }
            else puzzle[i]=0;
        }
        return puzzle;
    }
    public void puzzleStore(){

    }
}
