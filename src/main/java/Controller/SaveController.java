package Controller;

import Model.Player;
import Model.Round;
import com.alibaba.fastjson2.JSON;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tinylog.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Nie Weilin
 */
@Data
public class SaveController {
    private static SaveController saveController =new SaveController();
    private Player player= Player.getPlayer();
    private Round round= Round.getRound();
    private PlayerController playerController = PlayerController.getPlayerController();
    private RoundController roundController = RoundController.getRoundController();
    private String name= player.getPlayerName();
    private  LocalDateTime start= round.getStart();
    private LocalDateTime end=round.getEnd();
    private double score=round.getScore();
    private String savePath;



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
        File saveFile = new File("C:\\Users\\Fish\\Downloads\\" + player.getPlayerName() + ".json");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile,false), StandardCharsets.UTF_8));

            save.put("Name",player.getPlayerName());
            save.put("Start",round.getStart());
            save.put("End",round.getEnd());
            save.put("Score",round.getScore());
            save.put("Puzzle",toJsonArray(round.getPlayerStep()));
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
        BufferedReader bufferedReader=null;
        StringBuilder stringBuffer;
        ClassLoader classLoader= this.getClass().getClassLoader();
        InputStream inputStream;
        String info=null;
        try {
            inputStream = new FileInputStream(Objects.requireNonNull(classLoader.getResource(savePath),"utf-8").getFile());
            Logger.trace("load saveFile"+savePath);
            stringBuffer=new StringBuilder();
            String temp;
            while(true){
                try {
                    assert false;
                    if ((temp = bufferedReader.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stringBuffer.append(temp);
                info=stringBuffer.toString();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(info);
    }
    public  void loadFile() {
        JSONObject jsonObject=new JSONObject();
        /*Round round1= JSON.parseObject();*/
    /*            JSONObject jsonObject = JSON.parseObject(info);
        JSONObject address = jsonObject.getJSONObject("address");*/
    }
    public static SaveController getSaveController() {
        return saveController;
    }
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
