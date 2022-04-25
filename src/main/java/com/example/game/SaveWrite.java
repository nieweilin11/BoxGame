package com.example.game;

import lombok.Data;
import org.json.JSONObject;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class SaveWrite extends RoundInfo {
    private static SaveWrite saveWrite= new SaveWrite();
    private String name=PlayerInfo.player.getName();
    private LocalDateTime start=RoundInfo.roundInfo.getStart();
    private LocalDateTime end=RoundInfo.roundInfo.getEnd();
    private double score=RoundInfo.roundInfo.getScore();
    private ArrayList<Integer> puzzle = RoundInfo.roundInfo.getP();
    public JSONObject save=new JSONObject();

    public static SaveWrite getSaveWrite() {
        return saveWrite;
    }
    public void Writhe (){
        //要写入的数据

        File f = new File("/"+name+".json");
        //将数据写入.json文件--start
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), "UTF-8"));
           /* writer.write(jsonString);*/
            save.put("Name",name);
            save.put("Start",start);
            save.put("End",end);
            save.put("Score",score);
            save.put("Puzzle",puzzle);
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
