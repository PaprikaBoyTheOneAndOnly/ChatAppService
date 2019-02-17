package com.ch.oldFXTrials.control;

import com.ch.model.Account;
import com.ch.model.AccountManager;
import com.ch.oldFXTrials.model.ConfirmationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class StartScreenController extends Controller implements Observer {
    @FXML
    private TextField tfName;
    @FXML
    private Button btnLogin;

    private AccountManager manager;

    public StartScreenController(Stage primaryStage) {
        super(primaryStage, "StartScreen");

        this.manager = AccountManager.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(this::login);
        btnLogin.setDefaultButton(true);
    }

    public void login(ActionEvent event) {
        if(!tfName.getText().isEmpty() || !tfName.getText().equals("")){
            Account account = manager.isValidLogin(tfName.getText(),"");
            if(account != null)
                new ChatController(super.primaryStage, account);
            else {
                ConfirmationModel model = new ConfirmationModel(super.primaryStage, "Do you want to create a new Account?");
                model.addObserver(this);
            }
        }
    }

    @Override
    public void update(Observable o, Object answer) {
        this.createAccount((boolean) answer);
    }

    private void createAccount(boolean answer) {
        if(answer)
            manager.createAccount(tfName.getText(),"");
    }
}
