package controller;


import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyAccountController implements Initializable, ControlledScreen {
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField countryText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField loginText;
    @FXML
    private TextField personText;
    @FXML
    private Button mojeKonferencje;


    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void initialize(URL location, ResourceBundle resources) {


    }

    public void onClickUsunKonto(ActionEvent actionEvent) {
        Server.getInstance().deleteUsesr();
        myController.setScreen(Main.screen1ID);
    }

    public void onClickEdytujDane(ActionEvent actionEvent) {
        myController.setScreen(Main.screen7ID);

    }

    public void onClickMojeKonferencje(ActionEvent actionEvent) {
        if (Server.getInstance().czyOrganizatorUczPrelegent("UCZESTNIK", Server.getUserInstance().getId())) {
            myController.setScreen(Main.screen11ID);
        } else if (Server.getInstance().czyOrganizatorUczPrelegent("PRELEGENT", Server.getUserInstance().getId())) {
            myController.setScreen(Main.screen8ID);
        }


    }

    public void onClickPowrot(ActionEvent actionEvent) {
        myController.setScreen(Main.screen5ID);

        nameText.setText(" ");
        surnameText.setText(" ");
        countryText.setText(" ");
        loginText.setText(" ");
        passwordText.setText(" ");
        personText.setText(" ");
        emailText.setText(" ");
    }


    public void onClickWyswietl(ActionEvent actionEvent) {
        if (Server.getInstance().czyOrganizatorUczPrelegent("UCZESTNIK", Server.getUserInstance().getId())) {
            mojeKonferencje.setText("Moje konferencje");
        } else if (Server.getInstance().czyOrganizatorUczPrelegent("PRELEGENT", Server.getUserInstance().getId())) {
            mojeKonferencje.setText("Moje zgloszenia");
        }


        nameText.setText(Server.getUserInstance().getImie());
        surnameText.setText(Server.getUserInstance().getNazwisko());
        countryText.setText(Server.getUserInstance().getMiejscowosc());
        loginText.setText(Server.getUserInstance().getLogin());
        passwordText.setText(Server.getUserInstance().getHaslo());
        personText.setText(Server.getUserInstance().getImie());
        emailText.setText(Server.getUserInstance().getEmail());
    }
}