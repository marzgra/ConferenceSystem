package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Server;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-26.
 */
public class EditAccountController implements Initializable,ControlledScreen {

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




    ScreensController myController;
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    public void onClickPowrot(ActionEvent actionEvent) {
        myController.setScreen(Main.screen5ID);
    }

    public void onClickZapisz(ActionEvent actionEvent) {

    }

    public void  onClickUsunKonto(ActionEvent actionEvent) {
        myController.setScreen(Main.screen1ID);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickWyswietlDane(ActionEvent actionEvent) {
        nameText.setText(Server.getInstance().userName());
        surnameText.setText(Server.getInstance().userSurname());
        countryText.setText(Server.getInstance().userCountry());
        loginText.setText(Server.getInstance().userLogin());
        passwordText.setText(Server.getInstance().userPass());
        personText.setText(Server.getInstance().userName());
        emailText.setText(Server.getInstance().userEmail());

    }
}
