package Model;

/**
 * @author Fish
 */
public class Player {
    private static final Player PLAYER =new Player();
    private Player(){}
    private String playerName;

    public static Player getPlayer() {
        return PLAYER;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    }
