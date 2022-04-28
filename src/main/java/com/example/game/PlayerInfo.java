package com.example.game;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;


/**
 * @author Nie Weilin
 */

@Data
public class PlayerInfo {
    public static PlayerInfo player =new PlayerInfo();
    private String playerName;
    public JSONObject load=new JSONObject();

    /**
     * create a player saveFile
     * @param name
     */

    public void createPlayer(String name) {
        player.setPlayerName(name);
        File player = new File("C:\\Users\\Fish\\Downloads\\" + File.separator + name + ".json");
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

    /**
     * load player info from a saveFile
     * @param file
     */
    public void loadPlayer(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                player.setPlayerName( load.getString("name"));
                RoundInfo.roundInfo.setScore(load.getDouble("Score"));
                RoundInfo.roundInfo.setPlayerStep(toIntegerArray(load.getJSONArray("Puzzle")));
                assert in != null;
                if ( in.readLine() == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * convert a JsonArray to ArrayList<Integer>
     * @param jsonArray
     * @return
     */
    public ArrayList<Integer>toIntegerArray(JSONArray jsonArray){
        ArrayList<Integer>arrayList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++) {
            arrayList.add(Integer.parseInt(jsonArray.getString(i)));
        }
        return arrayList;
    }    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
