package controller;


import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Conference;
import model.Server;

import java.net.URL;
import java.util.ResourceBundle;

public class AssessmentConferenceController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TableView<Conference> assessmentTable;
    @FXML
    private TableColumn<Conference, String> nameCol;
    @FXML
    private TableColumn<Conference, String> descCol;
    @FXML
    private ChoiceBox ocenaP;
    @FXML
    private ChoiceBox ocenaM;
    @FXML
    private ChoiceBox ocenaK;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;


    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void initialize(URL location, ResourceBundle resources) {

        Server.getConferenceInstance().getConferences().removeAll(Server.getConferenceInstance().getConferences());
        ObservableList<Conference> dane = FXCollections.observableArrayList();

        if (Server.getInstance().pastConferenceInfo())
            for (Conference x : Server.getConferenceInstance().getConferences()) {
                dane.add(new Conference(x.getId(), x.getName(), x.getDescription()));

            }

        // Ustawienie danych dla tabeli
        assessmentTable.itemsProperty().setValue(dane);


        nameCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("name")
        );


        descCol.setCellValueFactory(
                new PropertyValueFactory<Conference, String>("description")
        );

    }

    public void onClickBack(ActionEvent actionEvent) {
        myController.setScreen(Main.screen5ID);
    }

    public void przeslijKonferencja(ActionEvent actionEvent) {

        Conference conf = assessmentTable.getSelectionModel().getSelectedItem();
        int ocena = Integer.parseInt((ocenaK.getValue().toString()));

        Server.getInstance().addConferenceMark(conf.getId(), Server.getUserInstance().getId(), ocena);
    }

    public void przeslijMiejsce(ActionEvent actionEvent) {
        Conference conf = assessmentTable.getSelectionModel().getSelectedItem();
        int ocena = Integer.parseInt(ocenaM.getValue().toString());

        System.out.println(ocena);
        Server.getInstance().addLocationMark(conf.getName(), Server.getUserInstance().getId(), ocena);

    }

    public void przeslijPrelegent(ActionEvent actionEvent) {

        String imieP = imie.getText();
        String nazwiskoP = nazwisko.getText();
        Conference conf = assessmentTable.getSelectionModel().getSelectedItem();
        int ocena = Integer.parseInt(ocenaP.getValue().toString());
        System.out.println(ocenaP.getValue());
        Server.getInstance().addLecturerMark(imieP, nazwiskoP, Server.getUserInstance().getId(), ocena);
    }
}