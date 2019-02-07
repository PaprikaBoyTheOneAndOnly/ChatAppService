package com.ch.oldFXTrials.control.popUp;

import com.ch.oldFXTrials.model.ConfirmationModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ConfirmationPopUp implements Initializable {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FXML
    private Button btnYes, btnNo;
    @FXML
    private Label lbQuestion;

    private ConfirmationModel model;

    private Stage stage;

    public ConfirmationPopUp(ConfirmationModel model) {
        stage = new Stage();
        this.model = model;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/popUp/ConfirmationPopUpScreen.fxml"));
        loader.setController(this);

        try {
            Pane root = loader.load();

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Confirmation");
            stage.initOwner(model.getPrimaryStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNo.setOnAction(a -> finalizeDialog(false));
        btnYes.setOnAction(a -> finalizeDialog(true));
        lbQuestion.setText(model.getQuestion());
    }

    private void finalizeDialog(boolean answer) {
        model.setAnswer(answer);
        stage.close();
        logger.info("New account: " + answer);
    }
}
