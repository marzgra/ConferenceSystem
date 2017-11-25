package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Alicja on 2017-11-21.
 */
public class HomepageUPController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button mojeKonto;

    @FXML
    public void onClickZaloguj(ActionEvent event)
    {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    public void onClickZapisz(ActionEvent event)
    {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);

    }

    public void onClickWyloguj(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/Homepage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);

    }

    public void onClickMojeKonto(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/MyAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);
    }
}