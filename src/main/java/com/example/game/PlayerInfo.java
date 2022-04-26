package com.example.game;//import lombok.Data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;


/**
 * @author Nie Weilin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name"})
@Data
public class PlayerInfo {
    public static PlayerInfo player =new PlayerInfo();
    private String name;
    JSONObject load=new JSONObject();

    public static PlayerInfo getPlayer() {
        return player;
    }

    public void createPlayer(String name) {
        setName(name);
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
    public void loadPlayer(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                setName( load.getString("name"));
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
    public ArrayList<Integer>toIntegerArray(JSONArray jsonArray){
        ArrayList<Integer>arrayList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++) {
            arrayList.add(Integer.parseInt(jsonArray.getString(i)));
        }
        return arrayList;
    }
}
