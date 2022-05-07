package GameView;

import Model.Player;
import Model.Round;
import Controller.PlayerInfo;
import Controller.RoundInfo;
import Controller.SaveWrite;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import static GameView.SetView.*;

/**
 * @author Nie Weilin
 */
public class Main extends Application {
    /**
     * instant class objects
     */


    Player player= Player.getPlayer();
    Round round= Round.getRound();
    PlayerInfo playerInfo=PlayerInfo.getPlayerInfo();
    SaveWrite saveWrite= SaveWrite.getSaveWrite();
    RoundInfo roundInfo=RoundInfo.getRoundInfo();


    public void convert(ArrayList<Integer>arrayList,List<Circle>list){
        Circle red=new Circle(11);
        red.setFill(Color.rgb(128,0,0));
        Circle black=new Circle(11);
        black.setFill(Color.rgb(0,0,0));
        Circle empty=new Circle(11);
        empty.setFill(Color.rgb(0,0,0));
        for (int i=0;i<list.size();i++){
            if(list.get(i).getFill()==red.getFill()){arrayList.set(i,1);}
            else if(list.get(i).getFill()==black.getFill()){arrayList.set(i,2);}
            else if(list.get(i).getFill()==empty.getFill()){arrayList.set(i,0);}
        }
    }
    /**
     *
     * @param i
     * @return int
     */

    public void selected(int i){
        System.out.println(i);
        int pair =2;
        if (roundInfo.getPlayerStep().size()<pair) {
            roundInfo.getPlayerStep().add(i);
        } else {
            roundInfo.getPlayerStep().remove(roundInfo.getPlayerStep().size()-1);
            roundInfo.setChessMax(true);
        }
        System.out.println(roundInfo.getPlayerStep());
        if (roundInfo.getPlayerStep().size()<pair*2) {

            roundInfo.getPlayerStep().add(i);
        }
        else {
            roundInfo.setChessMax(false);
        }

    }
    /**
     *
     * @param i
     * @return int
     */
    public int putBack(int i){
        return i;
    }

    final int totalBox=16;
    final int totalStone=6;
    List<Circle>puzzleList =new ArrayList<>();
    List<Polyline>boxsList =new ArrayList<>();
    @Override
    public void init() throws Exception {
        super.init();
        for (int i=1;i<totalBox+2;i++){
            boxsList.add(setBoxPosition(setBox(),60.0*i));
        }
        puzzleList.add(setRedStone(0));
        puzzleList.add(setBlackStone(1));
        puzzleList.add(setRedStone(2));
        puzzleList.add(setBlackStone(3));
        puzzleList.add(setRedStone(4));
        puzzleList.add(setBlackStone(5));
        for (int i=totalStone;i<totalBox;i++) {
            puzzleList.add(setEmpty(i));
        }
        roundInfo.initPuzzle(roundInfo.getPlayerStep());
    }

    @Override
    public void start(Stage primaryStage) {

        /*
          instantiate nodes
         */
        int line=11;
        ArrayList<Line> lList = new ArrayList<>();
        for (int i=0;i<line;i++){
            lList.add(new Line(0,46*i,200,46*i));
        }
        Line line1=new Line(30,0,30,500);
        lList.add(line1);
        ArrayList<Label>rank=new ArrayList<>();
        for (var i=0;i<line;i++){
            String text=""+i;
            rank.add(new Label(text));
            setLabelPosition(rank.get(i),4,46*i);
            rank.get(i).setFont(new Font(26));
        }
        Button newGame=new Button("Start New Game");
        Button score =new Button("Rank");
        Button aboutMe=new Button("About Me");
        Button loadGame =new Button("Load a Game");
        Button save =new Button("Save");
        Button homeGame =new Button("Home");
        Button homeLoad =new Button("Home");
        Button homeNew =new Button("Home");
        Button loadConfirm=new Button("confirm");
        Button newConfirm=new Button("confirm");
        Label homeTitle=new Label("BoxGame");
        Label roundS=new Label("Round :");
        homeTitle.setTextFill(Color.rgb(0,0,0));
        homeTitle.setFont(new Font(50));
        roundS.setFont(new Font(15));
        TextField saveFile=new TextField();
        TextField name=new TextField();

        /*
          set buttons,textFiled and labors
         */
        setButtonPosition(save,880,450);
        setButtonPosition(aboutMe,200,450);
        setButtonPosition(homeLoad,400,450);
        setButtonPosition(homeNew,400,450);
        setButtonPosition(loadGame,400,450);
        setButtonPosition(score,935,420);
        setTextFile(saveFile,150,200);
        setTextFile(name,150,200);
        setButtonPosition(loadConfirm,250,220);
        setButtonPosition(newConfirm,250,220);
        setButtonPosition(newGame,300,300);
        setButtonPosition(loadGame,100,300);
        setButtonPosition(homeGame,930,450);
        setLabelPosition(roundS,10,10);
        setLabelPosition(homeTitle,30,195);
        setLabelSize(homeTitle,80,400);
        homeTitle.setAlignment(Pos.CENTER);

        /*
          instantiate panes and set panes
         */
        AnchorPane homePane =new AnchorPane();
        AnchorPane gamePane =new AnchorPane();
        AnchorPane loadPane =new AnchorPane();
        AnchorPane newGamePane =new AnchorPane();
        AnchorPane scorePane =new AnchorPane();
        homePane.setStyle(
                "-fx-background-image: url(" + "file:src/main/resources/Image/red.jpg" + "); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-color:  transparent;");
        gamePane.setStyle(
                "-fx-background-image: url(" + "file:src/main/resources/Image/game.jpg" + "); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-color:  transparent;");
        loadPane.setStyle(
                "-fx-background-image: url(" + "file:src/main/resources/Image/black.jpg" + "); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-color:  transparent;");
        newGamePane.setStyle(
                "-fx-background-image: url(" + "file:src/main/resources/Image/black.jpg" + "); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-color:  transparent;");
        /*
          instantiate scenes
         */
        Scene primaryScene =new Scene(homePane,500,500);
        Scene gameScene=new Scene(gamePane,1000,500);
        Scene loadScene=new Scene(loadPane,500,500);
        Scene newGameScene=new Scene(newGamePane,500,500);
        Scene scoreScene=new Scene(scorePane,200,500);
        /*
          set red and black stone  and emptyBox click event
         */
        ArrayList<Integer>q=new ArrayList<>();
        for (int i=0;i<totalBox;i++) {
            int finalI = i;
            if (roundInfo.isChessMax()) {
                puzzleList.get(i).setOnMouseClicked(mouseEvent -> {
                    q.add(finalI);
                    if (puzzleList.get(finalI).getLayoutY() > 180) {
                        puzzleList.get(finalI).setLayoutY(180);
                        roundInfo.roundCounter(1);
                        selected(finalI);

                    }
                    else {
                        puzzleList.get(finalI).setLayoutY(220);
                    }
                    roundInfo.roundCounter(-1);
                    putBack(finalI);
                    if (roundInfo.isReset()){
                        puzzleList.get(q.size()-1).setLayoutY(220);
                        puzzleList.get(q.size()-2).setLayoutY(220);
                        System.out.println(q.size()-2);
                        roundInfo.roundCounter(-1);
                        System.out.println(q);
                    }
                    roundInfo.setReset(false);
                    convert(round.getPlayerStep(),puzzleList);
                });
            }
        }
        /*
          set textFile obtains files absolute address event
         */
        saveFile.setOnDragOver(dragEvent -> dragEvent.acceptTransferModes(TransferMode.ANY));
        saveFile.setOnDragDropped(dragEvent -> {
            dragEvent.getDragboard();
            if (dragEvent.getDragboard().hasFiles()) {
                String path = dragEvent.getDragboard().getFiles().get(0).getAbsolutePath();
                saveFile.setText(path);
                player.setPlayerName(saveFile.getText());//差点东西
                playerInfo.loadPlayer(player.getPlayerName());
            }
        });
        /*
          set promptText
         */
        name.setPromptText("      Enter your name");
        saveFile.setPromptText("Please drop a save file ");

        /*
          set buttons click events
         */

        newConfirm.setOnAction(actionEvent -> {
            player.setPlayerName(name.getText());
            System.out.println("name:" + player.getPlayerName());
            playerInfo.createPlayer(player.getPlayerName());
            roundInfo.startTime();
            primaryStage.setScene(gameScene);
        });
        homeGame.setOnAction(actionEvent -> {
            primaryStage.setScene(primaryScene);
            saveWrite.write();
        });
        aboutMe.setOnAction(actionEvent -> getHostServices().showDocument("https://github.com/nieweilin11"));
        loadConfirm.setOnAction(actionEvent -> primaryStage.setScene(gameScene));
        newGame.setOnAction(actionEvent -> primaryStage.setScene(newGameScene));
        homeNew.setOnAction(actionEvent -> primaryStage.setScene(primaryScene));
        loadGame.setOnAction(actionEvent -> primaryStage.setScene(loadScene));
        homeLoad.setOnAction(actionEvent -> primaryStage.setScene(primaryScene));
        score.setOnAction(actionEvent -> {
            Stage scoreStage =new Stage();
            scoreStage.setHeight(500);
            scoreStage.setWidth(200);
            scoreStage.setScene(scoreScene);
            scoreStage.show();
        });
        /*
          add nodes into pane
         */
        homePane.getChildren().addAll(newGame,loadGame,homeTitle,aboutMe);
        loadPane.getChildren().addAll(homeLoad,loadConfirm,saveFile);
        newGamePane.getChildren().addAll(newConfirm,homeNew,name);
        gamePane.getChildren().addAll(save,homeGame,roundS,score);
        for (Circle circle : puzzleList) {
            gamePane.getChildren().add(circle);
        }
        for (var i=1;i<boxsList.size();i++){
            gamePane.getChildren().add(boxsList.get(i));
        }
        for (Line value : lList) {
            scorePane.getChildren().addAll(value);
        }
        for (Label label : rank) {
            scorePane.getChildren().add(label);
        }

        /*
          add panes into stage and set stage
         */

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BoxGame");
        primaryStage.centerOnScreen();
        primaryStage.setY(primaryStage.getY() * 3f / 2f);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(
                new Image("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources\\Image\\29x29.jpg")
        );
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.print("Windows shut down");
            saveWrite.write(); //json file don't find now
            /*saveWrite.write();*/ //json file don't find now
            roundInfo.endTime();
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {launch();}

}