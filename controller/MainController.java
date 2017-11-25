package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    //
//    @FXML
//    private Button zalogujSie;
//
    Stage prevStage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {}



    public void setPrevStage(Stage stage)
    {
        this.prevStage = stage;
    }

    @FXML
    private AnchorPane rootPane;

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
}


