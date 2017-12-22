package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-21.
 */
public class HomepageOrganizatorController implements Initializable, ControlledScreen {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button mojeKonto;

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }


    public void onClickWyloguj(ActionEvent actionEvent) {
        myController.setScreen(Main.screen1ID);
    }

    @FXML
    public void onClickMojeKonto(ActionEvent actionEvent) {
        myController.setScreen(Main.screen4ID);
    }

    public void onClickDodajKonferencje(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}