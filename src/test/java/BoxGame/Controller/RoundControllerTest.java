package BoxGame.Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RoundControllerTest {

    @Test
    void initPuzzle() {
        ArrayList<Integer> arrayList=new ArrayList<>();
        int total = 16, stone = 6;
        for (int i = 0; i < total; i++) {
            arrayList.add(0);
        }
        for (int i = 0; i < stone; i++) {
            if (i % 2 == 0) {
                arrayList.set(i, 1);
            } else {
                arrayList.set(i, 2);
            }
        }
    }

    @Test
    void judgePlayerMovement() {
        ArrayList<Integer> arrayList= new ArrayList<>();
        for (int i=0;i<16;i++){
            arrayList.add(0);
            if (i<4&&i>-1){arrayList.set(i,1);}
            if (i>3&&i<7){arrayList.set(i,2);}
        }
        System.out.println(arrayList);
        int r1,r2,r3,b1,b2,b3;
        int empty = 10;
        for(int i = 1; i< empty +1; i++){
            r1=arrayList.get(i);r2=arrayList.get(i+1);r3=arrayList.get(i+2);
            b1=arrayList.get(i+3);b2=arrayList.get(i+4);b3=arrayList.get(i+5);

            if (r1==1&&b1==2&&r1==r2&&r2==r3&&b1==b2&&b2==b3){
                System.out.println("You Won");
            }

        }
    }
}