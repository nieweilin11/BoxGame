package Controller;

import Model.Round;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tinylog.Logger;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

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



    private JSONObject save=new JSONObject();
    public static com.alibaba.fastjson.JSONObject fileToJson(String fileName) {
        com.alibaba.fastjson.JSONObject json = null;
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        ) {
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
        System.out.println("Saved as:"+round.getPlayerName());
        File saveFile = new File("C:\\Users\\Fish\\IdeaProjects\\BoxG\\src\\main\\resources\\PlayerSaveFile\\" + round.getPlayerName() + ".json");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile,false), StandardCharsets.UTF_8));

            save.put("Name",round.getPlayerName());
            save.put("Score",round.getScore());
            for(int i=0;i<round.getPlayerStep().size();i++){
                String index=String.valueOf(i) ;
                save.put(index, round.getPlayerStep().get(i));
            }
            save.put("isFinished",round.isFinished());
            Logger.trace(round.getPlayerStep());
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
    ArrayList arrayList=new ArrayList();
    for(int i=0;i<RoundController.getRoundController().getTotalBox();i++){
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
