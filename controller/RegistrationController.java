package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model.Server;
import model.TypUzytkownika;

import static model.TypUzytkownika.PRELEGENT;

/**
 * Created by Alicja on 2017-11-17.
 */


public class RegistrationController implements Initializable, ControlledScreen {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField country;
    @FXML
    private TextField password;
    @FXML
    private TextField password2;
    @FXML
    private ChoiceBox wybor;
    @FXML
    private TextField login;
    @FXML
    private AnchorPane pane1;

    TypUzytkownika typUzytkownika;


    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    MainController homepage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void przeslij(ActionEvent actionEvent) {

        if (wybor.getValue().toString().equals("UCZESTNIK")) {
            typUzytkownika = TypUzytkownika.UCZESTNIK;
        } else if (wybor.getValue().toString().equals("PRELEGENT")) {
            typUzytkownika = TypUzytkownika.PRELEGENT;
        } else if (wybor.getValue().toString().equals("ORGANIZATOR")) {
            typUzytkownika = TypUzytkownika.ORGANIZATOR;
        }
        try {

            Server.getInstance().insertUser(login.getText(), password.getText(), name.getText(), surname.getText(), email.getText(), country.getText(), typUzytkownika);
        } catch (Exception e) {
            e.getMessage();
        }

        myController.setScreen(Main.screen1ID);
    }

    @FXML
    public void onclickStronaGlowna() {


        myController.setScreen(Main.screen1ID);


    }
}