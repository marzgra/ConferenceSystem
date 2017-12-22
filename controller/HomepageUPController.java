package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-21.
 */
public class HomepageUPController implements Initializable, ControlledScreen {

    @FXML
    private Button mojeKonto;

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void onClickWyloguj(ActionEvent actionEvent) {
        myController.setScreen(Main.screen1ID);

    }

    public void onClickMojeKonto(ActionEvent actionEvent) {
        myController.setScreen(Main.screen4ID);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}