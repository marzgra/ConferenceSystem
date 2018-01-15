package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import model.Server;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class LogInController implements Initializable, ControlledScreen {


    @FXML
    private TextField login1;
    @FXML
    private TextField haslo1;

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login1.setText("");
        haslo1.setText("");

    }

    public void onClickZapisz(ActionEvent actionEvent) {
        myController.setScreen(Main.screen2ID);
    }

    @FXML
    public void onclickZaloguj(ActionEvent actionEvent) {

        Boolean znaleziono;
        znaleziono = Server.getInstance().logIn(login1.getText(), haslo1.getText());
        System.out.println(znaleziono);
        if (znaleziono) {
            myController.setScreen(Main.screen5ID);
            //jak się zalogują mają ten sam panel
            if(Server.getInstance().userType("UCZESTNIK",Server.getUserInstance().getId()))
            {myController.setScreen(Main.screen5ID);}
           else if(Server.getInstance().userType("ORGANIZATOR",Server.getUserInstance().getId()))
            {  myController.setScreen(Main.screen5ID);}
              else if(Server.getInstance().userType("PRELEGENT",Server.getUserInstance().getId()))
            { myController.setScreen(Main.screen5ID);}
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
        login1.setText("");
        haslo1.setText("");

    }

    public void onclickStronaGlowna() {

        myController.setScreen(Main.screen1ID);


    }


}