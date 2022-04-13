package com.example.game;//import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

//@Data
public class PlayerInfo {
    public PlayerInfo player=new RoundInfo();
    public void startGame(){
        try {

            System.out.println("Please choose state a new game or load a game");
            Scanner sc = new Scanner(System.in);
            String choose = sc.toString();
            if (choose.length()>1)throw new Exception("Input name error");
            switch (choose) {
                case "N" -> createPlayer();
                case "L" -> loadPlayer();
                default -> throw new IllegalArgumentException();
            }
            sc.close();
        }
        catch (Exception e){
           e.printStackTrace();
        }

    }
    /**
     *
     */
    public void createPlayer(){
        System.out.println("Please enter a player name");
        Scanner sc =new Scanner(System.in);
        String playerName=sc.toString();
        File player =new File("D:"+File.separator+playerName+".xml");
        sc.close();

    }
    /**
     *
     */
    public void loadPlayer() {
        System.out.println("Please enter a player to load game");
        Scanner sc = new Scanner(System.in);
        String load = sc + "xml";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(load));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert in != null;
                if ( in.readLine() == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return StartTime
     */
    public LocalDateTime startTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Game start at"+dt);
       return dt;
    }
}
