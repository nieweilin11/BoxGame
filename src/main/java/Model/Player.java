package Model;

/**
 * @author Fish
 */
public class Player {
    private static Player player=new Player();
    private Player(){}
    private String playerName;



    public static Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    }
