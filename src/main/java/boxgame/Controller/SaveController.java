package boxgame.Controller;

import boxgame.Model.Player;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.IOUtils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nie Weilin
 */
public class SaveController {
    private final Player player = Player.getPlayer();
    private static final SaveController SAVE_CONTROLLER = new SaveController();

    private final JSONObject json = fileToJson();
    private final JSONObject save = new JSONObject();
    private final List<String> playerNameList = new ArrayList<> (json.keySet());
    private final List<JSONObject>playerJsonList = new ArrayList<>();
    private final List<JSONObject>validPlayer = new ArrayList<>();

    /**
     * load the json file as String and assign to the json.
     *
     * @return JsonObject
     */
    private   JSONObject fileToJson() {
        JSONObject json = null;
        try {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("player.json");
            assert is != null;
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        }
        catch (Exception e) {
            System.out.println("player.json" + "Read File Error" + e);
        }
        return json;
    }

    /**
     * store the player's information and combine it with the previous information then write to the player.json.
     */
    public void write() {

        FileWriter writer;
        try {
            writer = new FileWriter("C:\\Users\\Fish\\IdeaProjects\\BoxG\\src\\main\\resources\\player.json", false);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject x = new JSONObject();
        JSONObject jsonObject;
        JSONObject json = fileToJson();
        BufferedWriter out = new BufferedWriter(writer);
        save.put("Name", player.getPlayerName());
        save.put("Score", player.getScore());
        for (int i = 0; i < player.getPlayerStep().size(); i++) {
            String index = String.valueOf(i);
            save.put(index, player.getPlayerStep().get(i));
        }
        save.put("isFinished", player.isFinished());
        jsonObject = JSONObject.parseObject(json.toJSONString());
        for (String s1: jsonObject.keySet()) {
            x.put(s1,jsonObject.getString(s1));
        }
        x.put(save.getString("Name"),save);
        try {
            out.write(x.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sort the playerJsonList by Score.
     */
    public void rank() {
        for (String x:playerNameList) {
            playerJsonList.add(JSONObject.parseObject(json.get(x).toString()));
        }
        playerJsonList.sort((o1, o2) -> {
            double x = o1.getDouble("Score");
            double y = o2.getDouble("Score");
            return (int) (x - y);
        });
    }

    /**
     *
     */
    public void validPlayer() {
        for (JSONObject jsonObject : playerJsonList) {
            if (jsonObject.getBoolean("isFinished")) {
                validPlayer.add(jsonObject);
            }
            validPlayer.sort((o1, o2) -> {
                double x = o1.getDouble("Score");
                double y = o2.getDouble("Score");
                return (int) (y - x);
            });
        }
    }

    /**
     * receive a name from text bar and load the player from the player.json.
     */
    public void read() {
        JSONObject loadedPlayer = null;
        rank();
      for (JSONObject jsonObject : playerJsonList) {
          if (Objects.equals(jsonObject.getString("Name"), player.getPlayerName())) {
              loadedPlayer = JSONObject.parseObject(jsonObject.toJSONString());
          }
      }

        ArrayList <Integer>arrayList = new ArrayList<>();
        for (int  i = 0; i < 16; i++) {
            String index = String.valueOf(i);
            assert loadedPlayer != null;
            arrayList.add(loadedPlayer.getInteger(index));
        }
        player.setPlayerName(loadedPlayer.getString("Name"));
        player.setScore(loadedPlayer.getDouble("Score"));
        player.setFinished(loadedPlayer.getBoolean("isFinished"));
        player.setPlayerStep(arrayList);
    }

    public static SaveController getSaveController() {
        return SAVE_CONTROLLER;
    }
    public List<JSONObject> getValidPlayer() {
        return validPlayer;
    }
}
