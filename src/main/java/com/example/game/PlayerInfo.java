package com.example.game;//import lombok.Data;

import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;

/**
 * @author Nie Weilin
 */
@Data
public class PlayerInfo {
    private String name;

    public void createPlayer(String name) {
        setName(name);
        File player = new File("C:\\Users\\Fish\\Downloads\\" + File.separator + name + ".xml");
        if (!player.exists()) {
            File dir = new File(player.getParent());
            dir.mkdirs();
            try {
                player.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
