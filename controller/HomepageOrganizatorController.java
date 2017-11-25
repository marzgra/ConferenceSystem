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
public class HomepageOrganizatorController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button mojeKonto;




    public void onClickWyloguj(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/Homepage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    public void onClickMojeKonto(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/MyAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(pane);
    }

    public void onClickDodajKonferencje(ActionEvent actionEvent) {
    }
}
