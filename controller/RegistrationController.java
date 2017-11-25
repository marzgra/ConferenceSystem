package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;

import model.Server;

import static model.TypUzytkownika.PRELEGENT;

/**
 * Created by Alicja on 2017-11-17.
 */


public class RegistrationController implements Initializable {
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
    //    @FXML
//    private TextField typeuser;
    @FXML
    private TextField login;
    @FXML
   private AnchorPane pane1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void przeslij(ActionEvent actionEvent) {

System.out.println("SZlak mnie trafi");
System.out.println("SZlak mnie trafi");
        try {
            Server.getInstance().insertUser(login.getText(), password.getText(),name.getText(), surname.getText(), email.getText(), country.getText(),PRELEGENT);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void onclickStronaGlowna(){
        

//        AnchorPane pane= null;
//        try {
//            pane = FXMLLoader.load(getClass().getResource("/view/Homepage.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pane1.getChildren().setAll(pane);
//



    }
}