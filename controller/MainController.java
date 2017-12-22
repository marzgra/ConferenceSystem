package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable, ControlledScreen {

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void onClickZaloguj(ActionEvent event) {
        myController.setScreen(Main.screen3ID);

    }

    @FXML
    public void onClickZapisz(ActionEvent event) {
        myController.setScreen(Main.screen2ID);

    }
}