package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class WebBrowserController {

    @FXML
    private GridPane PageGridPane;

    @FXML
    private TextField addressTextField;

    @FXML
    private Button button;

    @FXML
    private WebView URLWebView;

    @FXML
    private Label loadLabel;

    @FXML
    private ImageView loadImageView;

    private WebEngine webEngine;

    public void initialize(){
        webEngine = URLWebView.getEngine();
        loadLabel.setText("");
        URLWebView.prefWidthProperty().bind(PageGridPane.widthProperty());
        URLWebView.prefHeightProperty().bind(PageGridPane.heightProperty());
    }

    public void urlLabelPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            loadWebSites();
        }
    }

    private String Validation(String url){

        String form = url.substring(0, 7);
        if(!(form.equals("http://"))){
            url = "http://" + url;
        }

        return url;
    }

    @FXML
    void buttonOnPressed(ActionEvent event) {
        loadWebSites();
    }

    private void loadWebSites(){
//        Image loadImg = new Image("sample/images/loading.gif");

        String validUrl = Validation(addressTextField.getText());
        webEngine.load(validUrl);
        addressTextField.setText(validUrl);
        loadLabel.setText("Please, wait...");
//        loadImageView.setImage(loadImg);

        webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                loadLabel.setText(" ");
            }
        });

    }

}