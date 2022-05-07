package Controller;

import Model.Player;
import Model.Round;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Nie Weilin
 */
@Data
public class SaveWrite {
private static SaveWrite saveWrite=new SaveWrite();

 Player player= Player.getPlayer();
 Round round= Round.getRound();
 PlayerInfo playerInfo=PlayerInfo.getPlayerInfo();
 RoundInfo roundInfo=RoundInfo.getRoundInfo();
    public static SaveWrite getSaveWrite() {
        return saveWrite;
    }

    private String name= player.getPlayerName();
    private  LocalDateTime start= round.getStart();
    private LocalDateTime end=round.getEnd();
    private double score=round.getScore();
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
        System.out.println("Saved as:"+player.getPlayerName());
        File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources"+ player.getPlayerName()+".json");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), StandardCharsets.UTF_8));
            save.put("Name",player.getPlayerName());
            save.put("Start",round.getStart());
            save.put("End",round.getEnd());
            save.put("Score",round.getScore());
            save.put("Puzzle",toJsonArray(round.getPlayerStep()));
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
    public void read(){
        File file=new File("mejson");
    }
}
