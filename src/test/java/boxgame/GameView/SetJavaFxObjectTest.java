package boxgame.GameView;

import boxgame.Controller.RoundController;
import boxgame.Model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetJavaFxObjectTest {
    Player player = Player.getPlayer();
    RoundController roundController = RoundController.getRoundController();
    ArrayList <Integer> integerArrayList = new ArrayList<>();

    @Test
    void selected() {
        ArrayList<Integer> test = new ArrayList<>();
        roundController.initPuzzle(test);
        int choose =1;
        int i =9;
        System.out.println(test);
        roundController.initPuzzle(integerArrayList);
        player.setPlayerStep(integerArrayList);
        roundController.getTempSelect();
            SetJavaFxObject.selected(i, choose);
            assertEquals(test.get(i), roundController.getTempSelect().get(i));

    }

    @Test
    void reSet() {
     roundController.initPuzzle(roundController.getTempSelect());
     ArrayList <Integer> arrayList = new ArrayList<>();
     for (int i = 0; i<32;i++) {
         arrayList.add(0);
     }
        System.out.println(roundController.getTempSelect());
        SetJavaFxObject.reset();
        assertEquals(arrayList,roundController.getTempSelect());
    }

    @Test
    void ron() {

    }

    @Test
    void selectEvent() {

    }
}