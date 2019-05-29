package com.ch.oldFXTrials.view;

import com.ch.oldFXTrials.control.StartScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,4,5,6,7,8,9);

        String topics = "";
        for (int i = 0; i < numbers.size(); i++) {
            topics = topics + numbers.get(i);

            if(i < numbers.size()-1)
            topics = topics + ",";
        }

        System.out.println(topics);
        //launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        new StartScreenController(primaryStage);

        primaryStage.setTitle("Chat Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
