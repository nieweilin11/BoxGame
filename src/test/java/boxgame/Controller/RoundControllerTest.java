package boxgame.Controller;

import boxgame.Model.Player;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;

class RoundControllerTest {
    ArrayList<Integer> playerStep = new ArrayList<>() ;
    private final RoundController roundController = RoundController.getRoundController();
    private final Player player = Player.getPlayer();
    @Test
    void initPuzzle() {
        roundController.initPuzzle(playerStep);
        ArrayList<Integer> integerArrayList =new ArrayList<>();
        for (int i = 0; i < 16; i++) {integerArrayList.add(0);}
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                integerArrayList.set(i, 1);
            } else {
                integerArrayList.set(i, 2);
            }
        }
        Assert.assertEquals(playerStep,integerArrayList);

    }
    @Test
    void JudgePlayerMovement() {
        for (int i = 0; i < 16; i++){
            playerStep.add(0);
        }
        for (int i = 0; i < 6; i++) {
            if (i < 3 ) {
                playerStep.set(i, 1);
            } else {
                playerStep.set(i, 2);
            }
        }
        Player.getPlayer().setPlayerStep(playerStep);
        System.out.println(Player.getPlayer().getPlayerStep());
        roundController.judgePlayerMovement();
        Assert.assertTrue(player.isFinished());
    }

    @Test
    void score(){
        double d = 0.3333333333333333;
        d = (double) Math.round(d * 100) / 100;
        System.out.println(d);
    }


}