package com.ch.oldFXTrials.control;

import com.ch.model.Account;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController extends Controller {
    @FXML
    private WebView browser;

    private Account account;

    protected ChatController(Stage primaryStage, Account account) {
        super(primaryStage, "ChatScreen");
        this.account = account;
    }

    /** for communication to the Javascript engine. */
    private JSObject javascriptConnector;

    /** for communication from the Javascript engine. */
    private JavaConnector javaConnector = new JavaConnector();;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = browser.getEngine();
        URL url = this.getClass().getResource("/html/chatScreen.html");

        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("connector", javaConnector);

                // get the Javascript connector object.
                javascriptConnector = (JSObject) engine.executeScript("getJsConnector()");
            }
        });

        engine.load("http://localhost:4200/");
    }

    public class JavaConnector {
        /**
         * called when the JS side wants a String to be converted.
         *
         * @param value
         *         the String to convert
         */
        public void getValue(String value) {
            if (null != value) {
                javascriptConnector.call("addMessage", value + " - "+ account.getName());
                System.out.println(value);
            }
        }
    }
}
