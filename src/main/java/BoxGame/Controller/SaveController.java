package BoxGame.Controller;

import BoxGame.Model.Player;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.IOUtils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nie Weilin
 */
public class SaveController {
    private final Player player = Player.getPlayer();
    private static final SaveController saveController =new SaveController();

    private final String fileName = "player.json";
    private final JSONObject json = fileToJson(fileName);
    private final List<String> playerNameList = new ArrayList<>(json.keySet());
    private final List<JSONObject>playerJsonList=new ArrayList<>();



    private JSONObject save=new JSONObject();


    public  JSONObject fileToJson(String fileName) {
        JSONObject json = null;
        try {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            assert is != null;
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        }
        catch (Exception e) {
            System.out.println(fileName + "Read File Error" + e);
        }
        return json;
    }




    public void write() {

        FileWriter writer;
        try {
            writer = new FileWriter("C:\\Users\\Fish\\IdeaProjects\\BoxG\\src\\main\\resources\\player.json", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject x=new JSONObject();
         JSONObject jsonObject ;
        JSONObject json = fileToJson("player.json");
        BufferedWriter out = new BufferedWriter(writer);
        save.put("Name", player.getPlayerName());
        save.put("Score", player.getScore());
        for (int i = 0; i < player.getPlayerStep().size(); i++) {
            String index = String.valueOf(i);
            save.put(index, player.getPlayerStep().get(i));
        }
        save.put("isFinished", player.isFinished());
        jsonObject = com.alibaba.fastjson.JSONObject.parseObject(json.toJSONString());
        for(String s1: jsonObject.keySet()){
            x.put(s1,jsonObject.getString(s1));
        }
        x.put(save.getString("Name"),save);
        try {
            out.write(x.toString());
            out.flush();
            out.close();
            json = fileToJson("player.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void rank(){
        for (String x:playerNameList){
            playerJsonList.add(JSONObject.parseObject(json.get(x).toString()));
        }
        playerJsonList.sort((o1, o2) -> {
            double x =  o1.getDouble("Score");
            double y = o2.getDouble("Score");
            return (int) (y - x);
        });
    }


public void read(){
    String fileName = "player.json";
    JSONObject json = fileToJson(fileName);
    JSONObject player= JSONObject.parseObject(json.get(this.player.getPlayerName()).toString());
    Player.getPlayer().setPlayerName(player.getString("Name"));
    Player.getPlayer().setFinished(player.getBoolean("isFinished"));
    Player.getPlayer().setScore(player.getDouble("Score"));
    ArrayList <Integer>arrayList=new ArrayList<>();
    for(int i=0;i<16;i++){
        String index=String.valueOf(i);
        arrayList.add(player.getInteger(index));
    }
    Player.getPlayer().setPlayerStep(arrayList);
}
    public static SaveController getSaveController() {
        return saveController;
    }
    public List<JSONObject> getPlayerJsonList() {
        return playerJsonList;
    }
}
