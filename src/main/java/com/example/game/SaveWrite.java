package com.example.game;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class SaveWrite extends RoundInfo {
    private static SaveWrite saveWrite= new SaveWrite();
    private String name=PlayerInfo.player.getName();
    private  LocalDateTime start=RoundInfo.roundInfo.getStart();
    private LocalDateTime end=RoundInfo.roundInfo.getEnd();
    private double score=RoundInfo.roundInfo.getScore();
    public JSONObject save=new JSONObject();


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
    public void write(){
        //要写入的数据

        File f = new File("/"+name+".json");
        //将数据写入.json文件--start
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), StandardCharsets.UTF_8));
           /* writer.write(jsonString);*/
            save.put("Name",name);
            save.put("Start",start);
            save.put("End",end);
            save.put("Score",score);
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
