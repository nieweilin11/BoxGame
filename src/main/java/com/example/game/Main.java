package com.example.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.util.AbstractList;
import java.util.List;

public class Main extends Application {

    public void setButtonPosition(Button button,int x,int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
    public void setLabelPosition (Label label,int x ,int y){
        label.setLayoutX(x);
        label.setLayoutY(y);
    }
    public void setLabelSize(Label label,double x,double y){
        label.setPrefHeight(x);
        label.setPrefWidth(y);
    }
    public void setBoxPosition(Box box ,double x){
        box.setTranslateX(x);

    }
    public void setPuzzle(Box e,Box r,Box b,int[] p){
        double garb=30;
        for (int i=0;i< p.length;i++){
            switch (p[i]){
                case 0 ->setBoxPosition(e,garb*i);
                case 1->setBoxPosition(r,garb*i);
                case 2->setBoxPosition(b,garb*i);
            }
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button newGameStart=new Button("Start New Game");
        Button loadGame =new Button("Load a Game");
        Button save =new Button("SaveGame");
        Button homeGame =new Button("Home");
        Button homeLoad =new Button("Home");
        Button loadConfirm=new Button("confirm");
        Label label=new Label("BoxGame");
        Label round=new Label("Round :");

        Box emptyBox=new Box(20,20,20);
        Box redChess =new Box(10,10,10);
        Box blackChess =new Box(10,10,10);
        emptyBox.setStyle("-fx-border-color:brown;-fx-border-width: 5px");
        redChess.setStyle("-fx-background-color: red");
        blackChess.setStyle("-fx-background-color: black");

        setButtonPosition(save,170,450);
        setButtonPosition(homeLoad,400,450);
        setButtonPosition(loadGame,400,450);
        setButtonPosition(newGameStart,300,300);
        setButtonPosition(loadGame,100,300);
        setButtonPosition(homeGame,270,450);
        setLabelPosition(round,10,10);
        setLabelPosition(label,50,200);
        label.setStyle("-fx-background-color:brown; -fx-border-color:brown;-fx-border-width: 3px");
        setLabelSize(label,80,400);
        label.setAlignment(Pos.CENTER);

        AnchorPane homePane =new AnchorPane();
        AnchorPane gamePane =new AnchorPane();
        AnchorPane loadPane =new AnchorPane();
        Scene primaryScene =new Scene(homePane,500,500);
        Scene gameScene=new Scene(gamePane,1000,500);
        Scene LoadScene=new Scene(loadPane,500,500);

        newGameStart.setOnAction(x->{
            getHostServices().showDocument("https://github.com/nieweilin11");
            primaryStage.setScene(gameScene);
        });
        loadGame.setOnAction(x->{
            primaryStage.setScene(LoadScene);
        });
        homeGame.setOnAction(x->{
            primaryStage.setScene(primaryScene);
        });
        homeLoad.setOnAction(x->{
            primaryStage.setScene(primaryScene);
        });
        loadConfirm.setOnAction(x->{
            System.out.println("load game");
            primaryStage.setScene(gameScene);
        });
        homePane.getChildren().addAll(newGameStart,loadGame,label);
        gamePane.getChildren().addAll(save,homeGame,round);
        loadPane.getChildren().addAll(homeLoad,loadConfirm);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BoxGame");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}