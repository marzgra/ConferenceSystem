package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Server;



import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-11.
 */
public class LogInController implements Initializable,ControlledScreen {


    @FXML
    private TextField login1;
    @FXML
    private TextField haslo1;

    ScreensController myController;
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickZapisz(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }
    @FXML
    public void onclickZaloguj(ActionEvent actionEvent) {
        AnchorPane pane = null;
        int znaleziono;
        znaleziono = Server.getInstance().checkLoginPass(login1.getText(), haslo1.getText());
        System.out.println(znaleziono);
        if (znaleziono == 1) {
            myController.setScreen(Main.screen5ID);
        } else {
            System.out.println("Nie wchodzimy");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Niepoprawny login lub haslo. Sprobuj jeszcze raz.",
                    ButtonType.YES);
            alert.setTitle("Ostrzezenie");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

            }
        }

    }

    public void onclickStronaGlowna(){

        myController.setScreen(Main.screen1ID);


    }


}
