package Controller;

import Model.Player;
import Model.Round;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SaveWriteTest {
    Player player= Player.getPlayer();
    Round round= Round.getRound();
    PlayerInfo playerInfo=PlayerInfo.getPlayerInfo();
    RoundInfo roundInfo=RoundInfo.getRoundInfo();
    private JSONObject save=new JSONObject();

    @Test
    void toJsonArray() {
    }
    @Test
    public void writeTest(){
        System.out.println("Saved as:"+player.getPlayerName());
        File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources"+ player.getPlayerName()+".json");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), StandardCharsets.UTF_8));
            save.put("Name",player.getPlayerName());
            save.put("Start",round.getStart());
            save.put("End",round.getEnd());
            save.put("Score",round.getScore());
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
    @Test
        public void write(){
            System.out.println("Saved as:"+"player.getPlayerName()");
            File f = new File("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources"+ player.getPlayerName()+".json");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), StandardCharsets.UTF_8));
                save.put("Name","player.getPlayerName()");
                save.put("Start",1);
                save.put("End",1);

                writer.write(save.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
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
    }
