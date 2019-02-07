package com.ch.oldFXTrials.control;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

abstract class Controller implements Initializable {
    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected Stage primaryStage;

    protected Controller(Stage primaryStage, String fxml) {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/" + fxml + ".fxml"));
        loader.setController(this);

        try {
            Pane root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
