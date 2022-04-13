package com.example.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public void setButtonPosition(Button button,int x,int y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button newGameStart=new Button("Start New Game");
        Button loadGame =new Button("Load a Game");
        Button save =new Button("Save a Game");
        Button home =new Button("Back to home");

        setButtonPosition(save,350,400);
        setButtonPosition(home,370,400);
        setButtonPosition(newGameStart,300,300);
        setButtonPosition(loadGame,100,300);

        AnchorPane primaryPane =new AnchorPane();
        AnchorPane gamePane =new AnchorPane();
        primaryPane.getChildren().addAll(newGameStart,loadGame);
        Scene primaryScene =new Scene(primaryPane,500,500);
        Scene gameScene=new Scene(gamePane,500,500);

        newGameStart.setOnAction(x->{
            getHostServices().showDocument("https://github.com/nieweilin11");
            primaryStage.setScene(gameScene);
        });
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