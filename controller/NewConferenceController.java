package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Server;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class NewConferenceController implements Initializable, ControlledScreen {

    @FXML
    public TextField nazwaLabel;
    @FXML
    public ChoiceBox miejsceChoiceBox;
    @FXML
    public TextArea opisField;
    @FXML
    public DatePicker dataRoz;
    @FXML
    public DatePicker dataZak;
    @FXML
    public TextField iloscField;
    @FXML
    public TextField cenaField;

    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaMiejsc = FXCollections.observableArrayList();
        listaMiejsc.addAll(Server.getInstance().getPlacesNames());

        miejsceChoiceBox.setItems(listaMiejsc);
    }

    @FXML
    public void dodajWydarzenie(ActionEvent actionEvent) {
        String nazwaMiejsca = miejsceChoiceBox.getSelectionModel().getSelectedItem().toString();
        String nazwaKonferencji = nazwaLabel.getText();
        Date dataOd = Date.valueOf(dataRoz.getValue());
        Date dataDo = Date.valueOf(dataZak.getValue());
        String opis = opisField.getText();
        int ilosc = Integer.valueOf(iloscField.getText());
        double cena = Double.valueOf(cenaField.getText());

        try {
            Server.getInstance().insertNewConference(nazwaMiejsca, nazwaKonferencji, dataOd, dataDo, opis, ilosc, cena);
        } catch (Exception e) {
            System.out.println("NewConferenceController error: " + e.getLocalizedMessage());
        }

        myController.setScreen(Main.screen6ID);
        nazwaLabel.setText("");
        dataDo.setTime(0);
        dataOd.setTime(0);
        opisField.setText("");
        iloscField.setText("");
        cenaField.setText("");
        miejsceChoiceBox.setValue("");

    }

    public void powrot(ActionEvent actionEvent) {
        myController.setScreen(Main.screen14ID);
    }


}
