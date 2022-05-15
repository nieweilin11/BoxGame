package GameView;

import Controller.RoundController;
import Model.Round;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SetJavaFxObjectTest {
    private final RoundController roundController = RoundController.getRoundController();

    @Test
    void selected() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        roundController.initPuzzle(arrayList);
        ArrayList <Integer>arrayList1 = new ArrayList<>();
        for (int i=0;i<16;i++){arrayList1.add(0);}
        int i = 5;

        if (arrayList.get(1)!= 0) {
            arrayList1.set(1,arrayList.get(i));
            System.out.println(arrayList.get(i));
            System.out.println(arrayList1);
            System.out.println(arrayList);
        }

    }
}