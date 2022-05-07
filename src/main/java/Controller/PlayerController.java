package Controller;

import Model.Player;
import Model.Round;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;


/**
 * @author Nie Weilin
 */

@Data
public class PlayerController {
    private static PlayerController playerController =new PlayerController();
    private Player player= Player.getPlayer();
    private Round round= Round.getRound();
    private SaveController saveController = SaveController.getSaveController();
    private RoundController roundController = RoundController.getRoundController();
    private JSONObject load=new JSONObject();

    
    public static PlayerController getPlayerController() {
        return playerController;
    }


    public void createPlayer(String name) {
        player.setPlayerName(name);
        File player = new File("C:\\Users\\Fish\\Downloads\\" + File.separator + name + ".json");
        if (!player.exists()) {
            File dir = new File(player.getParent());
            dir.mkdirs();
            try {
                player.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loadPlayer(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                player.setPlayerName( load.getString("name"));
                round.setScore(load.getDouble("Score"));
                round.setPlayerStep(toIntegerArray(load.getJSONArray("Puzzle")));
                assert in != null;
                if ( in.readLine() == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Integer>toIntegerArray(JSONArray jsonArray){
        ArrayList<Integer>arrayList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++) {
            arrayList.add(Integer.parseInt(jsonArray.getString(i)));
        }
        return arrayList;
    }

}
