package controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-25.
 */
public class MyAccountController implements Initializable{
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField countryText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField loginText;
    @FXML
    private TextField personText;
    public void initialize(URL location, ResourceBundle resources) {
        nameText.setText("Alicja");
        surnameText.setText("Uzar");
        countryText.setText("Kraków");
        loginText.setText("admin");
        passwordText.setText("admin");
        personText.setText("ADMIN");
        emailText.setText("adminowo@ad.pl");
    }

}

