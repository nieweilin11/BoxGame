package Controller;

import Model.Round;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class SaveControllerTest {
    private final Round round = Round.getRound();
    private final PlayerController playerController = PlayerController.getPlayerController();
    private final RoundController roundController = RoundController.getRoundController();
    private final JSONObject save = new JSONObject();

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
            save.put("Name", infoTest.infoTest.getPlayerName());
            save.put("Score", infoTest.infoTest.getScore());
            for(int i=0;i<round.getPlayerStep().size();i++){
                String index=String.valueOf(i) ;
                save.put(index, round.getPlayerStep().get(i));
            }
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
                System.out.println(save);
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
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)
        ) {
            assert is != null;
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        } catch (Exception e) {
            System.out.println(fileName + "Read File Error" + e);
        }
        return json;
    }

    @Test
    public  void read() {
        String fileName = "C:\\Users\\Fish\\Downloads\\awdawd.json";
        JSONObject json = SaveControllerTest.fileToJson(fileName);
        String name="";
        JSONArray array=json.getJSONArray(name);
        ArrayList<Object> listdata = new ArrayList<>();
        if (array != null) {

            //Iterating JSON array
            //Adding each element of JSON array into ArrayList
            listdata.addAll(array);
        }
        for (Object listdatum : listdata) {
            //Printing each element of ArrayList
            System.out.println(listdatum);
        }
    }

    @Test
    public void rank(){
        String fileName = "player.json";
        com.alibaba.fastjson.JSONObject json = SaveController.fileToJson(fileName);
        Set<String> scoreRank =json.keySet();
        ArrayList<String>rank= new ArrayList<>(scoreRank.size());
        rank.addAll(scoreRank);
        for (String x:rank) {
            System.out.println(x);
        }
    }
@Test
public void Re() throws IOException {
    ArrayList<Integer>s =new ArrayList<>();
    for (int i=0;i<16;i++){
        s.add(1);
        infoTest.infoTest.setFinished(false);
        infoTest.infoTest.setPlayerName("nie");
        infoTest.infoTest.setScore(1);
        infoTest.infoTest.setPlayerStep(s);
    }
    FileWriter writer = new FileWriter("C:\\Users\\Fish\\IdeaProjects\\BoxG\\src\\main\\resources\\player.json");
    BufferedWriter out = new BufferedWriter(writer);

    save.put("Name",infoTest.infoTest.getPlayerName());
    save.put("Score",infoTest.infoTest.getScore());
    for(int i=0;i<infoTest.infoTest.getPlayerStep().size();i++){
        String index=String.valueOf(i) ;
        save.put(index, infoTest.infoTest.getPlayerStep().get(i));
    }
    save.put("isFinished",infoTest.infoTest.isFinished());
    try {
        out.write(save.toString());
        out.flush();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    @Test
    void getSaveController() {
    }

    @Test
    void setSavePath() {
    }

    @Test
    void getRound() {
    }

    @Test
    void getPlayerController() {
    }

    @Test
    void getRoundController() {
    }

    @Test
    void getName() {
    }

    @Test
    void getScore() {
    }

    @Test
    void getSavePath() {
    }

    @Test
    void getJsonData() {
    }

    @Test
    void getPlayerList() {
    }

    @Test
    void getSave() {
    }

    @Test
    void testGetPlayerList() {
    }

    @Test
    void getRank() {
    }

    @Test
    void setRound() {
    }

    @Test
    void setPlayerController() {
    }

    @Test
    void setRoundController() {
    }

    @Test
    void setName() {
    }

    @Test
    void setScore() {
    }

    @Test
    void setJsonData() {
    }

    @Test
    void setPlayerList() {
    }

    @Test
    void setSave() {
    }

    @Test
    void testSetPlayerList() {
    }

    @Test
    void setRank() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}

