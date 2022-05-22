package Controller;

import BoxGame.Model.Player;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class SaveControllerTest {
    private final Player round = Player.getPlayer();
    private final JSONObject save = new JSONObject();


    public  JSONObject fileToJsonTest(String fileName) {
        JSONObject json = null;
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName)
        ) {
            assert is != null;
            json = JSONObject.parseObject(IOUtils.toString(is, "utf-8"));
        } catch (Exception e) {
            System.out.println(fileName + "Read File Error" + e);
        }
        return json;
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
    public void JsonArrayTest() throws IOException {
        JSONArray array=new JSONArray();
       JSONObject object=new JSONObject();
    JSONObject object1=new JSONObject();
    JSONObject object2=new JSONObject();
    object2.put("name","1aad");
    object1.put("name","14");
        object.put("name","1a");
        array.add(object);
        array.add(object1);
        array.add(object2);
    array.add(object);
    for (Object o : array) {
        System.out.println(o);
    }
    FileWriter writer = new FileWriter("player.json");
    BufferedWriter out = new BufferedWriter(writer);
    try {
        out.write(array.toString());
        out.flush();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}


    @Test
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
    System.out.println(json.toJSONString());
    BufferedWriter out = new BufferedWriter(writer);
    save.put("Name", "nie12");
    save.put("Score", "6");
    ArrayList <Integer> s= new ArrayList<>();
    for(int i=0;i<16;i++){
        s.add(1);
    }
    for (int i = 0; i < s.size(); i++) {
        String index = String.valueOf(i);
        save.put(index,s.get(i));
    }
    save.put("isFinished", "true");
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
        System.out.println(json);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}

    String fileName = "player.json";
    JSONObject json = fileToJsonTest(fileName);
    List<String> playerNameList = new ArrayList<>(json.keySet());
    List<JSONObject>playerList=new ArrayList<>();
    @Test
    public void rank1(){
    }
    @Test
    public void rank(){
        for (String x:playerNameList){
            playerList.add(JSONObject.parseObject(json.get(x).toString()));
        }
        playerList.sort((o1, o2) -> {
            double x =  o1.getDouble("Score");
            double y = o2.getDouble("Score");
            return (int) (y - x);
        });

    }
    @Test
    public void read(){
        rank();
        for (JSONObject jsonObject:playerList){
            System.out.println(jsonObject.getBoolean("isFinished"));
            System.out.println(jsonObject.getString("Name"));
            System.out.println(jsonObject.getDouble("Score"));
            ArrayList<Integer> arrayList=new ArrayList<>();
            for (int i=0 ;i<16;i++ ){
                String x= String.valueOf(i);
                arrayList.add(jsonObject.getInteger(x));
            }
            System.out.println(arrayList);
        }
    }

}

