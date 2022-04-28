package com.example.game;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Nie Weilin
 */

@Data

public class SaveWrite extends RoundInfo {

    private static SaveWrite saveWrite= new SaveWrite();
    public JSONObject save=new JSONObject();

    /**
     * convert ArrayList<Integer> to JsonArray
     * @param arrayList
     * @return
     */
    public JSONArray toJsonArray(ArrayList<Integer> arrayList){
        JSONArray jsonArray= new JSONArray();
        JSONObject jsonObject=new JSONObject();

        for (int i=0;i<arrayList.size();i++){
           String index=Integer.toString(i);
            jsonObject.put(index,arrayList.get(i));
        }
        jsonArray.put(jsonObject);
        return jsonArray;
    }

    /**
     * collect info and create a saveFile
     */
    public void write(){
        //要写入的数据
        File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources"+player.getPlayerName()+".json");
        //将数据写入.json文件--start
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), StandardCharsets.UTF_8));
            save.put("Name",player.getPlayerName());
            save.put("Start",roundInfo.getStart());
            save.put("End",roundInfo.getEnd());
            save.put("Score",roundInfo.getScore());
            save.put("Puzzle",toJsonArray(RoundInfo.roundInfo.getPlayerStep()));
            writer.write(save.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
