package BoxGame.GameView;

import BoxGame.Controller.RoundController;
import BoxGame.Controller.SaveController;
import BoxGame.Model.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static BoxGame.GameView.SetJavaFxObject.*;

/**
 * @author Nie Weilin
 */
public class Main extends Application {

    private final Player player = Player.getPlayer();
    private final SaveController saveController = SaveController.getSaveController();
    private final RoundController roundController = RoundController.getRoundController();
    private final List <Circle>puzzleList= roundController.getPuzzleList();
    private final List <Polyline>boxList =roundController.getBoxsList();

    @Override
    public void start(Stage primaryStage) {
        roundController.init();

        int line = 11;
        ArrayList<Line> lList = new ArrayList<>();
        ArrayList<Label> rank = new ArrayList<>();

        for (int i = 0; i < line; i++) {
            lList.add(new Line(0, 46 * i, 200, 46 * i));
        }
        Line line1 = new Line(30, 0, 30, 500);
        Line line2 = new Line(80, 0, 80, 500);
        lList.add(line1);
        lList.add(line2);

        for (var i = 0; i < line; i++) {
            String text = "" + i;
            rank.add(new Label(text));
            setLabelPosition(rank.get(i), 4, 46 * i);
            rank.get(i).setFont(new Font(26));
        }

        Button newGame = new Button("Start New Game");
        Button score = new Button("Rank");
        Button aboutMe = new Button("About Me");
        Button loadGame = new Button("Load a Game");
        Button save = new Button("Save");
        Button homeGame = new Button("Home");
        Button homeLoad = new Button("Home");
        Button homeNew = new Button("Home");
        Button loadConfirm = new Button("confirm");
        Button newConfirm = new Button("confirm");
        Button quickWin= new Button("QuickWin");
        Label homeTitle = new Label("BoxGame");
        ArrayList<Label> rankName=new ArrayList<>();
        ArrayList<Label> rankScore=new ArrayList<>();
        TextField saveFile = new TextField();
        TextField name = new TextField();
        for (int i=0;i<10;i++){
           rankName.add(new Label("Name"));
            setLabelSize(rankName.get(i),50,60);
            setLabelPosition(rankName.get(i),82,10+i*46);
        }
        for (int i=0;i<10;i++){
            rankScore.add(new Label("0.0"));
            setLabelSize(rankScore.get(i),50,60);
            setLabelPosition(rankScore.get(i),40,10+i*46);
        }

        homeTitle.setTextFill(Color.rgb(0, 0, 0));
        homeTitle.setFont(new Font(50));
        setButtonPosition(quickWin,915, 420);
        setButtonPosition(save, 880, 450);
        setButtonPosition(aboutMe, 200, 450);
        setButtonPosition(homeLoad, 400, 450);
        setButtonPosition(homeNew, 400, 450);
        setButtonPosition(loadGame, 400, 450);
        setButtonPosition(score, 935, 420);
        setTextFile(saveFile, 150, 200);
        setTextFile(name, 150, 200);
        setButtonPosition(loadConfirm, 250, 220);
        setButtonPosition(newConfirm, 250, 220);
        setButtonPosition(newGame, 300, 300);
        setButtonPosition(loadGame, 100, 300);
        setButtonPosition(homeGame, 930, 450);
        setLabelPosition(homeTitle, 30, 195);
        setLabelSize(homeTitle, 80, 400);
        homeTitle.setAlignment(Pos.CENTER);

        AnchorPane homePane = new AnchorPane();
        AnchorPane gamePane = new AnchorPane();
        AnchorPane loadPane = new AnchorPane();
        AnchorPane newGamePane = new AnchorPane();
        AnchorPane scorePane = new AnchorPane();

        homePane.setStyle("-fx-background-image: url(" + "file:src/main/resources/Image/red.jpg" + "); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-color:  transparent;");
        gamePane.setStyle("-fx-background-image: url(" + "file:src/main/resources/Image/game.jpg" + "); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-color:  transparent;");
        loadPane.setStyle("-fx-background-image: url(" + "file:src/main/resources/Image/black.jpg" + "); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-color:  transparent;");
        newGamePane.setStyle("-fx-background-image: url(" + "file:src/main/resources/Image/black.jpg" + "); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-color:  transparent;");

        Scene primaryScene = new Scene(homePane, 500, 500);
        Scene gameScene = new Scene(gamePane, 1000, 500);
        Scene loadScene = new Scene(loadPane, 500, 500);
        Scene newGameScene = new Scene(newGamePane, 500, 500);
        Scene scoreScene = new Scene(scorePane, 200, 500);

        name.setPromptText("      Enter your name");
        saveFile.setPromptText("      Enter your name");

        selectEvent();

        quickWin.setOnAction(actionEvent -> {
            ArrayList<Integer> arrayList= new ArrayList<>();
            for (int i=0;i<16;i++){
                arrayList.add(0);
                if (i<4){arrayList.set(i,1);}
                if (i>3&&i<7){arrayList.set(i,2);}
                roundController.judgePlayerMovement();
            }
            player.setPlayerStep(arrayList);
        });

        newConfirm.setOnAction(actionEvent -> {
            if (name.getText() == null){
                name.setText("player");
            }
            player.setPlayerName(name.getText());
            System.out.println("name:" + player.getPlayerName());
            roundController.startTime();
            roundController.displayStones();
            primaryStage.setScene(gameScene);
        });

        homeGame.setOnAction(actionEvent -> {
            primaryStage.setScene(primaryScene);
            saveController.write();
        });

        loadConfirm.setOnAction(actionEvent -> {
            player.setPlayerName(saveFile.getText());
            if (saveFile.getText() == null){
                saveFile.setText("player");
            }
            System.out.println("name:" + player.getPlayerName());
            primaryStage.setScene(gameScene);
            roundController.displayStones();
        });
        score.setOnAction(actionEvent -> {
            saveController.rank();
            for (int i=0;i<saveController.getPlayerJsonList().size()&&i<10;i++){
                rankName.get(i).setText(saveController.getPlayerJsonList().get(i).getString("Name"));
                rankScore.get(i).setText(saveController.getPlayerJsonList().get(i).getString("Score"));
            }
            Stage scoreStage = new Stage();
            scoreStage.setHeight(500);
            scoreStage.setWidth(200);
            scoreStage.setScene(scoreScene);
            scoreStage.show();
        });


        newGame.setOnAction(actionEvent -> primaryStage.setScene(newGameScene));
        homeNew.setOnAction(actionEvent -> primaryStage.setScene(primaryScene));
        loadGame.setOnAction(actionEvent -> primaryStage.setScene(loadScene));
        homeLoad.setOnAction(actionEvent -> primaryStage.setScene(primaryScene));

        homePane.getChildren().addAll(newGame, loadGame, homeTitle, aboutMe);
        loadPane.getChildren().addAll(homeLoad, loadConfirm, saveFile);
        newGamePane.getChildren().addAll(newConfirm, homeNew, name);
        gamePane.getChildren().addAll(save, homeGame,score);

            for (var i = 1; i < boxList.size(); i++) {gamePane.getChildren().add(boxList.get(i));}
            for (Line value : lList) {scorePane.getChildren().addAll(value);}
            for (Label label : rank) {scorePane.getChildren().add(label);}
            for (Label label:rankName){scorePane.getChildren().add(label);}
            for (Label label:rankScore){scorePane.getChildren().add(label);}
            for (Circle circle : puzzleList) {gamePane.getChildren().add(circle);}

          //add panes into stage and set stage

            primaryStage.setScene(primaryScene);
            primaryStage.setTitle("BoxGame");
            primaryStage.centerOnScreen();
            primaryStage.setY(primaryStage.getY() * 3f / 2f);
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("C:\\Users\\Fish\\IdeaProjects\\Game\\src\\main\\resources\\Image\\29x29.jpg"));
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                roundController.endTime();
                roundController.getScore();
                saveController.write();
            });
    }

    public static void main(String[] args) {launch();}

}