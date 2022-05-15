package Controller;

import Model.Round;
;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;
import java.io.InputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class SaveControllerTest {
    private Round round = Round.getRound();
    private PlayerController playerController = PlayerController.getPlayerController();
    private RoundController roundController = RoundController.getRoundController();
    private JSONObject save = new JSONObject();

    @Test
    void toJsonArray() {
    }

    @Test
    public void writeTest() {
        System.out.println("Saved as:" + round.getPlayerName());
        File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources" + round.getPlayerName() + ".json");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
            save.put("Name", round.getPlayerName());
            save.put("Start", round.getStart());
            save.put("End", round.getEnd());
            save.put("Score", round.getScore());
            writer.write(save.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void write() {
        System.out.println("Saved as:" + "player.getPlayerName()");
        File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources" + round.getPlayerName() + ".json");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
            save.put("Name", "player.getPlayerName()");
            save.put("Start", 1);
            save.put("End", 1);

            writer.write(save.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println(save.toString());
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static JSONObject fileToJson(String fileName) {
        JSONObject json = null;
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        ) {
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        } catch (Exception e) {
            System.out.println(fileName + "Read File Error" + e);
        }
        return json;
    }

    @Test
    public  void read() {
        ArrayList arrayList=new ArrayList();
        String fileName = "C:\\Users\\Fish\\Downloads\\awdawd.json";
        JSONObject json = SaveControllerTest.fileToJson(fileName);

        System.out.println(json.get("Score"));
    }
}

