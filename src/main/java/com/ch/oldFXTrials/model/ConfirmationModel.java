package com.ch.oldFXTrials.model;

import com.ch.oldFXTrials.control.popUp.ConfirmationPopUp;
import javafx.stage.Stage;

import java.util.Observable;

public class ConfirmationModel extends Observable {
    private Stage primaryStage;
    private String question;

    public ConfirmationModel(Stage primaryStage, String question){
        this.primaryStage = primaryStage;
        this.question = question;
        new ConfirmationPopUp(this);
    }

    public void setAnswer(boolean answer) {
        this.setChanged();
        this.notifyObservers(answer);
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public String getQuestion() {
        return question;
    }
}
