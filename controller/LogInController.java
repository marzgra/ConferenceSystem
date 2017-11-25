package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alicja on 2017-11-11.
 */
public class LogInController implements Initializable {

    @FXML
    private AnchorPane loginPane;
    @FXML



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickZapisz(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginPane.getChildren().setAll(pane);
    }
    @FXML
    public void onclickStronaGlowna(ActionEvent actionEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/panelG.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginPane.getChildren().setAll(pane);
    }


}
