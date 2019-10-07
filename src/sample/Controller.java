package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class Controller {

    @FXML private TextField urlField;
    @FXML private WebView webView;
    private WebEngine webEngine;
    @FXML private Label loading;

    @FXML void initialize(){
         webEngine = webView.getEngine();
         loading.setText(" ");
    }

    @FXML void urlLabelPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            load();

        }
    }

    private String getValidUrl(String url){

        String prefix = url.substring(0, 7);
        if(!(prefix.equals("http://"))){
            url = "http://" + url;
        }

        return url;
    }

    @FXML
    void GoSite(ActionEvent event) {
        load();
    }

    private void load(){
        String validUrl = getValidUrl(urlField.getText());
        webEngine.load(validUrl);
        urlField.setText(validUrl);
        loading.setText("Loading ...");
        webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                loading.setText(" ");
            }
        });
    }

}