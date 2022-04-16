package com.example.game;//import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author Fish
 */ //@Data
public class PlayerInfo {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    private LocalDateTime end;


    public void createPlayer() {
        System.out.println("Please enter a player name");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.toString();
        setName(sc.toString());
        File player = new File("D:" + File.separator + playerName + ".xml");
        sc.close();

    }
    public void loadPlayer(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert in != null;
                if ( in.readLine() == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
