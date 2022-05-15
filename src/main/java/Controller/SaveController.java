package Controller;

import Model.Round;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.tinylog.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Nie Weilin
 */
@Data
public class SaveController {
    private Round round= Round.getRound();
    private static SaveController saveController =new SaveController();



    private PlayerController playerController = PlayerController.getPlayerController();
    private RoundController roundController = RoundController.getRoundController();
    private String name= round.getPlayerName();
    private double score=round.getScore();
    private String savePath;
    private  Gson jsonData=new Gson();




    private JSONObject save=new JSONObject();
    private JsonArray playerList=new JsonArray();
    private JSONObject rank=new JSONObject();
    public static com.alibaba.fastjson.JSONObject fileToJson(String fileName) {
        com.alibaba.fastjson.JSONObject json = null;
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)
        ) {
            assert is != null;
            json = com.alibaba.fastjson.JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        } catch (Exception e) {
            System.out.println(fileName + "Read File Error" + e);
        }
        return json;
    }

    /**
     * collect info and create a saveFile
     */
    public void write(){
        FileWriter saveFile;
        try {
            saveFile = new FileWriter("player.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter writer = null;
        try {
            String fileName = savePath;
            com.alibaba.fastjson.JSONObject json = SaveController.fileToJson(fileName);
            String score=String.valueOf(round.getScore());
            /*writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile,false), StandardCharsets.UTF_8));
            */
            writer=new BufferedWriter(saveFile);
            rank.put(score,round.getPlayerName());
            save.put("Name",round.getPlayerName());
            save.put("Score",round.getScore());
            for(int i=0;i<round.getPlayerStep().size();i++){
                String index=String.valueOf(i) ;
                save.put(index, round.getPlayerStep().get(i));
            }
            save.put("isFinished",round.isFinished());
            writer.write(save.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void rank(){
        String fileName = "Rank.json";
        com.alibaba.fastjson.JSONObject json = SaveController.fileToJson(fileName);
        Set<String> scoreRank =json.keySet();
        ArrayList<String>rank= new ArrayList<>(scoreRank.size());
        rank.addAll(scoreRank);
    }

    /**
     * read all puzzle information and player information from a save file
     */
public void read(){
    String fileName = savePath;
    com.alibaba.fastjson.JSONObject json = SaveController.fileToJson(fileName);
    Round.getRound().setPlayerName(json.getString("Name"));
    Round.getRound().setFinished(json.getBoolean("isFinished"));
    Round.getRound().setScore(json.getDouble("Score"));
    Logger.trace(round.getPlayerStep());
    ArrayList <Integer>arrayList=new ArrayList<>();
    for(int i=0;i<16;i++){
        String index=String.valueOf(i);
        arrayList.add(json.getInteger(index));
    }
    Round.getRound().setPlayerStep(arrayList);
}
    public static SaveController getSaveController() {
        return saveController;
    }
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
