package controller;


import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Conference;
import model.Server;
import model.TypUzytkownika;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchConferenceController implements Initializable, ControlledScreen {


    ScreensController myController;

    @FXML
    private TableView<Conference> conferenceTable;
    @FXML
    private TableColumn<Conference, String> nameCol;
    @FXML
    private TableColumn<Conference, String> descCol;

    @FXML
    private TextField wyszukajPrelegenta;
    @FXML
    private TextField wyszukajOrganizatora;
    @FXML
    private TextField wyszukajMiasto;
    @FXML
    private DatePicker dataOd;
    @FXML
    private DatePicker dataDo;
    @FXML
    private TextField nazwiskoPrelegenta;
    @FXML
    private TextField nazwiskoOrganizatora;


    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void onClickBack(ActionEvent actionEvent) {
        if (Server.getUserInstance() == null) {
            // uzytkownik niezalogowany
            myController.setScreen(Main.screen1ID);
        }else{
            myController.setScreen(Main.screen5ID);
        }
    }

    public void wyszukajPrelegenta(ActionEvent actionEvent) {
        Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
        ObservableList<Conference> dane = FXCollections.observableArrayList();

        if (Server.getInstance().searchLecturer(wyszukajPrelegenta.getText(), nazwiskoPrelegenta.getText()))
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getName(), x.getDescription()));

            }

        // Ustawienie danych dla tabeli
        conferenceTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );


        descCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );


    }


    public void wyszukajPoDacie(ActionEvent actionEvent) {
        Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
        ObservableList<Conference> dane = FXCollections.observableArrayList();

        LocalDate dataRozpoczecia = dataOd.getValue();
        LocalDate datazakonczenia = dataDo.getValue();

        System.out.println(dataRozpoczecia);
        System.out.println(datazakonczenia);

        if (Server.getInstance().searchDate(dataRozpoczecia.toString(), datazakonczenia.toString()))
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getName(), x.getDescription()));

            }


        // Ustawienie danych dla tabeli
        conferenceTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );


        descCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );


    }

    public void wyszukajOrganizatora(ActionEvent actionEvent) {
        Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
        ObservableList<Conference> dane = FXCollections.observableArrayList();


        if (Server.getInstance().searchOrganiser(wyszukajOrganizatora.getText(), nazwiskoOrganizatora.getText()))
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getName(), x.getDescription()));

            }


        // Ustawienie danych dla tabeli
        conferenceTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );


        descCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );

    }

    public void wyszukajMiasto(ActionEvent actionEvent) {
        Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
        ObservableList<Conference> dane = FXCollections.observableArrayList();


        if (Server.getInstance().searchLocation(wyszukajMiasto.getText()))
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getName(), x.getDescription()));

            }


        // Ustawienie danych dla tabeli
        conferenceTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );


        descCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );


    }
}