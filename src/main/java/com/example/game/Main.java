package com.example.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    PlayerInfo playerInfo =new PlayerInfo();
    RoundInfo roundInfo=new RoundInfo();
    JudgeState judgeState =new JudgeState();
    SaveWrite saveWrite =new SaveWrite();

    public void setButtonPosition(Button button,int x,int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
    public void setLabelPosition (Label label,int x ,int y){
        label.setLayoutX(x);
        label.setLayoutY(y);
    }
    public void setTextFile(TextField textFile,int x,int y){
        textFile.setLayoutX(x);
        textFile.setLayoutY(y);
    }
    public void setLabelSize(Label label,double x,double y){
        label.setPrefHeight(x);
        label.setPrefWidth(y);
    }
    public Circle setRedStone(int garb){
        int s=60;
        Circle circle=new Circle(11);
        circle.setFill(Color.rgb(128,0,0));
        circle.setTranslateX(48+s*garb);
        circle.setLayoutY(220);
        return circle;
    }
    public Circle setBlackStone(int garb){
        int s=60;
        Circle circle=new Circle(11);
        circle.setFill(Color.rgb(0,0,0));
        circle.setTranslateX(48+s*garb);
        circle.setLayoutY(220);
        return circle;
    }
    public Circle setEmpty(int garb){
        double s=60;
        Circle circle=new Circle(11);
        circle.setFill(Color.rgb(139,69,19));
        circle.setTranslateX(48+s*garb);
        circle.setLayoutY(220);
        return circle;
    }
    public Polyline setBox(){
        Polyline polyline =new Polyline();
        polyline.setStroke(Color.rgb(139,69,19));
        polyline.setStrokeWidth(5);
        polyline.getPoints().addAll(0.0,0.0);
        polyline.getPoints().addAll(0.0,35.0);
        polyline.getPoints().addAll(35.0,35.0);
        polyline.getPoints().addAll(35.0,0.0);
        return polyline;
    }
    public Polyline setBoxPosition(Polyline polyline,double garb){
        polyline.setLayoutX(garb-90);
        polyline.setLayoutY(200);
        return polyline;
    }

    public int[] setStonePuzzle(){
        int[]p={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i =1;i<17;i++){
            p[i]=48+60*i;
        }
        return p;
    }
    public Circle setStonePuzzlePosition(Circle circle,int[] p){
        circle.setCenterX(p[1]);
        return circle;
    }
    public void clickEvent(Circle circle){
        circle.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (circle.getLayoutY() > 180) {
                    circle.setLayoutY(180);
                } else circle.setLayoutY(220);
            }
        });
    }
    public int selected(int i){
        return i;
    }
    public int putBack(int i){
        return i;
    }

    @Override
    public void start(Stage primaryStage) {
        List<Polyline>boxsList =new ArrayList<>();
        for (int i=1;i<18;i++){
            boxsList.add(setBoxPosition(setBox(),60.0*i));
        }
        List<Circle>puzzleList =new ArrayList<>();
        double garb=60;
        puzzleList.add(setRedStone(0));
        puzzleList.add(setBlackStone(1));
        puzzleList.add(setRedStone(2));
        puzzleList.add(setBlackStone(3));
        puzzleList.add(setRedStone(4));
        puzzleList.add(setBlackStone(5));
        for (int i=5;i<16;i++) {
            puzzleList.add(setEmpty(i));
        }

        Button newGame=new Button("Start New Game");
        Button loadGame =new Button("Load a Game");
        Button save =new Button("Save");
        Button homeGame =new Button("Home");
        Button homeLoad =new Button("Home");
        Button homeNew =new Button("Home");
        Button loadConfirm=new Button("confirm");
        Button newConfirm=new Button("confirm");
        Label loadTip =new Label("BoxGame");
        Label homeTitle=new Label("BoxGame");
        Label newTip=new Label("BoxGame");
        Label round=new Label("Round :");
        homeTitle.setTextFill(Color.rgb(124,252,0));
        homeTitle.setFont(new Font(32));
        loadTip.setTextFill(Color.rgb(124,252,0));
        loadTip.setFont(new Font(32));
        newTip.setTextFill(Color.rgb(124,252,0));
        newTip.setFont(new Font(32));
        round.setFont(new Font(15));
        TextField saveFile=new TextField();
        TextField name=new TextField();
        Group boxs =new Group(boxsList.get(1),boxsList.get(2),boxsList.get(3),boxsList.get(4),
                               boxsList.get(5),boxsList.get(6),boxsList.get(7),boxsList.get(8),
                               boxsList.get(9),boxsList.get(10),boxsList.get(11),boxsList.get(12),
                               boxsList.get(13),boxsList.get(14),boxsList.get(15),boxsList.get(16)
        );
        Group stones=new Group(
                puzzleList.get(0),puzzleList.get(1),puzzleList.get(2),
                puzzleList.get(3),puzzleList.get(4),puzzleList.get(5),
                puzzleList.get(6),puzzleList.get(7),puzzleList.get(8),
                puzzleList.get(9),puzzleList.get(10),puzzleList.get(11),
                puzzleList.get(12),puzzleList.get(13),puzzleList.get(14),
                puzzleList.get(15),puzzleList.get(16)
        );

        int[] puzzle={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        setButtonPosition(save,880,450);
        setButtonPosition(homeLoad,400,450);
        setButtonPosition(loadGame,400,450);
        setTextFile(saveFile,150,200);
        setTextFile(name,150,200);
        setButtonPosition(loadConfirm,250,220);
        setButtonPosition(newConfirm,250,220);
        setButtonPosition(newGame,300,300);
        setButtonPosition(loadGame,100,300);
        setButtonPosition(homeGame,930,450);
        setLabelPosition(loadTip,160,150);
        setLabelPosition(newTip,160,150);
        setLabelPosition(round,10,10);
        setLabelPosition(homeTitle,50,200);
        setLabelSize(homeTitle,80,400);
        homeTitle.setAlignment(Pos.CENTER);

        AnchorPane homePane =new AnchorPane();
        AnchorPane gamePane =new AnchorPane();
        AnchorPane loadPane =new AnchorPane();
        AnchorPane newGamePane =new AnchorPane();


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
        Scene primaryScene =new Scene(homePane,500,500);
        Scene gameScene=new Scene(gamePane,1000,500);
        Scene LoadScene=new Scene(loadPane,500,500);
        Scene newGameScene=new Scene(newGamePane,500,500);
        for (int i=0;i<6;i++){
            int finalI = i;
            puzzleList.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (puzzleList.get(finalI).getLayoutY()>180){
                        puzzleList.get(finalI).setLayoutY(180);
                         selected(finalI);
                    }
                    else puzzleList.get(finalI).setLayoutY(220);
                    putBack(finalI);
                }
            });
        }
        for (int i=5;i<16;i++){
            int finalI = i;
            puzzleList.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (puzzleList.get(finalI).getLayoutY()>180){
                        puzzleList.get(finalI).setLayoutY(180);
                        selected(finalI);
                    }
                    else puzzleList.get(finalI).setLayoutY(220);
                    putBack(finalI);
                }
            });
        }
        saveFile.setOnDragOver(new EventHandler<>() {
            @Override
            public void handle(DragEvent dragEvent) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        saveFile.setOnDragDropped(new EventHandler<>() {
            @Override
            public void handle(DragEvent dragEvent) {
                dragEvent.getDragboard();
                if (dragEvent.getDragboard().hasFiles()) {
                    String path = dragEvent.getDragboard().getFiles().get(0).getAbsolutePath();
                    saveFile.setText(path);
                }
            }
        });
        name.setPromptText("Enter your name.");
        saveFile.setPromptText("Please drop a save file to the text bar");
        name.getText();




        newGame.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(newGameScene);
            }
        });
        newConfirm.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(gameScene);
            }
        });
        homeNew.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(primaryScene);
            }
        });
        loadGame.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(LoadScene);
            }
        });
        homeGame.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(primaryScene);
            }
        });
        homeLoad.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(primaryScene);
            }
        });
        loadConfirm.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(gameScene);
            }
        });

        homePane.getChildren().addAll(newGame,loadGame,homeTitle);
        gamePane.getChildren().addAll(save,homeGame,round,boxs,stones);
        loadPane.getChildren().addAll(homeLoad,loadConfirm,saveFile,loadTip);
        newGamePane.getChildren().addAll(newConfirm,homeNew,newTip,name);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BoxGame");
        primaryStage.centerOnScreen();
        primaryStage.setY(primaryStage.getY() * 3f / 2f);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.print("Windows shut down");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }


}