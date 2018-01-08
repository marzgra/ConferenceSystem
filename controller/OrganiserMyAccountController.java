package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Server;

import java.net.URL;
import java.util.ResourceBundle;

public class OrganiserMyAccountController implements Initializable, ControlledScreen {
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


    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void initialize(URL location, ResourceBundle resources) {
//        if(Server.getInstance().userType("UCZESTNIK",Server.getUserInstance().getId()))
//        mojeKonferencje.setText("MOJE KONFERENCJE");
//           if(Server.getInstance().userType("ORGANIZATOR",Server.getUserInstance().getId()))
//               mojeKonferencje.setText("MOJE KONFERENCJE");
//     if(Server.getInstance().userType("PRELEGENT",Server.getUserInstance().getId()))
//         mojeKonferencje.setText("MOJE ZGLOSZENIA");


    }

    public void onClickUsunKonto(ActionEvent actionEvent) {
        Server.getInstance().deleteUsesr();
        myController.setScreen(Main.screen1ID);
    }

    public void onClickEdytujDane(ActionEvent actionEvent) {
        myController.setScreen(Main.screen7ID);

    }

    public void onClickMojeKonferencje(ActionEvent actionEvent) {


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
        nameText.setText(Server.getUserInstance().getImie());
        surnameText.setText(Server.getUserInstance().getNazwisko());
        countryText.setText(Server.getUserInstance().getMiejscowosc());
        loginText.setText(Server.getUserInstance().getLogin());
        passwordText.setText(Server.getUserInstance().getHaslo());
        personText.setText(Server.getUserInstance().getImie());
        emailText.setText(Server.getUserInstance().getEmail());
    }

    public void onUczestnicy(ActionEvent actionEvent) {

        myController.setScreen(Main.screen12ID);
    }


    public void onZgloszenia(ActionEvent actionEvent) {

    }
}


