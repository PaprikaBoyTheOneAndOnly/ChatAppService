package com.ch.oldFXTrials.view;

import com.ch.oldFXTrials.control.StartScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        new StartScreenController(primaryStage);

        primaryStage.setTitle("Chat Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
